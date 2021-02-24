
package com.example.loginandproduclist;

import com.google.gson.annotations.SerializedName;

public class Product {

    @SerializedName("id")
    public Integer id;

    @SerializedName("title")
    public String title;

    @SerializedName("price")
    public Double price;

    @SerializedName("description")
    public String description;

    @SerializedName("category")
    public String category;

    @SerializedName("image")
    public String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

}
