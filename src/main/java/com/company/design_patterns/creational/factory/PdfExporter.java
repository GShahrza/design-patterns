package com.company.design_patterns.creational.factory;

public class PdfExporter implements FileExporter {
    @Override
    public String export(String content) {
        return "PDF-> " + content;
    }
}
