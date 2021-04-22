package com.example.newsboard.model;

import com.google.gson.annotations.SerializedName;

public class ArticleEntity {
    @SerializedName("id")
    private int objId;
    private String title;
    private String time;
    private String image;
    private String content;

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getImage() {
        return image;
    }

    public String getContent() {
        return this.content;
    }

    public int getObjId() {
        return this.objId;
    }
}
