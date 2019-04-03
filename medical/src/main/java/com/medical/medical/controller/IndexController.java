package com.medical.medical.controller;

import com.medical.medical.model.HostHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

    @Autowired
    HostHolder hostHolder;

    @RequestMapping(path = {"/","/index"})
    public String index(Model model){
        model.addAttribute("vo","123");
//        return "index";
        if (hostHolder.getUser()!=null){
            return "index";
        }else{
            return "front";
        }
    }
}
