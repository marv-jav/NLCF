package com.tmmarv.nlcf.Models;

public class ScheduleModel {
    private String title;
    private String date;
    private String time;
    private String status;

    public ScheduleModel() {
    }

    public ScheduleModel(String title, String date, String time, String status) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
