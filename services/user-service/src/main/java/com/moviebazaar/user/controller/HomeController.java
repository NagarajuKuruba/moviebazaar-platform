package com.moviebazaar.user.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@Slf4j
@RestController
@RequestMapping("/api/v1/home")
public class HomeController {

    @Value("${custom.message}")
    private String customMessage;

    @RequestMapping("/welcome")
    public String welcome() {
        log.info("Received request for welcome endpoint. {}", customMessage);
        return "Welcome to Movie Bazaar User Service!";
    }
}
