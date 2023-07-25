package com.example.manga.elements.post;

public class Data_Response_Post {
    private String msg;
    private boolean success;

    public Data_Response_Post() {
    }

    public Data_Response_Post(String msg, boolean success) {
        this.msg = msg;
        this.success = success;
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
