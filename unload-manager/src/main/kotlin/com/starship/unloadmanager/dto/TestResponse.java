package com.starship.unloadmanager.dto;

import java.io.Serializable;

public class TestResponse implements Serializable {
    private Integer length;

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
