package com.example.demo.register;

import com.example.demo.exceptions.UserAlreadyRegisteredException;
import com.example.demo.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String register(ModelMap map) {
        map.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String processRegister(RegisterForm rf, ModelMap map) {
        try {
            userService.registerUser(rf);
            return "redirect:/login";
        } catch (UserAlreadyRegisteredException e) {
            map.addAttribute("registerError", "Registration failed!");
            return "register";
        }
    }
}
