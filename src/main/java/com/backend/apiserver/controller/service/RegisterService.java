package com.backend.apiserver.controller.service;

import com.backend.apiserver.controller.bean.RegisterBean;
import com.backend.apiserver.exception.DataDuplicatedException;
import com.backend.apiserver.exception.NotFoundException;

public interface RegisterService {

    /**
     * Register new user with given information
     *
     * @param registerBean
     * @throws DataDuplicatedException
     * @throws NotFoundException
     */
    void registerUser(RegisterBean registerBean) throws DataDuplicatedException, NotFoundException;
}
