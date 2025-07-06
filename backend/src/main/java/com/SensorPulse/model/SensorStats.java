package com.SensorPulse.model;

public class SensorStats {
    private String sensorId;
    private double minTemperature;
    private double maxTemperature;
    private double avgTemperature;
    private double minHumidity;
    private double maxHumidity;
    private double avgHumidity;
    private long count;

    public SensorStats() {}

    public SensorStats(String sensorId) {
        this.sensorId = sensorId;
        this.minTemperature = Double.MAX_VALUE;
        this.maxTemperature = Double.MIN_VALUE;
        this.minHumidity = Double.MAX_VALUE;
        this.maxHumidity = Double.MIN_VALUE;
        this.avgTemperature = 0;
        this.avgHumidity = 0;
        this.count = 0;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public double getMinTemperature() {
        return minTemperature;
    }

    public void setMinTemperature(double minTemperature) {
        this.minTemperature = minTemperature;
    }

    public double getMaxTemperature() {
        return maxTemperature;
    }

    public void setMaxTemperature(double maxTemperature) {
        this.maxTemperature = maxTemperature;
    }

    public double getAvgTemperature() {
        return avgTemperature;
    }

    public void setAvgTemperature(double avgTemperature) {
        this.avgTemperature = avgTemperature;
    }

    public double getMinHumidity() {
        return minHumidity;
    }

    public void setMinHumidity(double minHumidity) {
        this.minHumidity = minHumidity;
    }

    public double getMaxHumidity() {
        return maxHumidity;
    }

    public void setMaxHumidity(double maxHumidity) {
        this.maxHumidity = maxHumidity;
    }

    public double getAvgHumidity() {
        return avgHumidity;
    }

    public void setAvgHumidity(double avgHumidity) {
        this.avgHumidity = avgHumidity;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "SensorStats{" +
                "sensorId='" + sensorId + '\'' +
                ", minTemperature=" + minTemperature +
                ", maxTemperature=" + maxTemperature +
                ", avgTemperature=" + avgTemperature +
                ", minHumidity=" + minHumidity +
                ", maxHumidity=" + maxHumidity +
                ", avgHumidity=" + avgHumidity +
                ", count=" + count +
                '}';
    }
}
