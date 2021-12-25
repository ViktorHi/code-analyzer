package com.vicras.codeanalyzerserver.service.impl;

import com.vicras.codeanalyzerserver.dto.DocumentResponseDto;
import com.vicras.codeanalyzerserver.mapper.DocumentMapper;
import com.vicras.codeanalyzerserver.model.Document;
import com.vicras.codeanalyzerserver.model.DocumentSource;
import com.vicras.codeanalyzerserver.model.SourceType;
import com.vicras.codeanalyzerserver.repository.DocumentRepository;
import com.vicras.codeanalyzerserver.service.DocumentService;
import com.vicras.codeanalyzerserver.service.UploadService;
import com.vicras.codeanalyzerserver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.time.LocalDateTime;

import static com.vicras.codeanalyzerserver.model.SourceType.GIT;
import static com.vicras.codeanalyzerserver.model.SourceType.INTERNAL;

@Component
@RequiredArgsConstructor
public class UploadServiceImpl implements UploadService {

    private static final String URL_SEPARATOR = "/";

    private final FileAnalyzer analyzer;
    private final FilePerformer performer;
    private final UserService userService;
    private final DocumentRepository documentRepository;
    private final DocumentService documentService;
    private final DocumentMapper mapper;

    @Override
    public DocumentResponseDto fromArchive(MultipartFile multipartFile, Principal user) {
        var file = performer.fromMultipartFile(multipartFile);
        var stat = analyzer.fromFile(file.toFile());
        var source = createSource(INTERNAL, file.getFileName().toString());
        var doc = createDocument(source, user, multipartFile.getOriginalFilename(), stat.toString());
        return mapper.toResponse(documentRepository.save(doc));
    }

    @Override
    public DocumentResponseDto fromUrl(String url, Principal user) {
        var stat = analyzer.fromUrl(url);
        var source = createSource(GIT, url);
        var doc = createDocument(source, user, getNameFromUrl(url), stat.toString());
        return mapper.toResponse(documentRepository.save(doc));
    }

    @Override
    public Pair<ByteArrayResource, DocumentResponseDto> downloadFile(Long id, Principal user) {
        var doc = documentService.getDocumentById(id, user);
        return Pair.of(performer.findInputStream(doc.getLink()), doc);
    }

    private DocumentSource createSource(SourceType type, String link) {
        DocumentSource documentSource = new DocumentSource();
        documentSource.setLink(link);
        documentSource.setType(type);
        return documentSource;
    }

    private Document createDocument(DocumentSource source, Principal principal, String title, String statistics) {
        Document document = new Document();
        document.setTitle(title);
        document.setSource(source);
        document.setStatistics(statistics);
        document.setCreatedAt(LocalDateTime.now());
        document.setUser(userService.findUserByLogin(principal.getName()));
        return document;
    }

    private String getNameFromUrl(String url) {
        final String[] urls = url.split(URL_SEPARATOR);
        int lastFragmentIndex = urls.length - 1;
        if (lastFragmentIndex < 0) lastFragmentIndex = 0;
        return urls[lastFragmentIndex];
    }
}
