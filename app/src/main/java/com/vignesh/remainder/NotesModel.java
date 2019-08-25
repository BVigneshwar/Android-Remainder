package com.vignesh.remainder;

public class NotesModel {
    private int id;
    private String title;
    private String description;
    private String category_color;

    NotesModel(int id, String title, String description, String category_color){
        this.id = id;
        this.title = title;
        this.description = description;
        this.category_color = category_color;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategoryColor() {
        return category_color;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategoryColor(String category) {
        this.category_color = category;
    }
}
