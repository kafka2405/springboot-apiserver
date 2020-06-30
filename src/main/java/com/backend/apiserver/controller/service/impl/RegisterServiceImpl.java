package com.backend.apiserver.controller.service.impl;

import com.backend.apiserver.controller.bean.RegisterBean;
import com.backend.apiserver.controller.service.RegisterService;
import com.backend.apiserver.database.service.RoleService;
import com.backend.apiserver.database.service.UserService;
import com.backend.apiserver.entity.Role;
import com.backend.apiserver.entity.User;
import com.backend.apiserver.exception.DataDuplicatedException;
import com.backend.apiserver.exception.NotFoundException;
import com.backend.apiserver.utils.Constants;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class RegisterServiceImpl implements RegisterService {

    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(RegisterServiceImpl.class);

    /**
     * UserService
     */
    private UserService userService;

    /**
     * RoleService
     */
    private RoleService roleService;

    /**
     * PasswordEncoder
     */
    private PasswordEncoder passwordEncoder;

    /**
     * ModelMapper
     */
    private ModelMapper modelMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerUser(RegisterBean registerBean) throws DataDuplicatedException, NotFoundException {
        LOG.info("Start to register new user with registerBean information");
        User user = modelMapper.map(registerBean, User.class);

        //encrypt password with passwordEncoder (BCrypt)
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        //set default role is user
        Role role = roleService.findByName(Constants.ROLE_USER);

        Set<Role> roles = new HashSet<>(Arrays.asList(role));
        user.setRoles(roles);

        //perform save user to database
        userService.registerUser(user);
        LOG.info("End to register new user with registerBean information");
    }
}
