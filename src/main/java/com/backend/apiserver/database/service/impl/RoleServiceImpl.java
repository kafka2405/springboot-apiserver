package com.backend.apiserver.database.service.impl;

import com.backend.apiserver.database.repository.RoleRepository;
import com.backend.apiserver.database.service.RoleService;
import com.backend.apiserver.entity.Role;
import com.backend.apiserver.exception.DataDuplicatedException;
import com.backend.apiserver.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * RoleRepository
     */
    private RoleRepository repository;

    /**
     *{@inheritDoc}
     */
    @Override
    public Role findByName(String name) throws NotFoundException {
        LOG.info("Start to find user by name: " + name);
        Role role = repository.findByName(name);
        if (Objects.isNull(role)) {
            LOG.warn("Not found role with name: " + name);
            throw new NotFoundException("Not found role with name: " + name);
        }
        LOG.info("Start to find user by name: " + name);
        return role;
    }

    /**
     *{@inheritDoc}
     */
    @Override
    public void saveRole(Role role) throws DataDuplicatedException {
        LOG.info("Start to save new role : " + role.getName());
        Role result = repository.findByName(role.getName());
        if (Objects.nonNull(result)) {
            LOG.warn("Not found role with name: " + role.getName());
            throw new DataDuplicatedException("Not found role with name: " + role.getName());
        }
        repository.saveAndFlush(role);
        LOG.info("End save new role : " + role.getName());
    }
}
