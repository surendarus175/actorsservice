package com.springboot.actorsservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/actors")
public class ActorServiceController {
    @GetMapping("/")
    public String index () {
        return "Actor Service";
    }
}