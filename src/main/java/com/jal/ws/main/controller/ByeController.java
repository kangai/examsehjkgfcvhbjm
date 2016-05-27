package com.jal.ws.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by 0A8936 on 2015/04/07.
 * To say bye-bay
 */
@Controller
public class ByeController {
    @RequestMapping("/bye")
    public String bye(@RequestParam(value="name", required=false, defaultValue="World") String name, Model model) {
        model.addAttribute("name", name);
        return "bye";
    }
}
