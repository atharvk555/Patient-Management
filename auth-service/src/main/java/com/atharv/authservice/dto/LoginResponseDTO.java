package com.atharv.authservice.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Getter
public class LoginResponseDTO {
    private final String token;
}
