package com.example.demobiling.security;

import com.example.demobiling.service.AuthInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationManagerImpl implements AuthenticationManager {

    private final PasswordEncoder passwordEncoder;

    private final AuthInfoService authInfoService;

    @Autowired
    public AuthenticationManagerImpl(PasswordEncoder passwordEncoder, AuthInfoService authInfoService) {
        this.passwordEncoder = passwordEncoder;
        this.authInfoService = authInfoService;
    }


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        UserDetails userDetails = authInfoService.loadUserByUsername(authentication.getName());

        if (!passwordEncoder.matches(authentication.getCredentials().toString(), userDetails.getPassword())) {
            throw new BadCredentialsException("bad credentials");
        }

        return new UsernamePasswordAuthenticationToken(
                userDetails.getUsername(), null, userDetails.getAuthorities());
    }
}
