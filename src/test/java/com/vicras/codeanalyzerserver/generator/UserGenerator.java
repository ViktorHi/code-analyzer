package com.vicras.codeanalyzerserver.generator;

import com.vicras.codeanalyzerserver.model.AnalyzerUser;

import java.time.LocalDateTime;

public class UserGenerator {
    public static AnalyzerUser getUser(){
        var user = new AnalyzerUser();
        user.setLogin("login");
        user.setId(1L);
        user.setCreatedAt(LocalDateTime.of(2021,12,12,12,0));
        user.setUpdatedAt(LocalDateTime.of(2021,12,12,12,0));
        user.setPassword("password");
        user.setName("name");
        user.setSurname("surname");
        return user;
    }

    public static AnalyzerUser getUser2(){
        var user = new AnalyzerUser();
        user.setLogin("login");
        user.setId(2L);
        user.setCreatedAt(LocalDateTime.of(2021,12,12,12,0));
        user.setUpdatedAt(LocalDateTime.of(2021,12,12,12,0));
        user.setPassword("password");
        user.setName("name");
        user.setSurname("surname");
        return user;
    }
}
