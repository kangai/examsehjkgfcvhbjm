package com.jal.ws.main.controller;

import com.jal.ws.main.dao.CustomerRepository;
import com.jal.ws.main.entity.User;
import com.jal.ws.main.form.UserForm;
import org.h2.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by 0A8936 on 2015/04/13.
 */
@Controller
public class IndexController {

    @Autowired
    private CustomerRepository cr;

    @RequestMapping("/")
    public String index(Model model) {
        List<User> list = cr.findAllOrderByName();
        model.addAttribute("users", list);
        return "index";
    }

    @RequestMapping("/register")
    public String register(Model model, UserForm userForm) {
        User user = new User(userForm.getName(),userForm.getAge(),userForm.getAddress());
        cr.insert(user);
        // push here

        List<User> list = cr.findAllOrderByName();
        model.addAttribute("users", list);
        return "index";
    }

    @RequestMapping("/delete")
    public String delete(Model model, @RequestParam(value="id", required=false) String target) {
        if (StringUtils.isNumber(target)) {
            User u = new User(Integer.valueOf(target),null,null,null);
            cr.delete(u);
        }
        return "index";
    }
}
