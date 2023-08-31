package com.company.design_patterns.creational.factory;

public class ExcelExporter implements FileExporter{
    @Override
    public String export(String content) {
        return "EXCEL -> " + content;
    }
}
