package com.example.demobiling.api;

import com.example.demobiling.dto.request.AuthRequest;
import com.example.demobiling.dto.request.RegisterRequest;
import com.example.demobiling.dto.response.AuthResponse;
import com.example.demobiling.service.AuthInfoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/auth")
@CrossOrigin
public class AuthApi {

    private final AuthInfoService authInfoService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest registerRequest) {
        return authInfoService.register(registerRequest);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest authRequest) {
        return authInfoService.login(authRequest);
    }
}
