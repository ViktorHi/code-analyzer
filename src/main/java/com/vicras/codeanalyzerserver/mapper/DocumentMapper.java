package com.vicras.codeanalyzerserver.mapper;


import com.vicras.codeanalyzerserver.dto.DocumentResponseDto;
import com.vicras.codeanalyzerserver.model.Document;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

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
}
