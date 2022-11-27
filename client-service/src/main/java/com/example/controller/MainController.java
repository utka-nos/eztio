package com.example.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MainController {

    @GetMapping("/")
    public String welcomePage(
            HttpServletRequest request
    ) {
        return String.format("Welcome to client-service! Remote ip is %s", request.getRemoteAddr());
    }
}
