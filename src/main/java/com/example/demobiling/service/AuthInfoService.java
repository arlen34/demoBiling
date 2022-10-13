package com.example.demobiling.service;

import com.example.demobiling.dto.request.AuthRequest;
import com.example.demobiling.dto.request.RegisterRequest;
import com.example.demobiling.dto.response.AuthResponse;
import com.example.demobiling.entites.AuthInfo;
import com.example.demobiling.entites.Client;
import com.example.demobiling.entites.enums.Role;
import com.example.demobiling.exceptions.BadCredentialsException;
import com.example.demobiling.exceptions.BadRequestException;
import com.example.demobiling.exceptions.NotFoundException;
import com.example.demobiling.repository.AuthInfoRepository;
import com.example.demobiling.security.jwt.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthInfoService implements UserDetailsService {

    private final AuthInfoRepository repository;

    private final JwtTokenUtil jwtTokenUtil;

    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return (UserDetails) repository.findByEmail(email).orElseThrow(
                () -> new NotFoundException(
                        String.format("user with email - %s not found",email)));
    }

    public AuthResponse register(RegisterRequest registerRequest) {

        if (registerRequest.getPassword().isBlank()){
            throw new BadRequestException("password can not be empty!");
        }

        if(repository.existsByEmail(registerRequest.getEmail())) {
            throw new BadRequestException("this email: " + registerRequest.getEmail() + " is already in use!");
        }

        Client client = convertToRegisterEntity(registerRequest);

        client.getAuthInfo().setPassword(passwordEncoder.encode(registerRequest.getPassword()));

        client.getAuthInfo().setRole(Role.CLIENT);

        repository.save(client.getAuthInfo());

        String jwt = jwtTokenUtil.generateToken(client.getAuthInfo().getEmail());

        return new AuthResponse(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getAuthInfo().getEmail(),
                client.getAuthInfo().getRole(),
                jwt
        );
    }

    public AuthResponse login(AuthRequest authRequest) {

        if (authRequest.getPassword().isBlank()) {
            throw new BadRequestException("password can not be empty!");
        }

        Client client = repository.findByEmail(authRequest.getEmail()).orElseThrow(() -> new NotFoundException("user with this email: " + authRequest.getEmail() + " not found!"));

        if (!passwordEncoder.matches(authRequest.getPassword(), client.getAuthInfo().getPassword())) {
            throw new BadCredentialsException("incorrect password");
        }

        String jwt = jwtTokenUtil.generateToken(client.getAuthInfo().getEmail());

        return new AuthResponse(
                client.getId(),
                client.getFirstName(),
                client.getLastName(),
                client.getAuthInfo().getEmail(),
                client.getAuthInfo().getRole(),
                jwt
        );
    }

    public Client convertToRegisterEntity(RegisterRequest registerRequest)  {

        return Client.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .authInfo(AuthInfo.builder()
                        .email(registerRequest.getEmail())
                        .password(registerRequest.getPassword())
                        .build())
                .build();

    }
}
