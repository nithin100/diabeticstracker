package com.dharani.diabetestracker.rest;

import com.dharani.diabetestracker.entities.DTUserDailyData;
import com.dharani.diabetestracker.repo.UserDailyDataRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/data")
public class UserDailyDataController {

    @Autowired
    UserDailyDataRepo userDailyDataRepo;

    @GetMapping
    public List<DTUserDailyData> userDailyData(Principal principal){
        return this.userDailyDataRepo.findDTUserDailyDataByUserName(principal.getName());
    }

    @PostMapping
    public DTUserDailyData addDailyData(@RequestBody DTUserDailyData userDailyData, Principal principal){
        userDailyData.setUserName(principal.getName());
        return this.userDailyDataRepo.save(userDailyData);
    }
}
