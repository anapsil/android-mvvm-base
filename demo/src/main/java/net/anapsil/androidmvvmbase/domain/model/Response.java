package net.anapsil.androidmvvmbase.domain.model;

import com.google.gson.annotations.Expose;

import java.io.Serializable;

/**
 * Created by ana.silva on 19/01/18.
 */

public class Response implements Serializable {
    @Expose
    private int code;
    @Expose
    private String status;
    @Expose
    private String attributionText;
    @Expose
    private Data data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAttributionText() {
        return attributionText;
    }

    public void setAttributionText(String attributionText) {
        this.attributionText = attributionText;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
