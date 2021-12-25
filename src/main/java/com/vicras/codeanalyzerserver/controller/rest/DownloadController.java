package com.vicras.codeanalyzerserver.controller.rest;

import com.vicras.codeanalyzerserver.service.UploadService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import static java.lang.String.format;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;
import static org.springframework.http.MediaType.APPLICATION_OCTET_STREAM;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/download")
public class DownloadController {

    private final UploadService uploadService;

    @GetMapping("/document/{id}")
    ResponseEntity<Resource> downloadFile(@PathVariable Long id, Principal user) {
        var pair = uploadService.downloadFile(id, user);
        return ResponseEntity.ok()
                .contentLength(pair.getKey().contentLength())
                .header(CONTENT_DISPOSITION, format("attachment; filename=%s", pair.getValue().getTitle()))
                .contentType(APPLICATION_OCTET_STREAM)
                .body(pair.getKey());
    }
}
