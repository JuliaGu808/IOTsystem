package com.example.intelliji.model;

import java.io.Serializable;
import java.security.Timestamp;
import java.time.LocalDateTime;

public class DeviceEsp32Dht11 implements Serializable {
    private int id;
    private String deviceName, deviceHolder;
    private String temperature, humidity;
    private String timestamp;

    public DeviceEsp32Dht11() {}

    public DeviceEsp32Dht11(int id, String deviceName, String deviceHolder, String timestamp) {
        this.id = id;
        this.deviceName = deviceName;
        this.deviceHolder = deviceHolder;
        this.timestamp = timestamp;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public void setDeviceHolder(String deviceHolder) {
        this.deviceHolder = deviceHolder;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public void setDateTime(String dateTime) {
        this.timestamp = dateTime;
    }

    public int getId() {
        return id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getDeviceHolder() {
        return deviceHolder;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getDateTime() {
        return timestamp;
    }
}




