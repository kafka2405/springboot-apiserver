package com.backend.apiserver.controller.service.impl;

import com.backend.apiserver.controller.bean.AuthBean;
import com.backend.apiserver.controller.bean.TokenInfoBean;
import com.backend.apiserver.controller.service.AuthenticateService;
import com.backend.apiserver.utils.TokenProvider;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthenticateServiceImpl implements AuthenticateService {

    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticateServiceImpl.class);

    /**
     * TokenProvider
     */
    private TokenProvider tokenProvider;

    /**
     * AuthenticationManager
     */
    private AuthenticationManager authenticationManager;

    @Override
    public TokenInfoBean passwordCredentials(AuthBean authBean) throws AuthenticationException {

        LOG.info("Start validate username and password and generate jwt token");

        // authenticate username and password
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authBean.getUsername(), authBean.getPassword()
                )
        );

        // if there's not exception thrown, mean that user information is valid
        // set authentication information to Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //generate jwt token after user valid
        String jwt = tokenProvider.generateToken(authentication);

        //get information from UserDetailService
        User user = (User) authentication.getPrincipal();

        //Get all the roles of user
        Set<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        //create response entity
        TokenInfoBean tokenInfoBean = TokenInfoBean.builder()
                .username(user.getUsername())
                .token(jwt)
                .roles(roles)
                .build();

        LOG.info("Start validate username and password and generate jwt token");

        return tokenInfoBean;
    }
}
