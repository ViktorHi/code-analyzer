package com.vicras.codeanalyzerserver.dto;

import com.vicras.codeanalyzerserver.model.SourceType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentResponseDto implements Dto {

    private Long id;
    private Long userId;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String link;
    private String title;
    private String statistics;

    private SourceType type;
}
