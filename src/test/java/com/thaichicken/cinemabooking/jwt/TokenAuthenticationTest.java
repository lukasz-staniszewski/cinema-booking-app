package com.thaichicken.cinemabooking.jwt;

import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.crypto.SecretKey;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class TokenAuthenticationTest {

    @Autowired
    private MockMvc mvc;

    @Value("${application.jwt.secretKey}")
    private String secret;

    @Value("${application.jwt.tokenPrefix}")
    private String prefix;

    @Value("${application.jwt.tokenExpirationAfterDays}")
    private Integer expiration;

    @Test
    public void shouldNotAllowAccessToUnauthenticatedUsers() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/cinema_halls")).andExpect(status().isForbidden());
    }

    @Test
    public void shouldGenerateAuthTokenAndAccessAuthenticated() throws Exception {
        JwtConfig jwtConfig = new JwtConfig();
        jwtConfig.setSecretKey(secret);
        jwtConfig.setTokenPrefix(prefix);
        jwtConfig.setTokenExpirationAfterDays(expiration);
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority("USER");
        String token = JwtUsernameAndPasswordAuthenticationFilter.createToken("Jaskier", Collections.singletonList(authority), jwtConfig, secretKey);

        assertNotNull(token);
        mvc.perform(MockMvcRequestBuilders.get("/cinema_halls").header("Authorization", prefix + " " + token)).andExpect(status().isOk());
    }

    @Test
    public void shouldGenerateAuthTokenAndNotAccessUser() throws Exception {
        JwtConfig jwtConfig = new JwtConfig();
        jwtConfig.setSecretKey(secret);
        jwtConfig.setTokenPrefix(prefix);
        jwtConfig.setTokenExpirationAfterDays(expiration);
        SecretKey secretKey = Keys.hmacShaKeyFor(jwtConfig.getSecretKey().getBytes());
        SimpleGrantedAuthority authority =
                new SimpleGrantedAuthority("USER");
        String token = JwtUsernameAndPasswordAuthenticationFilter.createToken("Jaskier", Collections.singletonList(authority), jwtConfig, secretKey);

        assertNotNull(token);
        mvc.perform(MockMvcRequestBuilders.get("/movies").header("Authorization", prefix + " " + token)).andExpect(status().isForbidden());
    }

}
