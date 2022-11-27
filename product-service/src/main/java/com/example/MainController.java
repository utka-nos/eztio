package com.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class MainController {

    @GetMapping("/")
    public String main(
            HttpServletRequest request
    ) {
        String remoteAddr = request.getRemoteAddr();
        return String.format("welcome to product-service! Our remote addr is %s", remoteAddr);
    }

}
