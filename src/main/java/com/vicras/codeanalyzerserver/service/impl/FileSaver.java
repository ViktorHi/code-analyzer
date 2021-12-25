package com.vicras.codeanalyzerserver.service.impl;

import com.vicras.codeanalyzerserver.exception.exceptions.business.BusinessException;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class FileSaver {

    private final FileNamer fileProvider;

    @PostConstruct
    public void tryToCreateFileFolder() {
        fileProvider.createDirectory();
    }

    public ByteArrayResource findInputStream(String fileName) {
        Path path = getFilePath(fileName);
        try {
            return new ByteArrayResource(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new BusinessException(e);
        }
    }

    public Path fromMultipartFile(MultipartFile file) {
        try {
            var newFile = fileProvider.newEmptyFile();
            file.transferTo(newFile);
            return newFile;
        } catch (IOException e) {
            throw new BusinessException(e);
        }
    }

    private Path getFilePath(String fileName) {
        final Path path = fileProvider.filePathProvider(fileName);
        if (!path.toFile().isFile()) {
            throw new BusinessException(format("File %s not found", fileName));
        }
        return path;
    }
}

