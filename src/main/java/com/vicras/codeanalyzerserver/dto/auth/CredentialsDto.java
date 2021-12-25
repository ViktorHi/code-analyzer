package com.vicras.codeanalyzerserver.dto.auth;

import com.vicras.codeanalyzerserver.dto.Dto;
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
public class CredentialsDto implements Dto {
    public String password;
    public String login;
}
