package com.vignesh.remainder.entity;

import androidx.room.Embedded;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class NotesWithCategory {
    @PrimaryKey
    private int notes_id;
    private String notes_name;
    private String notes_description;
    private int category_id;
    private String created_time;
    private String last_modified;
    private String category_name;
    private String category_color;

    public int getNotes_id() {
        return notes_id;
    }

    public String getNotes_name() {
        return notes_name;
    }

    public String getNotes_description() {
        return notes_description;
    }

    public int getCategory_id() {
        return category_id;
    }

    public String getCreated_time() {
        return created_time;
    }

    public String getLast_modified() {
        return last_modified;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getCategory_color() {
        return category_color;
    }

    public void setNotes_id(int notes_id) {
        this.notes_id = notes_id;
    }

    public void setNotes_name(String notes_name) {
        this.notes_name = notes_name;
    }

    public void setNotes_description(String notes_description) {
        this.notes_description = notes_description;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
    }

    public void setLast_modified(String last_modified) {
        this.last_modified = last_modified;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setCategory_color(String category_color) {
        this.category_color = category_color;
    }
}
