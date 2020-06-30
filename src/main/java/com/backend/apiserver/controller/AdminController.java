package com.backend.apiserver.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@Secured("ROLE_ADMIN")
public class AdminController {

    @GetMapping("/get")
    public String getContent() {
        return "Get Content.";
    }

    @PostMapping("/post")
    public String postContent() {
        return "Post Content.";
    }
}
