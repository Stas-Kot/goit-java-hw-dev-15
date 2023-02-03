package com.goit.feature.mvc.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class SecurityController {
    @GetMapping("/login")
    String getLogin() {
        return "notes/login";
    }

//    @RequestMapping("/login-error")
//    public String loginError(Model model) {
//        model.addAttribute("loginError", true);
//        return "notes/list";
//    }
}
