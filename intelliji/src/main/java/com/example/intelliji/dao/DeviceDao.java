package com.example.intelliji.dao;

import com.example.intelliji.model.DeviceEsp32Dht11;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import java.io.FileInputStream;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.Date;

public class DeviceDao {

    public static boolean addData(DeviceEsp32Dht11 d){
        int holderId=0, deviceId=0;
        String holderQuery = "insert into holders (name) values (?)";
        String holderIdQuery = "select id from holders where name=?";
        String deviceQuery = "insert into devices (name, holdersId) values (?,?)";
        String deviceIdQuery = "select id from devices where holdersId=? and name=?";
        String recordQuery = "insert into records (temperature, humidity, recordtime, devicesId) values (?, ?, ?, ?)";

        int rowChanged = 0;

        try (Connection con = DriverManager.getConnection(System.getenv("connectionstring"),
                System.getenv("spring.datasource.username"),
                System.getenv("spring.datasource.password"));
             PreparedStatement holder_stmt = con.prepareStatement(holderQuery);
             PreparedStatement holderId_stmt = con.prepareStatement(holderIdQuery);
             PreparedStatement device_stmt = con.prepareStatement(deviceQuery);
             PreparedStatement deviceId_stmt = con.prepareStatement(deviceIdQuery);
             PreparedStatement record_stmt = con.prepareStatement(recordQuery);
             ){

            holder_stmt.setString(1, d.getDeviceHolder());
            try{
                holder_stmt.executeUpdate();
            }catch (Exception e){}
            holderId_stmt.setString(1, d.getDeviceHolder());
            ResultSet holderIdresult = holderId_stmt.executeQuery();
            while (holderIdresult.next()) {
                holderId = holderIdresult.getInt("id");
            }
            device_stmt.setString(1, d.getDeviceName());
            device_stmt.setInt(2, holderId);
            try{
                device_stmt.executeUpdate();
            }catch (Exception e){}
            deviceId_stmt.setInt(1, holderId);
            deviceId_stmt.setString(2, d.getDeviceName());
            ResultSet deviceIdresult = deviceId_stmt.executeQuery();
            while (deviceIdresult.next()) {
                deviceId = deviceIdresult.getInt("id");
            }
            record_stmt.setString(1, d.getTemperature());
            record_stmt.setString(2, d.getHumidity());
            record_stmt.setString(3, d.getRecordtime());
            record_stmt.setInt(4, deviceId);
            rowChanged = record_stmt.executeUpdate();
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
                ResultSet resultSet = statement.executeQuery("select * from get_all");
        ){
            while (resultSet.next()){
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

    public static DeviceEsp32Dht11 DeviceConvert(Map map){
            String deviceHolder = String.valueOf(map.get("deviceHolder"));
            String deviceName = String.valueOf(map.get("deviceName"));
            String temperature = String.valueOf(map.get("temperature"));
            String humidity = String.valueOf(map.get("humidity"));
            String recordtime = String.valueOf(map.get("recordtime"));
            return new DeviceEsp32Dht11(deviceHolder, deviceName, temperature, humidity, recordtime);
    }

}
