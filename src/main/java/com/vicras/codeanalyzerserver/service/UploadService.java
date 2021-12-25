package com.vicras.codeanalyzerserver.service;


import com.vicras.codeanalyzerserver.dto.DocumentResponseDto;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

public interface UploadService {
    DocumentResponseDto fromArchive(MultipartFile file, Principal user);

    DocumentResponseDto fromUrl(String url, Principal user);

    Pair<ByteArrayResource, DocumentResponseDto> downloadFile(Long id, Principal user);
}
