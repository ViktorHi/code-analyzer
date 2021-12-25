package com.vicras.codeanalyzerserver.generator;


import com.vicras.codeanalyzerserver.model.AnalyzerUser;
import com.vicras.codeanalyzerserver.model.Document;
import com.vicras.codeanalyzerserver.model.DocumentSource;
import com.vicras.codeanalyzerserver.model.SourceType;

import java.time.LocalDateTime;

import static com.vicras.codeanalyzerserver.generator.UserGenerator.getUser;

public class DocumentGenerator {
    public static Document getDocument(){
        var doc = new Document();
        doc.setUser(getUser());
        doc.setStatistics("statistics");
        doc.setTitle("title");
        doc.setSource(getSource());
        doc.setId(1L);
        doc.setCreatedAt(LocalDateTime.of(2021,12,12,12,0));
        doc.setUpdatedAt(LocalDateTime.of(2021,12,12,12,0));
        return doc;
    }

    public static DocumentSource getSource(){
        var source = new DocumentSource();
        source.setType(SourceType.INTERNAL);
        source.setLink("link");
        return source;
    }
}
