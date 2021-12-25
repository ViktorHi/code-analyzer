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
public class SignInDto implements Dto {
    private String name;
    private String surname;
    private CredentialsDto credentials;

    public String getLogin() {
        return credentials.getLogin();
    }

    public String getPassword() {
        return credentials.getPassword();
    }
}
