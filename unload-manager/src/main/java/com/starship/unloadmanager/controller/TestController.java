package com.starship.unloadmanager.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/test")
public class TestController {


    @GetMapping("/ping")
    public String unloadSpaceMarine() {
        return "pong";
    }

}
