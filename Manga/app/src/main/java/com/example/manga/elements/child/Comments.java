package com.example.manga.elements.child;

import java.util.Date;

public class Comments {
    private String _id;
    private User id_user;
    private String id_comic;
    private String content;
    private Date time;

    public Comments() {
    }

    public Comments(String _id, User id_user, String id_comic, String content, Date time) {
        this._id = _id;
        this.id_user = id_user;
        this.id_comic = id_comic;
        this.content = content;
        this.time = time;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }

    public String getId_comic() {
        return id_comic;
    }

    public void setId_comic(String id_comic) {
        this.id_comic = id_comic;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
