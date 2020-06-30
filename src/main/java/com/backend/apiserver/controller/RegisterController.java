package com.backend.apiserver.controller;

import com.backend.apiserver.controller.bean.RegisterBean;
import com.backend.apiserver.controller.service.RegisterService;
import com.backend.apiserver.exception.BadRequestException;
import com.backend.apiserver.exception.DataDuplicatedException;
import com.backend.apiserver.exception.NotFoundException;
import com.backend.apiserver.response.Response;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class RegisterController {

    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

    /**
     * RegisterService
     */
    private RegisterService registerService;

    /**
     * Register new user with given register bean
     *
     * @param registerBean
     * @return
     * @throws BadRequestException
     */
    @RequestMapping(value = "api/register", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public void register(@Valid @RequestBody final RegisterBean registerBean) throws BadRequestException {
        try {
            LOG.info("Start to register new user " + registerBean.getUsername());
            registerService.registerUser(registerBean);
            LOG.info("Start to register new user " + registerBean.getUsername());
        } catch (DataDuplicatedException | NotFoundException e) {
            LOG.warn("Register new user failed: " + registerBean.getUsername());
            throw new BadRequestException("Register new user failed: " + registerBean.getUsername());
        }
    }
}
