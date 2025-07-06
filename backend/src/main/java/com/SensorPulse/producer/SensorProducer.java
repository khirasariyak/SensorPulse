package com.SensorPulse.producer;

import com.SensorPulse.model.SensorData;
import com.SensorPulse.rest.SensorDataController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@EnableScheduling
public class SensorProducer {

    private final Random random = new Random();
    private double currentTemperature = 20.0;
    private double currentHumidity = 30.0;

    @Autowired
    SensorDataController sensorDataController;

    @Scheduled(fixedRate = 10)
    public void sendSensorData() {
        SensorData sensorData = createSensorData();
        sensorDataController.addSensorData(sensorData);
    }

    private SensorData createSensorData() {
        String sensorId = "sensor_" + (random.nextInt(10) + 1);

        currentTemperature = clamp(currentTemperature + getRandomDelta(), 1.0, 50.0);
        currentHumidity = clamp(currentHumidity + getRandomDelta(), 1.0, 50.0);

        long timestamp = System.currentTimeMillis();
        return new SensorData(sensorId, currentTemperature, currentHumidity, timestamp);
    }

    private double getRandomDelta() {
        return (random.nextDouble() - 0.5); // returns value in range [-0.5, +0.5)
    }

    private double clamp(double value, double min, double max) {
        return Math.max(min, Math.min(max, value));
    }

}
