package com.tmmarv.nlcf.Models;

public class BooksModel {
    private String pdfName;
    private long size;
    private String pdfUrl;
    private String lastPath;

    public BooksModel() {
    }

    public BooksModel(String pdfName, long size, String pdfUrl, String lastPath) {
        this.pdfName = pdfName;
        this.size = size;
        this.pdfUrl = pdfUrl;
        this.lastPath = lastPath;
    }

    public String getPdfName() {
        return pdfName;
    }

    public long getSize() {
        return size;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }

    public String getLastPath() {
        return lastPath;
    }
}
