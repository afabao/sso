package com.afabao.itdragon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: 啊发包
 * @Date: 2019/07/29 2019-07-29
 */
@Controller
public class PageController {

    @RequestMapping(value = "/login")
    public String showLogin(String redirect, Model model){
        model.addAttribute("redirect",redirect);
        return "login";
    }
}
