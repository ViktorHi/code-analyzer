package com.vicras.codeanalyzerserver.service;

import com.vicras.codeanalyzerserver.dto.DocumentResponseDto;

import java.security.Principal;
import java.util.List;

public interface DocumentService {

    DocumentResponseDto getDocumentById(Long id, Principal principal);

    List<DocumentResponseDto> getAllUserDocuments(Principal principal);
}
