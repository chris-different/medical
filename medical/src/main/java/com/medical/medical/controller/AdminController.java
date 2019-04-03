package com.medical.medical.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    @RequestMapping(path = {"/admin"})
    public String admin(){
        return "admin";
    }
}
