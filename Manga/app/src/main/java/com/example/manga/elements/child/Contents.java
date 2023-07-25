package com.example.manga.elements.child;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Contents implements Serializable {
    private String name;
    private List<String> painting_data;

    public Contents() {
    }

    public Contents(String name, List<String> painting_data) {
        this.name = name;
        this.painting_data = painting_data;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPainting_data() {
        return painting_data;
    }

    public void setPainting_data(List<String> painting_data) {
        this.painting_data = painting_data;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Contents)) return false;
        Contents contents = (Contents) obj;
        return Objects.equals(name, contents.name);
    }
}
