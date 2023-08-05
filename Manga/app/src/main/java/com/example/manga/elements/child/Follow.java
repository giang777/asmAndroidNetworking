package com.example.manga.elements.child;

import java.util.List;

public class Follow {
    private String _id;
    private List<String> listComics;
    private boolean isFollow;

    public Follow() {
    }

    public Follow(String _id, List<String> listComics,boolean isFollow) {
        this._id = _id;
        this.listComics = listComics;
        this.isFollow = isFollow;
    }

    public boolean isFollow() {
        return isFollow;
    }

    public void setFollow(boolean follow) {
        isFollow = follow;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public List<String> getListComics() {
        return listComics;
    }

    public void setListComics(List<String> listComics) {
        this.listComics = listComics;
    }
}
