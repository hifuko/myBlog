package com.ivana.web.admin;

import com.ivana.pojo.User;
import com.ivana.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    UserService service;

    @GetMapping
    public String toLogin(){
        return "../admin/login";
    }

    @PostMapping("/login") //@RequestParam: param here matches the param we get from user input
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user = service.checkUser(username, password);
        if (user != null){
            user.setPassword(null); //don't store password, not safe
            session.setAttribute("user", user);
            return "../admin/indexAfterLogin";
        } else {
            attributes.addFlashAttribute("message", "Wrong username or password.");
            //here we can not use Model, cuz it can't send data when we use redirect
            return "redirect:/admin";
        }

    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "redirect:/admin";
    }
}
