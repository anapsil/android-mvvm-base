package net.anapsil.androidmvvmbase.domain.model;

import java.io.Serializable;

/**
 * Created by ana.silva on 19/01/18.
 */

public class Character implements Serializable{
    private int id;
    private String name;
    private Image thumbnail;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Image getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Image thumbnail) {
        this.thumbnail = thumbnail;
    }
}
