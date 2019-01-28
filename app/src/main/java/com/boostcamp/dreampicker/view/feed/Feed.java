package com.boostcamp.dreampicker.view.feed;

import java.util.List;

public class Feed {
    private String id;
    private Image[] images;
    private User user;
    private String content;
    private String date;

    // TODO : 마감 조건 확인 이후 작업
    private boolean isEnded;

    // TODO : Firestore 연동 이후 작업
    private List<String> upVodtedUserKeys;
    private List<String> downVotedUserKeys;

    public Feed(String id, Image[] images, User user, String content, String date, boolean isEnded) {
        this.id = id;
        this.images = images;
        this.user = user;
        this.content = content;
        this.date = date;
        this.isEnded = isEnded;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Image[] getImages() {
        return images;
    }

    public void setImages(Image[] images) {
        this.images = images;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
