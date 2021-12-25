package com.vicras.codeanalyzerserver.service.impl;

import com.vicras.codeanalyzerserver.exception.exceptions.business.BusinessException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Path;
import java.util.UUID;

@Component
public class FileNamer {

    private static final String SERVER_LIB_FOLDER = "server_lib";
    private static final String SERVER_FILES_FOLDER = "files";

    public void createDirectory() {
        Path.of(System.getProperty("user.home"), SERVER_LIB_FOLDER, SERVER_FILES_FOLDER).toFile().mkdirs();
    }

    public Path filePathProvider(String name) {
        return Path.of(System.getProperty("user.home"), SERVER_LIB_FOLDER, SERVER_FILES_FOLDER, name);
    }

    public String fileNameGenerator() {
        return UUID.randomUUID().toString();
    }

    public Path newEmptyFile() {
        try {
            var fileName = fileNameGenerator();
            var path = filePathProvider(fileName);
            path.toFile().createNewFile();
            return path;
        } catch (IOException e) {
            throw new BusinessException(e);
        }
    }
}