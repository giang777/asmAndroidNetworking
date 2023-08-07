package com.example.manga.elements.child;

import android.graphics.Bitmap;

import java.io.Serializable;

public class Notifi implements Serializable {
    private String titel,msg,img;

    public Notifi() {
    }

    public Notifi(String titel, String msg, String img) {
        this.titel = titel;
        this.msg = msg;
        this.img = img;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
