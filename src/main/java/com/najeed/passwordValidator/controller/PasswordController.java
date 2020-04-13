package com.najeed.passwordValidator.controller;

import com.najeed.passwordValidator.service.PasswordValidatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
public class PasswordController {

    @Autowired
    PasswordValidatorService service;

    @GetMapping("/password/check")
    @ResponseBody
    public ArrayList<String> validatePassword(@RequestParam String password) {
        return service.validatePassword(password);
    }

}
