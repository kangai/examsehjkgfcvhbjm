package com.jal.ws.main.controller;

import com.jal.ws.main.dao.CustomerRepository;
import com.jal.ws.main.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.NumberFormat;

/**
 * Created by 0A8936 on 2015/04/07.
 */
@Controller
public class HelloController {

    @Autowired
    private CustomerRepository cr;

    @RequestMapping("/hello")
    public String hello(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        User user = cr.findById(1);
        System.out.println(user);
        return "hello";
    }

    @RequestMapping("/input")
    public String input() {
        return "input"; // input form
    }

    @RequestMapping("/send")
    public String send(Model model, @RequestParam("name") String name) {
        model.addAttribute("name", name);
        Integer num = null;
        try {
            num = Integer.valueOf(name);
            User user = cr.findById(num);
            System.out.println(user);
        } catch (NumberFormatException nfe) {
            System.out.println("nfe is not number ");
        }
        return "hello";
    }

}

