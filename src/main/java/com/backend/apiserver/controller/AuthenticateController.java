package com.backend.apiserver.controller;

import com.backend.apiserver.controller.bean.AuthBean;
import com.backend.apiserver.controller.bean.TokenInfoBean;
import com.backend.apiserver.controller.service.AuthenticateService;
import com.backend.apiserver.exception.BadRequestException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class AuthenticateController {

    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(AuthenticateController.class);

    /**
     * AuthenticateService
     */
    private AuthenticateService authenticateService;

    /**
     * Authenticate user and return generated access token
     *
     * @param authBean
     * @return
     */
    @RequestMapping(value = "/api/auth", method = RequestMethod.POST)
    public TokenInfoBean authenticate(@Valid @RequestBody final AuthBean authBean) {
        LOG.info("Start validate username and password and generate jwt token");
        TokenInfoBean tokenInfoBean = authenticateService.passwordCredentials(authBean);
        LOG.info("End validate username and password and generate jwt token");
        return tokenInfoBean;
    }
}
