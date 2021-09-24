package com.example.apitest.beans;

public class SensorReading {

    private String sensorId;
    private Long stampTime;
    private Double temperature;

    public SensorReading() {}

    public SensorReading(String sensorId, Long stampTime, Double temperature) {
        this.sensorId = sensorId;
        this.stampTime = stampTime;
        this.temperature = temperature;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public Long getStampTime() {
        return stampTime;
    }

    public void setStampTime(Long stampTime) {
        this.stampTime = stampTime;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    @Override
    public String toString() {
        return "SensorReading{" +
                "sensorId='" + sensorId + '\'' +
                ", stampTime=" + stampTime +
                ", temperature=" + temperature +
                '}';
    }
}
