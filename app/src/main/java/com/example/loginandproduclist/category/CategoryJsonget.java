package com.example.loginandproduclist.category;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CategoryJsonget {

    public String getName() {
        return name;
    }
    public String getImage() {
        return image;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setImage(String image) {
        this.image = image;
    }

    public String name;

    public String image;


}
