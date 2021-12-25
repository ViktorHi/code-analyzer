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
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.String.format;

@Component
@RequiredArgsConstructor
public class FilePerformer {

    private final FileProvider fileProvider;

    @PostConstruct
    public void tryToCreateFileFolder() {
        fileProvider.createDirectory();
    }

    public String findFile(String fileName) {
        Path path = getFilePath(fileName);
        return readFromFile(path);
    }

    public ByteArrayResource findInputStream(String fileName) {
        Path path = getFilePath(fileName);
        try {
            return new ByteArrayResource(Files.readAllBytes(path));
        } catch (IOException e) {
            throw new BusinessException(e);
        }
    }

    public void writeFile(String fileName, String date) {
        Path path = getFilePath(fileName);
        writeToFile(path, date);
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

    public void deleteFile(String filename) {
        Path path = getFilePath(filename);
        deleteFile(path);
    }

    private Path getFilePath(String fileName) {
        final Path path = fileProvider.filePathProvider(fileName);
        if (!path.toFile().isFile()) {
            throw new BusinessException(format("File %s not found", fileName));
        }
        return path;
    }

    private void deleteFile(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new BusinessException(e);
        }
    }

    private void writeToFile(Path file, String date) {
        try {
            file.toFile().createNewFile();
            Files.write(file, List.of(date), StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new BusinessException(e);
        }
    }

    private String readFromFile(Path path) {
        try (Stream<String> lines = Files.lines(path)) {
            return lines.collect(Collectors.joining("\n"));
        } catch (
                IOException e) {
            throw new BusinessException(e);
        }
    }
}

