package net.anapsil.androidmvvmbase.domain.model;

import java.io.Serializable;

/**
 * Created by ana.silva on 19/01/18.
 */

public class Image implements Serializable {
    private String path;
    private String extension;
    private transient String url;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
