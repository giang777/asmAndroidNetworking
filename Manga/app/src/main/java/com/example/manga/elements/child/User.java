package com.example.manga.elements.child;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
    private String _id,username,email,avatar,passwrd;
    private List<String> follow;
    private List<String> history;


    public User() {
    }

    public User(String email,String passwrd) {
        this.email = email;
        this.passwrd = passwrd;
    }

    public User(String _id,String username,String email,String avatar, List<String> follow,List<String> history) {
        this._id = _id;
        this.username = username;
        this.email = email;
        this.avatar = avatar;
        this.follow = follow;
        this.history = history;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<String> getFollow() {
        return follow;
    }

    public void setFollow(List<String> follow) {
        this.follow = follow;
    }

    public List<String> getHistory() {
        return history;
    }

    public void setHistory(List<String> history) {
        this.history = history;
    }
}
