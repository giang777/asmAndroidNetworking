package com.example.manga.elements.child;

public class Blogs {
    private String _id,titel,content,img;

    public Blogs() {
    }

    public Blogs(String _id, String titel, String content, String img) {
        this._id = _id;
        this.titel = titel;
        this.content = content;
        this.img = img;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
