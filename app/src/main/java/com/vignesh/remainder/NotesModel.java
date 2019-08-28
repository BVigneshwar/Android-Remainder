package com.vignesh.remainder;

public class NotesModel {
    private int id;
    private String title;
    private String description;
    private String category_color;
    private String category_name;
    private int category_id;

    NotesModel(int id, String title, String description, int category_id, String category_name, String category_color){
        this.id = id;
        this.title = title;
        this.description = description;
        this.category_id = category_id;
        this.category_name = category_name;
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

    public String getCategory_color() { return category_color; }

    public String getCategory_name() { return category_name; }

    public int getCategory_id() { return category_id; }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory_color(String category_color) {
        this.category_color = category_color;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
