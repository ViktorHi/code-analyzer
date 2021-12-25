package com.vicras.codeanalyzerserver.repository;

import com.vicras.codeanalyzerserver.model.AnalyzerUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AnalyzerUserRepository extends JpaRepository<AnalyzerUser, Long> {
    Optional<AnalyzerUser> findByLogin(String email);
}