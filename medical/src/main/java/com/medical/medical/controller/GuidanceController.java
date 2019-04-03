package com.medical.medical.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import sun.misc.Request;

@Controller
public class GuidanceController {
    @RequestMapping(path={"/guidance"})
    public String guidance(){
        return "guidance";
    }
}
