package com.dharani.diabetestracker.rest;

import com.dharani.diabetestracker.entities.SecurityImage;
import com.dharani.diabetestracker.repo.SecurityImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/data")
public class DataController {

    @Autowired
    private SecurityImageRepo imageRepo;

    @GetMapping("/securityimages")
    public List<SecurityImage> imagesList(){
        return imageRepo.findAll();
    }
}
