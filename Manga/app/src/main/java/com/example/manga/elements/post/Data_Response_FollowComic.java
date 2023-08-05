package com.example.manga.elements.post;

import java.util.List;

public class Data_Response_FollowComic {
    private List<String> listComics;
    private String msg;
    private boolean success;


    public Data_Response_FollowComic() {
    }

    public Data_Response_FollowComic(List<String> listComics, String msg, boolean success) {
        this.listComics = listComics;
        this.msg = msg;
        this.success = success;
    }

    public List<String> getListComics() {
        return listComics;
    }

    public void setListComics(List<String> listComics) {
        this.listComics = listComics;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
