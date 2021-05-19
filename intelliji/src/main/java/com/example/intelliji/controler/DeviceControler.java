package com.example.intelliji.controler;

import com.example.intelliji.dao.DeviceDao;
import com.example.intelliji.model.DeviceEsp32Dht11;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/DeviceDBService")
public class DeviceControler {


    @PostMapping("/data/add")
    public boolean addData(@RequestBody DeviceEsp32Dht11 d){
        return DeviceDao.addData(d);
    }

    @GetMapping("/all")
    public List<DeviceEsp32Dht11> getAll(){
        return DeviceDao.getAllDummy();
    }




}

