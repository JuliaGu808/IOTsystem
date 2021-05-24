package com.example.intelliji.model;

import java.io.Serializable;

public class AvgTemperature implements Serializable {
    private String deviceHolder;
    private String temperature;

        public AvgTemperature() {}

        public AvgTemperature(String deviceHolder, String temperature) {
            this.deviceHolder = deviceHolder;
            this.temperature = temperature;
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
}
