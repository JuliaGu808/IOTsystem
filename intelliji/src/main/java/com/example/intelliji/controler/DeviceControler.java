package com.example.intelliji.controler;

import com.example.intelliji.dao.DeviceDao;
import com.example.intelliji.model.DeviceEsp32Dht11;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/DeviceDBService")
public class DeviceControler {

    private static DeviceDao deviceDao = new DeviceDao();

    @PostMapping("/data/add")
    public boolean addData(@RequestBody DeviceEsp32Dht11 d){
        System.out.println("add"+d);
        return deviceDao.addData(d);

    }

    @GetMapping("/all")
    public List<DeviceEsp32Dht11> getAll(){
        System.out.println("all");
        return DeviceDao.getAllDummy();
    }




}

