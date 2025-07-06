package com.SensorPulse.dal;

import com.SensorPulse.model.SensorData;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DataStore {

    private final Map<String, SensorData> sensorDataMap = new ConcurrentHashMap<>();

    public void add(SensorData sensorData) {
        sensorDataMap.put(sensorData.getSensorId(), sensorData);
    }

    public List<SensorData> getAllSensorData() {
        return sensorDataMap.values().stream().toList();
    }

    public SensorData getSensorDataById(String sensorDataId) {
        return sensorDataMap.get(sensorDataId);
    }


}
