package com.vicras.codeanalyzerserver.mapper;


import com.vicras.codeanalyzerserver.dto.DocumentResponseDto;
import com.vicras.codeanalyzerserver.model.AnalyzerUser;
import com.vicras.codeanalyzerserver.model.Document;
import com.vicras.codeanalyzerserver.model.DocumentSource;
import com.vicras.codeanalyzerserver.model.SourceType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ERROR)
public abstract class DocumentMapper {

    @Mapping(target = "id", source = "doc.id")
    @Mapping(target = "userId", source = "doc.user.id")
    @Mapping(target = "link", source = "doc.source.link")
    @Mapping(target = "type", source = "doc.source.type")
    public abstract DocumentResponseDto toResponse(Document doc);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "source", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract Document toDoc(DocumentResponseDto requestDto);

    public abstract List<DocumentResponseDto> mapList(List<Document> documents);

    public DocumentSource createSource(SourceType type, String link) {
        DocumentSource documentSource = new DocumentSource();
        documentSource.setLink(link);
        documentSource.setType(type);
        return documentSource;
    }

    public Document createDocument(DocumentSource source, AnalyzerUser user, String title, String statistics) {
        Document document = new Document();
        document.setTitle(title);
        document.setSource(source);
        document.setStatistics(statistics);
        document.setCreatedAt(LocalDateTime.now());
        document.setUser(user);
        return document;
    }
}
