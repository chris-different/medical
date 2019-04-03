package com.medical.medical.controller;

import com.medical.medical.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Controller
public class loginController {

    private static Logger logger = LoggerFactory.getLogger(loginController.class);

    @Autowired
    UserService userService;

//注册路由
    @RequestMapping(path = {"/reg"},method = {RequestMethod.POST})
    public String reg(Model model,
                      @RequestParam("username") String username,
                      @RequestParam("password") String password,
                      @RequestParam(value = "next",required = false) String next){
        try {
            Map<String,String> map = userService.register(username,password);
            if (map.containsKey("msg")){
                model.addAttribute("msg",map.get("msg"));
                return "login";
            }
            if (StringUtils.isNotBlank(next)) {
                return "redirect:"+next;
            }else {
                return "redirect:/";
            }
        }catch (Exception e){
            logger.error("注册异常");
            return "login";
        }

    }
//登录路由
    @RequestMapping(value = "/login",method = {RequestMethod.POST})
    public String login(Model model,
                        @RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam(value = "next",required = false) String next,
                        @RequestParam(value = "rememberme",defaultValue = "false") boolean rememberme
                        ,HttpServletResponse httpServletResponse){

        try{

            Map<String,String> map = userService.login(username,password);

            if (map.containsKey("ticket")){
                Cookie cookie = new Cookie("ticket",map.get("ticket"));
                cookie.setPath("/");

                httpServletResponse.addCookie(cookie);
                if (StringUtils.isNotBlank(next)){
                    return "redirect:"+next;
                }
                return "redirect:/";
            }



            return "login";

        }catch (Exception e){
            logger.error("登陆异常");
            return "login";
        }

    }

//跳转登录页面
    @RequestMapping(value = "/reglogin",method ={RequestMethod.GET} )
    public String reglogin(Model model,
                           @RequestParam(value = "next",required = false) String next){
        model.addAttribute("next",next);
        return "login";
    }


//登出路由
    @RequestMapping(value = "/logout")
    public String logout(@CookieValue("ticket") String ticket){
        userService.logout(ticket);
        return "redirect:/reglogin";
    }

}
