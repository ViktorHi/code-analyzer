package com.vicras.codeanalyzerserver.dto.auth;


import com.vicras.codeanalyzerserver.dto.UserResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthTokenDto {
    private String token;
    private UserResponseDto user;
}
