package com.vicras.codeanalyzerserver.controller.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hello")
public class HelloController {

    @GetMapping()
    public String sayHello() {
        return "hello world";
    }

    @GetMapping("/auth")
    public String sayHelloAuth(Principal user) {
        return "hello world, auth " + user.getName();
    }
}
