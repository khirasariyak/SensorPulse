package com.SensorPulse.consumer;

import com.SensorPulse.dal.DataStore;
import com.SensorPulse.model.SensorData;
import com.SensorPulse.model.SensorStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import static com.SensorPulse.constant.Constants.SENSOR_ANALYTICS_GROUP_ID;
import static com.SensorPulse.constant.Constants.SENSOR_ANALYTICS_TOPIC;
import static com.SensorPulse.constant.Constants.SENSOR_DATA_GROUP_ID;
import static com.SensorPulse.constant.Constants.SENSOR_DATA_TOPIC;

@Service
public class SensorConsumer {

    @Autowired
    private DataStore dataStore;

    @KafkaListener(topics = SENSOR_DATA_TOPIC, groupId = SENSOR_DATA_GROUP_ID)
    public void consume(SensorData sensorData) {
        dataStore.add(sensorData);
        System.out.println("Consumed: " + sensorData);
    }

    @KafkaListener(topics = SENSOR_ANALYTICS_TOPIC, groupId = SENSOR_ANALYTICS_GROUP_ID)
    public void consume(SensorStats sensorStats) {

        System.out.println("Processed Stats:" + sensorStats);
    }


}
