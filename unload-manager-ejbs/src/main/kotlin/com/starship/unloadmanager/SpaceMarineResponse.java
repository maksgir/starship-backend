package com.starship.unloadmanager;

import java.io.Serializable;

public class SpaceMarineResponse implements Serializable {
    private Integer status;

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
