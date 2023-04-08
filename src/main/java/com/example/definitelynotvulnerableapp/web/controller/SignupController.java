package com.example.definitelynotvulnerableapp.web.controller;

import com.example.definitelynotvulnerableapp.domain.model.UserData;
import com.example.definitelynotvulnerableapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.UUID;

@Controller
class SignupController {

    private static final String SIGNUP_VIEW_NAME = "signup/signup";

    @Autowired
    private UserService userService;

    @GetMapping("signup")
    String signup(Model model) {
        model.addAttribute(new SignupForm());
        return SIGNUP_VIEW_NAME;
    }

    @PostMapping("signup")
    public String signup(@Valid @ModelAttribute SignupForm signupForm, Errors errors, RedirectAttributes ra) {
        if (errors.hasErrors()) {
            return SIGNUP_VIEW_NAME;
        }
        userService.createUser(UserData.builder()
                .id(UUID.randomUUID())
                .name(signupForm.getName())
                .password(signupForm.getPassword())
                .role("user")
                .build()
        );
        return "redirect:/";
    }
}