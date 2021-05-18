package com.example.intelliji.dao;

import com.example.intelliji.model.DeviceEsp32Dht11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.io.FileInputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class DeviceDao {

    Properties p = new Properties();

    public boolean addData(DeviceEsp32Dht11 d){
        String query = " insert into iotdevice (id, deviceName, deviceHolder, temperature, humidity, timestamp) values (?, ?, ?, ?, ?, ?)";
        int rowChanged = 0;

        try (Connection con = DriverManager.getConnection(System.getenv("connectionstring"),
                System.getenv("spring.datasource.username"),
                System.getenv("spring.datasource.password"));
             PreparedStatement stmt = con.prepareStatement(query)){

            stmt.setInt(1, d.getId());
            stmt.setString(2, d.getDeviceName());
            stmt.setString(3, d.getDeviceHolder());
            stmt.setString(4, d.getTemperature());
            stmt.setString(5, d.getHumidity());
            stmt.setString(6, d.getDateTime());
            rowChanged = stmt.executeUpdate();
            if (rowChanged == 1){
                return true;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
