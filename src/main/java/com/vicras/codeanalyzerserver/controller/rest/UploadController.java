package com.vicras.codeanalyzerserver.controller.rest;

import com.vicras.codeanalyzerserver.dto.DocumentResponseDto;
import com.vicras.codeanalyzerserver.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/upload")
public class UploadController {

    private final UploadService uploadService;

    @PostMapping("/archive")
    public DocumentResponseDto fromArchive(@RequestParam("file") MultipartFile file,
                                           Principal user) {
        return uploadService.fromArchive(file, user);
    }

    @PostMapping("/url")
    public DocumentResponseDto fromUrl(@RequestParam String link,
                                       Principal user) {
        return uploadService.fromUrl(link, user);
    }
}
