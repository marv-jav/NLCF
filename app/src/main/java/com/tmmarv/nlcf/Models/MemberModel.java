package com.tmmarv.nlcf.Models;

public class MemberModel {

    private String department;
    private String dob;
    private String imageUrl;
    private String lastPath;
    private String level;
    private String name;
    private String position;

    public MemberModel() {
    }

    public MemberModel(String department, String dob, String imageUrl, String lastPath, String level, String name, String position) {
        this.department = department;
        this.dob = dob;
        this.imageUrl = imageUrl;
        this.lastPath = lastPath;
        this.level = level;
        this.name = name;
        this.position = position;
    }

    public String getDepartment() {
        return department;
    }

    public String getDob() {
        return dob;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getLastPath() {
        return lastPath;
    }

    public String getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }
}
