package com.tmmarv.nlcf.Models;

public class SermonModel {

    private String sermonName;
    private long size;
    private String sermonUrl;
    private String lastPath;

    public SermonModel() {
    }

    public SermonModel(String sermonName, long size, String sermonUrl, String lastPath) {
        this.sermonName = sermonName;
        this.size = size;
        this.sermonUrl = sermonUrl;
        this.lastPath = lastPath;
    }

    public String getSermonName() {
        return sermonName;
    }

    public long getSize() {
        return size;
    }

    public String getSermonUrl() {
        return sermonUrl;
    }

    public String getLastPath() {
        return lastPath;
    }
}
