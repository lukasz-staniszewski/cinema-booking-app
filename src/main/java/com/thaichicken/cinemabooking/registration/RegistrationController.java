package com.thaichicken.cinemabooking.registration;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    public String register(@RequestBody RegistrationRequest request) {

        log.info("Client with email " + request.getEmail() + " has been trying to register himself");
        return registrationService.register(request);
    }

    @GetMapping(path = "confirm")
    public String confirm(@RequestParam("token") String token) {

        log.info("Client with registration token: " + token + " has register");
        return registrationService.confirmToken(token);
    }

}


