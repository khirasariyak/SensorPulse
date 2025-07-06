package com.SensorPulse.rest;

import com.SensorPulse.model.SensorData;
import com.SensorPulse.service.SensorDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("sensor-data")
public class SensorDataController {

    @Autowired
    private SensorDataService sensorDataService;

    @PostMapping()
    public ResponseEntity<Void> addSensorData(@RequestBody SensorData sensorData) {
        sensorDataService.addSensorData(sensorData);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<SensorData>> getSensorData() {
        List<SensorData> allSensorData = sensorDataService.getAllSensorData();
        return new ResponseEntity<>(allSensorData, HttpStatus.OK);
    }


}
