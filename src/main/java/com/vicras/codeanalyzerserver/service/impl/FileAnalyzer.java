package com.vicras.codeanalyzerserver.service.impl;

import com.company.ArchitectureStatistic;
import com.vicras.codeanalyzerserver.exception.exceptions.business.BusinessException;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.concurrent.Callable;

import static com.company.AnalyzerApiImpl.instance;

@Component
public class FileAnalyzer {
    public ArchitectureStatistic fromUrl(String url) {
        return handleException(() -> instance().analyzeGitRepo(url));
    }

    public ArchitectureStatistic fromFile(File file) {
        return handleException(() -> instance().analyzeArchive(file));
    }

    private ArchitectureStatistic handleException(Callable<ArchitectureStatistic> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            throw new BusinessException(e.getMessage());
        }
    }
}
