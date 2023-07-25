package com.example.manga.elements.child;

import java.io.Serializable;

public class Comics implements Serializable {
    private String _id,name,describe,img,avatar_story,id_chapter,id_category,author;
    private int like,view,follow;
    private boolean status;

    public Comics() {
    }

    public Comics(String _id, String name, String describe, String img, String avatar_story, String id_chapter, String id_category, String author, int like, int view, int follow, boolean status) {
        this._id = _id;
        this.name = name;
        this.describe = describe;
        this.img = img;
        this.avatar_story = avatar_story;
        this.id_chapter = id_chapter;
        this.id_category = id_category;
        this.author = author;
        this.like = like;
        this.view = view;
        this.follow = follow;
        this.status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getAvatar_story() {
        return avatar_story;
    }

    public void setAvatar_story(String avartar) {
        this.avatar_story = avartar;
    }

    public String getId_chapter() {
        return id_chapter;
    }

    public void setId_chapter(String id_chapter) {
        this.id_chapter = id_chapter;
    }

    public String getId_category() {
        return id_category;
    }

    public void setId_category(String id_category) {
        this.id_category = id_category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    public int getFollow() {
        return follow;
    }

    public void setFollow(int follow) {
        this.follow = follow;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
