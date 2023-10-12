package com.dharani.diabetestracker.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UiController {


    @GetMapping(value = "/ui")
    public String redirect() {
        System.out.println("okay now!");
        return "forward:/login";
    }
}
