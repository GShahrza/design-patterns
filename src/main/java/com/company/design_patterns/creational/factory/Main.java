package com.company.design_patterns.creational.factory;

public class Main {
    public static void main(String[] args) {
        String fileExcel = FileExporterFactory.getInstance(FileExporterFactory.FileType.EXCEL)
                .export(" Test content ");
        System.out.println(fileExcel);

        String filePdf = FileExporterFactory.getInstance(FileExporterFactory.FileType.PDF)
                .export(" Test content ");
        System.out.println(filePdf);

    }
}
