package com.vicras.codeanalyzerserver.mapper;

import com.vicras.codeanalyzerserver.dto.UserResponseDto;
import com.vicras.codeanalyzerserver.model.AnalyzerUser;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import static org.mapstruct.ReportingPolicy.ERROR;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ERROR)
public abstract class UserMapper {

    @Mapping(target = "id", source = "user.id")
    public abstract UserResponseDto toResponse(AnalyzerUser user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    public abstract AnalyzerUser toUser(UserResponseDto requestDto);

}