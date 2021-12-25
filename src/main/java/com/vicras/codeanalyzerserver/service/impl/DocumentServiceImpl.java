package com.vicras.codeanalyzerserver.service.impl;

import com.vicras.codeanalyzerserver.dto.DocumentResponseDto;
import com.vicras.codeanalyzerserver.exception.exceptions.business.BusinessException;
import com.vicras.codeanalyzerserver.exception.exceptions.business.EntityNotFoundException;
import com.vicras.codeanalyzerserver.mapper.DocumentMapper;
import com.vicras.codeanalyzerserver.model.AnalyzerUser;
import com.vicras.codeanalyzerserver.model.Document;
import com.vicras.codeanalyzerserver.repository.DocumentRepository;
import com.vicras.codeanalyzerserver.service.DocumentService;
import com.vicras.codeanalyzerserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository repository;
    private final UserService userService;
    private final DocumentMapper mapper;

    @Override
    public DocumentResponseDto getDocumentById(Long id, Principal principal) {
        var doc = getDocById(id);
        validateDocOwner(principal, doc);
        return mapper.toResponse(doc);
    }

    @Override
    public List<DocumentResponseDto> getAllUserDocuments(Principal principal) {
        var user = userService.findUserByLogin(principal.getName());
        return mapper.mapList(repository.findAllByUser(user));
    }

    private Document getDocById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(Document.class, id.toString()));
    }

    private void validateDocOwner(Principal principal, Document doc) {
        final AnalyzerUser user = userService.findUserByLogin(principal.getName());
        if (!doc.getUser().getId().equals(user.getId())) {
            throw new BusinessException("Entity not belong to this user");
        }
    }
}
