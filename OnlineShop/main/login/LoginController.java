package com.example.demo.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login(ModelMap map) {
        map.addAttribute("loginForm", new LoginForm());
        return "login";
    }
}
