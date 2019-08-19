package com.vignesh.remainder;

public class CategoryModel {
    private String title;
    private String color;
    CategoryModel(String title, String color){
        this.title = title;
        this.color = color;
    }

    public String getTitle() {
        return title;
    }

    public String getColor() {
        return color;
    }
}
