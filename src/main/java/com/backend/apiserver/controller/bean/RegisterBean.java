package com.backend.apiserver.controller.bean;

import lombok.Data;

@Data
public class RegisterBean {
    private String username;
    private String password;
    private String email;
    private String fullName;
}
