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

    public boolean addData(DeviceEsp32Dht11 d){
        String query = " insert into iotdevice (deviceName, deviceHolder, temperature, humidity, recordtime) values (?, ?, ?, ?, ?)";
        int rowChanged = 0;

        try (Connection con = DriverManager.getConnection(System.getenv("connectionstring"),
                System.getenv("spring.datasource.username"),
                System.getenv("spring.datasource.password"));
             PreparedStatement stmt = con.prepareStatement(query)){

            stmt.setString(1, d.getDeviceName());
            stmt.setString(2, d.getDeviceHolder());
            stmt.setString(3, d.getTemperature());
            stmt.setString(4, d.getHumidity());
            stmt.setString(5, d.getRecordtime());
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

    public static List<DeviceEsp32Dht11> getAllDummy(){
        List<DeviceEsp32Dht11> devicelist = new ArrayList<>();
        try(
                Connection connection = DriverManager.getConnection(System.getenv("connectionstring"),
                        System.getenv("spring.datasource.username"),
                        System.getenv("spring.datasource.password"));
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery("select * from iot20.iotdevice order by recordtime desc");
        ){
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String deviceName = resultSet.getString("deviceName");
                String deviceHolder = resultSet.getString("deviceHolder");
                String temperature = resultSet.getString("temperature");
                String humidity = resultSet.getString("humidity");
                String recordtime = resultSet.getString("recordtime");
                devicelist.add(new DeviceEsp32Dht11(deviceName, deviceHolder, temperature, humidity, recordtime));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return devicelist;
    }

}
