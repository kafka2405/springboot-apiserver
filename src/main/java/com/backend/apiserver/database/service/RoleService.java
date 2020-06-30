package com.backend.apiserver.database.service;

import com.backend.apiserver.entity.Role;
import com.backend.apiserver.exception.DataDuplicatedException;
import com.backend.apiserver.exception.NotFoundException;

public interface RoleService {

    /**
     * find a Role by roleName
     *
     * @param name
     * @return
     * @throws NotFoundException
     */
    Role findByName(String name) throws NotFoundException;

    /**
     * save a new Role to database
     *
     * @param role
     * @throws DataDuplicatedException
     */
    void saveRole(Role role) throws DataDuplicatedException;
}
