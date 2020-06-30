package com.backend.apiserver.database.repository;

import com.backend.apiserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * find a User with given username
     *
     * @param username
     * @return User
     */
    User findByUsername(String username);
}
