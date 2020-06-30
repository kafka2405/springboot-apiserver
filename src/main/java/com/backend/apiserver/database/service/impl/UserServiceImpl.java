package com.backend.apiserver.database.service.impl;

import com.backend.apiserver.database.repository.UserRepository;
import com.backend.apiserver.database.service.UserService;
import com.backend.apiserver.entity.User;
import com.backend.apiserver.exception.DataDuplicatedException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);


    /**
     * UserRepository
     */
    private UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public User findByUsername(String username) throws UsernameNotFoundException {
        LOG.info("Start to find user by username: " + username);
        User user = userRepository.findByUsername(username);
        if (Objects.isNull(user)) {
            LOG.warn("Not found any user with given username");
            throw new UsernameNotFoundException("Not found any user with given username");
        }
        LOG.info("End to find user by username: " + username);
        return user;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void registerUser(User user) throws DataDuplicatedException {
        LOG.info("Start to register user by username: " + user.getUsername());
        User result = userRepository.findByUsername(user.getUsername());
        if (Objects.nonNull(result)) {
            LOG.warn("User with this username already exists: " + user.getUsername());
            throw new DataDuplicatedException("User with this username already exists: " + user.getUsername());
        }
        LOG.info("End to register user by username: " + user.getUsername());
        userRepository.saveAndFlush(user);
    }
}
