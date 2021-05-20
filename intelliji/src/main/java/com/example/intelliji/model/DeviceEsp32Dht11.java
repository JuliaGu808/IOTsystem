package com.example.intelliji.model;

import java.io.Serializable;
import java.security.Timestamp;
import java.time.LocalDateTime;

public class DeviceEsp32Dht11 implements Serializable {
    private String deviceName, deviceHolder;
    private String temperature, humidity;
    private String recordtime;

    public DeviceEsp32Dht11() {}

    public DeviceEsp32Dht11(String deviceName, String deviceHolder, String temperature, String humidity, String recordtime) {
        this.deviceName = deviceName;
        this.deviceHolder = deviceHolder;
        this.temperature = temperature;
        this.humidity = humidity;
        this.recordtime = recordtime;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceHolder() {
        return deviceHolder;
    }

    public void setDeviceHolder(String deviceHolder) {
        this.deviceHolder = deviceHolder;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getRecordtime() {
        return recordtime;
    }

    public void setRecordtime(String recordtime) {
        this.recordtime = recordtime;
    }

    @Override
    public String toString() {
        return "deviceName=" + deviceName +
                ",deviceHolder=" + deviceHolder +
                ",temperature=" + temperature +
                ",humidity=" + humidity +
                ",recordtime=" + recordtime;
    }
}




