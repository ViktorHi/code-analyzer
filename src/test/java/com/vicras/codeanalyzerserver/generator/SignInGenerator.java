package com.vicras.codeanalyzerserver.generator;

import com.vicras.codeanalyzerserver.dto.auth.CredentialsDto;
import com.vicras.codeanalyzerserver.dto.auth.SignInDto;

public class SignInGenerator {
    public static SignInDto getSignInDto(){
        var cred = getCredentialsDto();
        return new SignInDto("name", "surname", cred);
    }

    public static CredentialsDto getCredentialsDto() {
        return new CredentialsDto("password", "login");
    }
}
