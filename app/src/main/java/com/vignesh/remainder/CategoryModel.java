package com.vignesh.remainder;

public class CategoryModel {
    private String title;
    private String color;
    private int id;
    CategoryModel(int id, String title, String color){
        this.id = id;
        this.title = title;
        this.color = color;
    }

    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getColor() {
        return color;
    }
}
