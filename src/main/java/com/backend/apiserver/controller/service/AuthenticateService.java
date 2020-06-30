package com.backend.apiserver.controller.service;

import com.backend.apiserver.controller.bean.AuthBean;
import com.backend.apiserver.controller.bean.TokenInfoBean;
import org.springframework.security.core.AuthenticationException;

public interface AuthenticateService {

    /**
     *
     * @param authBean
     * @return
     * @throws AuthenticationException
     */
    TokenInfoBean passwordCredentials(AuthBean authBean) throws AuthenticationException;
}
