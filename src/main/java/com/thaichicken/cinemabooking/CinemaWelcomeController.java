package com.thaichicken.cinemabooking;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CinemaWelcomeController {
    private static final String template = "Welcome to the cinema: %s!";

    @GetMapping("/cinema_client")
    public CinemaWelcome welcomeCinemaClient(@RequestParam(value = "client_id", defaultValue = "PIS") String name) {
        return new CinemaWelcome(String.format(template, name));
    }
}
