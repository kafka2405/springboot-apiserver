package com.backend.apiserver.controller.bean;

import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class TokenInfoBean {
    private String token;
    private String username;
    private Set<String> roles;
}
