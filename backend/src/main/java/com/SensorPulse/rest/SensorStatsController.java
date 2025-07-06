package com.SensorPulse.rest;

import com.SensorPulse.model.SensorStats;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StoreQueryParameters;
import org.apache.kafka.streams.state.KeyValueIterator;
import org.apache.kafka.streams.state.QueryableStoreTypes;
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.StreamsBuilderFactoryBean;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.SensorPulse.constant.Constants.SENSOR_STATS_STORE;

@RestController
@RequestMapping("sensor-stats")
@CrossOrigin(origins = "http://localhost:3000")
public class SensorStatsController {

    @Autowired
    StreamsBuilderFactoryBean factoryBean;

    @GetMapping()
    public ResponseEntity<List<SensorStats>> getAllSensorStats() {

        KafkaStreams kafkaStreams = factoryBean.getKafkaStreams();

        ReadOnlyKeyValueStore<String, SensorStats> store =
                kafkaStreams.store(
                        StoreQueryParameters.fromNameAndType(SENSOR_STATS_STORE, QueryableStoreTypes.keyValueStore())
                );

        List<SensorStats> list = new ArrayList<>();

        KeyValueIterator<String, SensorStats> all = store.all();

        while (all.hasNext()) {
            KeyValue<String, SensorStats> keyValue = all.next();
            list.add(keyValue.value);
        }

        return ResponseEntity.ok(list);
    }

}
