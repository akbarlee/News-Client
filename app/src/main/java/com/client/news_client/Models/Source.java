package com.client.news_client.Models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Source implements Serializable {

    @Expose
    private Long snews_id;

    @Expose
    private String name = "";



    @Override
    public String toString() {
        return "Source{" +
                "id=" + snews_id +
                ", name='" + name + '\'' +
                '}';
    }



    public Long getId() {
        return snews_id;
    }

    public void setId(Long id) {
        this.snews_id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
