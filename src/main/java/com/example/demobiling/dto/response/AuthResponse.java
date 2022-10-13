package com.example.demobiling.dto.response;

import com.example.demobiling.entites.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class AuthResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private String jwt;
}