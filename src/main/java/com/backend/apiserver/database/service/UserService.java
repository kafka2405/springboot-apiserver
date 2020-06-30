package com.backend.apiserver.database.service;

import com.backend.apiserver.entity.User;
import com.backend.apiserver.exception.DataDuplicatedException;

public interface UserService {

    /**
     * find a User with given username
     *
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * save a User to database
     *
     * @param user
     * @return
     */
    void registerUser(User user) throws DataDuplicatedException;
}
