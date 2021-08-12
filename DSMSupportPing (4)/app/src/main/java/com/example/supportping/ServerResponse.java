package com.example.supportping;

public class ServerResponse {
    private String title;
    private String content;
    private String NowLocation;
    private String people;


    public ServerResponse(String title, String content, String NowLocation, String people) {
        this.title = title;
        this.content = content;
        this.NowLocation = NowLocation;
        this.people = people;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public String getNowLocation() {
        return NowLocation;
    }

    public void setNowLocation(String NowLocation) {
        this.NowLocation = NowLocation;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }
}
