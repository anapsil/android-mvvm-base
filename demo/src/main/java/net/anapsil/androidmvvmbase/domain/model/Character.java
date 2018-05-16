package net.anapsil.androidmvvmbase.domain.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

/**
 * Created by ana.silva on 19/01/18.
 */

public class Character implements Serializable, Parcelable {
    public static final Creator<Character> CREATOR = new Creator<Character>() {
        @Override
        public Character createFromParcel(Parcel source) {
            return new Character(source);
        }

        @Override
        public Character[] newArray(int size) {
            return new Character[size];
        }
    };
    private int id;
    private String name;
    private Image thumbnail;
    private String description;

    public Character() {
    }

    protected Character(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.thumbnail = (Image) in.readSerializable();
        this.description = in.readString();
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeSerializable(this.thumbnail);
        dest.writeString(this.description);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Character) {
            return id == ((Character) obj).id;
        } else {
            return false;
        }
    }
}
