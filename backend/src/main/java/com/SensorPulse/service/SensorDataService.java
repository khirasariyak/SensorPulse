package com.SensorPulse.service;

import com.SensorPulse.dal.DataStore;
import com.SensorPulse.model.SensorData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.SensorPulse.constant.Constants.SENSOR_DATA_TOPIC;

@Service
public class SensorDataService {

    @Autowired
    private KafkaTemplate<String, SensorData> kafkaTemplate;

    @Autowired
    private DataStore dataStore;

    public void addSensorData(SensorData sensorData) {
        kafkaTemplate.send(SENSOR_DATA_TOPIC, sensorData.getSensorId(), sensorData);
    }

    public List<SensorData> getAllSensorData() {
        return dataStore.getAllSensorData();
    }


}
