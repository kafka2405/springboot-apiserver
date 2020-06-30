package com.backend.apiserver.exception.handler;

import com.backend.apiserver.exception.BadRequestException;
import com.backend.apiserver.exception.ForbiddenException;
import com.backend.apiserver.exception.InternalServerException;
import com.backend.apiserver.exception.UnauthorizedException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Class define return httpStatus when exception is thrown
 */
@RestControllerAdvice
public class ApiExceptionHandler {

    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(ApiExceptionHandler.class);

    /**
     * All the unhandled exception is handled here
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public String exceptionHandler(Exception e) {
        LOG.warn(e.getMessage());
        return StringUtils.EMPTY;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(InternalServerException.class)
    @ResponseBody
    public String internalServerExceptionHandler(InternalServerException e) {
        LOG.warn(e.getMessage());
        return StringUtils.EMPTY;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BadRequestException.class)
    @ResponseBody
    public String badRequestExceptionHandler(BadRequestException e) {
        LOG.info(e.getMessage());
        return StringUtils.EMPTY;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(AuthenticationException.class)
    @ResponseBody
    public String authenticationExceptionHandler(AuthenticationException e) {
        LOG.info(e.getMessage());
        return StringUtils.EMPTY;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(ForbiddenException.class)
    @ResponseBody
    public String forbiddenExceptionHandler(ForbiddenException e) {
        LOG.warn(e.getMessage());
        return StringUtils.EMPTY;
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public String unauthorizedExceptionHandler(UnauthorizedException e) {
        LOG.info(e.getMessage());
        return StringUtils.EMPTY;
    }

}
