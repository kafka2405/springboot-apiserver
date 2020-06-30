package com.backend.apiserver.utils;

import com.backend.apiserver.configuration.CommonProperties;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@AllArgsConstructor
public class TokenProvider {

    /**
     * Logger
     */
    private static final Logger LOG = LoggerFactory.getLogger(TokenProvider.class);

    /**
     * CommonProperties
     */
    private CommonProperties commonProperties;

    /**
     * Using to generate JWT Token
     *
     * @param authentication
     * @return
     */
    public String generateToken(Authentication authentication) {

        LOG.info("Exact user principal from authentication");
        User userPrincipal = (User) authentication.getPrincipal();

        //Calculate expire time
        Date now = new Date();
        Date expireDate = new Date(now.getTime() + commonProperties.getExpiration());

        LOG.info("Start generate the jwt token with necessary information");
        String jwtToken = Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(now)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, commonProperties.getSecret())
                .compact();
        LOG.info("End generate the jwt token with necessary information");

        return jwtToken;
    }

    /**
     * Using to exact username from JWT token
     *
     * @param token
     * @return
     */
    public String getUsername(String token) {
        LOG.info("Start to exact username from jwt web token string");
        String username = Jwts.parser()
                .setSigningKey(commonProperties.getSecret())
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        LOG.info("End to exact username from jwt web token string");
        return username;
    }

    /**
     * Validate json web token string
     *
     * @param authToken
     * @return
     */
    public boolean validateToken(String authToken) {
        try {
            LOG.info("Start to validate json web token string");
            Jwts.parser()
                    .setSigningKey(commonProperties.getSecret())
                    .parseClaimsJws(authToken);
            LOG.info("Json web token string validated");
            return true;
        } catch (SignatureException e) {
            LOG.error("Invalid JWT signature: ", e.getMessage());
        } catch (MalformedJwtException e) {
            LOG.error("Invalid JWT token: ", e.getMessage());
        } catch (ExpiredJwtException e) {
            LOG.error("JWT token is expired: ", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOG.error("JWT token is unsupported: ", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOG.error("JWT claims string is empty: ", e.getMessage());
        }

        return false;
    }
}