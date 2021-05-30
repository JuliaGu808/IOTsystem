package com.example.intelliji.model;

import java.io.Serializable;

public class AvgTemperature implements Serializable {
    private String deviceHolder;
    private String deviceName;
    private String temperature;

        public AvgTemperature() {}

    public AvgTemperature(String deviceHolder, String deviceName, String temperature) {
        this.deviceHolder = deviceHolder;
        this.deviceName = deviceName;
        this.temperature = temperature;
    }

    public String getDeviceHolder() {
        return deviceHolder;
    }

    public void setDeviceHolder(String deviceHolder) {
        this.deviceHolder = deviceHolder;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
