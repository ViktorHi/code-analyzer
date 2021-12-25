package com.vicras.codeanalyzerserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponseDto {
    private Long id;

    private String name;
    private String surname;
    private String login;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
