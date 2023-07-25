package com.example.manga.elements.child;

import java.util.List;

public class Chapters {
    private String _id;
    private String comic_name;
    private List<Contents> contents;

    public Chapters() {
    }

    public Chapters(String _id, String comic_name, List<Contents> contents) {
        this._id = _id;
        this.comic_name = comic_name;
        this.contents = contents;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getComic_name() {
        return comic_name;
    }

    public void setComic_name(String comic_name) {
        this.comic_name = comic_name;
    }

    public List<Contents> getContents() {
        return contents;
    }

    public void setContents(List<Contents> contents) {
        this.contents = contents;
    }
}
