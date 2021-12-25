package com.vicras.codeanalyzerserver.dto.auth;

import com.vicras.codeanalyzerserver.dto.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import static com.vicras.codeanalyzerserver.constant.ValidationConstants.LESS_THEN_255_SYMBOLS_MESSAGE;
import static com.vicras.codeanalyzerserver.constant.ValidationConstants.NOT_BLANK_MESSAGE;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInDto implements Dto {

    @NotBlank(message = NOT_BLANK_MESSAGE)
    @Size(max = 255, message = LESS_THEN_255_SYMBOLS_MESSAGE)
    private String name;

    @NotBlank(message = NOT_BLANK_MESSAGE)
    @Size(max = 255, message = LESS_THEN_255_SYMBOLS_MESSAGE)
    private String surname;

    @NotNull(message = NOT_BLANK_MESSAGE)
    private CredentialsDto credentials;

    public String getLogin() {
        return credentials.getLogin();
    }

    public String getPassword() {
        return credentials.getPassword();
    }
}
