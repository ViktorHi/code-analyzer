package com.vicras.codeanalyzerserver.controller.rest;


import com.vicras.codeanalyzerserver.dto.DocumentResponseDto;
import com.vicras.codeanalyzerserver.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/document")
public class DocumentController {
    private final DocumentService documentService;

    @GetMapping
    public List<DocumentResponseDto> getDocuments(Principal principal) {
        return documentService.getAllUserDocuments(principal);
    }

    @GetMapping("/{id}")
    public DocumentResponseDto getDocumentsById(@PathVariable Long id, Principal principal) {
        return documentService.getDocumentById(id, principal);
    }
}
