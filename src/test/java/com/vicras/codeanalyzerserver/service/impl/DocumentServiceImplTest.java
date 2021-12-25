package com.vicras.codeanalyzerserver.service.impl;

import com.vicras.codeanalyzerserver.exception.exceptions.business.BusinessException;
import com.vicras.codeanalyzerserver.exception.exceptions.business.EntityNotFoundException;
import com.vicras.codeanalyzerserver.mapper.DocumentMapper;
import com.vicras.codeanalyzerserver.model.AnalyzerUser;
import com.vicras.codeanalyzerserver.repository.DocumentRepository;
import com.vicras.codeanalyzerserver.service.DocumentService;
import com.vicras.codeanalyzerserver.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.Principal;
import java.util.Optional;

import static com.vicras.codeanalyzerserver.generator.DocumentGenerator.getDocument;
import static com.vicras.codeanalyzerserver.generator.UserGenerator.getUser;
import static com.vicras.codeanalyzerserver.generator.UserGenerator.getUser2;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class DocumentServiceImplTest {

    private final DocumentMapper mapper = mock(DocumentMapper.class);
    private final DocumentRepository repository = mock(DocumentRepository.class);
    private final UserService userService = mock(UserService.class);

    private final DocumentService service = new DocumentServiceImpl(repository, userService, mapper);

    @Test
    public void getDocumentByIdSuccess(){
        //given
        var doc = getDocument();
        var principal = mock(Principal.class);

        //when
        when(repository.findById((doc.getId()))).thenReturn(Optional.of(doc));
        when(userService.findUserByLogin(principal.getName())).thenReturn(getUser());

        //then
        service.getDocumentById(doc.getId(), principal);
        verify(repository, times(1)).findById(doc.getId());
        verify(userService, times(1)).findUserByLogin(principal.getName());
    }

    @Test
    public void getDocumentByIdNoDocFail(){
        //given
        var doc = getDocument();
        var principal = mock(Principal.class);

        //when
        when(repository.findById((doc.getId()))).thenReturn(Optional.empty());
        when(userService.findUserByLogin(principal.getName())).thenReturn(getUser());

        //then
        Assertions.assertThrows(EntityNotFoundException.class, ()->service.getDocumentById(doc.getId(), principal));
        verify(repository, times(1)).findById(doc.getId());
        verify(userService, times(0)).findUserByLogin(principal.getName());
    }

    @Test
    public void getDocumentByIdNotBelongFail(){
        //given
        var doc = getDocument();
        var principal = mock(Principal.class);
        var user = getUser2();

        //when
        when(repository.findById((doc.getId()))).thenReturn(Optional.of(doc));
        when(userService.findUserByLogin(principal.getName())).thenReturn(user);

        //then
        Assertions.assertThrows(BusinessException.class, ()->service.getDocumentById(doc.getId(), principal));
        verify(repository, times(1)).findById(doc.getId());
        verify(userService, times(1)).findUserByLogin(principal.getName());
    }
}