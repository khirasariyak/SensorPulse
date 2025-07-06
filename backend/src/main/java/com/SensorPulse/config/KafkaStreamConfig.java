package com.SensorPulse.config;

import com.SensorPulse.model.SensorData;
import com.SensorPulse.model.SensorStats;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.common.utils.Bytes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.state.KeyValueStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.support.serializer.JsonSerde;

import static com.SensorPulse.constant.Constants.SENSOR_ANALYTICS_TOPIC;
import static com.SensorPulse.constant.Constants.SENSOR_DATA_TOPIC;
import static com.SensorPulse.constant.Constants.SENSOR_STATS_STORE;

@Configuration
public class KafkaStreamConfig {

    @Bean
    public KStream<String, SensorData> sensorStatsStream(StreamsBuilder builder) {

        Serde<SensorData> sensorDataSerde = new JsonSerde<>(SensorData.class);
        Serde<SensorStats> sensorStatsSerde = new JsonSerde<>(SensorStats.class);

        KStream<String, SensorData> inputStream =
                builder.stream(SENSOR_DATA_TOPIC, Consumed.with(Serdes.String(), sensorDataSerde));

        KTable<String, SensorStats> statsTable = inputStream
                .groupByKey()
                .aggregate(
                        SensorStats::new,  // Initializer
                        (key, value, agg) -> updateStats(agg, value),
                        Materialized.<String, SensorStats, KeyValueStore<Bytes, byte[]>>as(SENSOR_STATS_STORE)
                                .withKeySerde(Serdes.String())
                                .withValueSerde(sensorStatsSerde)
                );

        statsTable
                .toStream()
                .to(SENSOR_ANALYTICS_TOPIC, Produced.with(Serdes.String(), sensorStatsSerde));

        return inputStream;
    }

    private SensorStats updateStats(SensorStats stats, SensorData data) {
        if (stats.getSensorId() == null) {
            stats.setSensorId(data.getSensorId());
            stats.setMinHumidity(data.getHumidity());
            stats.setMinTemperature(data.getTemperature());
        }

        double temp = data.getTemperature();
        double hum = data.getHumidity();
        long count = stats.getCount();

        stats.setMinTemperature(Math.min(stats.getMinTemperature(), temp));
        stats.setMaxTemperature(Math.max(stats.getMaxTemperature(), temp));
        stats.setMinHumidity(Math.min(stats.getMinHumidity(), hum));
        stats.setMaxHumidity(Math.max(stats.getMaxHumidity(), hum));

        // Running average
        double tempAvg = ((stats.getAvgTemperature() * count) + temp) / (count + 1);
        double humAvg = ((stats.getAvgHumidity() * count) + hum) / (count + 1);

        stats.setCount(count + 1);
        stats.setAvgTemperature(tempAvg);
        stats.setAvgHumidity(humAvg);

        return stats;
    }
}
