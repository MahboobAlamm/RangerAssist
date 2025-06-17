package com.pickassist.pao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class Ordercontroller {

    //TBD: Remove when project will be ready
    @GetMapping("/")
    public String requestMethodName() {
        return "Home API hit on 8080: Project is up and running.";
    }
    

}
