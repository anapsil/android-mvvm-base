package net.anapsil.androidmvvmbase.domain.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ana.silva on 19/01/18.
 */

public class Data implements Serializable {
    private int offset;
    private int limit;
    private int total;
    private int count;
    private List<Character> results;

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Character> getResults() {
        return results;
    }

    public void setResults(List<Character> results) {
        this.results = results;
    }
}
