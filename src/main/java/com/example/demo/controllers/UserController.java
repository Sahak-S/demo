package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String login(Principal principal, ModelMap map) {
     //   map.addAttribute("user", userService.getUserByPrincipal(principal));
        return "login";
    }

//    @GetMapping("/profile")
//    public String profile(Principal principal,
//                          Model model) {
//        User user = userService.getUserByPrincipal(principal);
//        model.addAttribute("user", user);
//        return "profile";
//    }

    @GetMapping("/registration")
    public String registration(Principal principal, ModelMap map) {
        //model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "registration";
    }


    @PostMapping("/registration")
    public String createUser(User user, ModelMap map) {
        if (!userService.createUser(user)) {
            map.addAttribute("errorMessage", "Пользователь с email: " + user.getEmail() + " уже существует");
            return "registration";
        }
        return "redirect:/login";
    }

    @GetMapping("/user/{user}")
    public String userInfo(@PathVariable("user") User user, ModelMap map) {
        map.addAttribute("user", user);
        map.addAttribute("products", user.getProducts());
        return "user-info";
    }

    @GetMapping("/hello")
    public String securityUrl(){
        return "hello";
    }
}
