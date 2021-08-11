package com.example.supportping;

public class MainData {

    // 게시물 관련
    String title;
    String nickname;
    String place;
    String personnel;
    String content;


    public MainData(String title, String nickname, String place, String personnel, String content) {
        this.title = title;
        this.nickname = nickname;
        this.place = place;
        this.personnel = personnel;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPlace() {
        return place;
    }

    public String getPersonnel() {
        return personnel;
    }

    public String getContent() {
        return content;
    }
}
