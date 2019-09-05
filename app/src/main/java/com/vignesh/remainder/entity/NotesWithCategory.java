package com.vignesh.remainder.entity;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.vignesh.remainder.BR;

public class NotesWithCategory extends BaseObservable {
    private int notes_id;
    private String notes_name;
    private String notes_description;
    private int category_id;
    private String created_time;
    private String last_modified;
    private String category_name;
    private String category_color;

    @Bindable
    public int getNotes_id() {
        return notes_id;
    }

    @Bindable
    public String getNotes_name() {
        return notes_name;
    }

    @Bindable
    public String getNotes_description() {
        return notes_description;
    }

    @Bindable
    public int getCategory_id() {
        return category_id;
    }

    @Bindable
    public String getCreated_time() {
        return created_time;
    }

    @Bindable
    public String getLast_modified() {
        return last_modified;
    }

    @Bindable
    public String getCategory_name() {
        return category_name;
    }

    @Bindable
    public String getCategory_color() {
        return category_color;
    }


    public void setNotes_id(int notes_id) {
        this.notes_id = notes_id;
        notifyPropertyChanged(BR.notesDetail);
    }

    public void setNotes_name(String notes_name) {
        this.notes_name = notes_name;
        notifyPropertyChanged(BR.notesDetail);
    }

    public void setNotes_description(String notes_description) {
        this.notes_description = notes_description;
        notifyPropertyChanged(BR.notesDetail);
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
        notifyPropertyChanged(BR.notesDetail);
    }

    public void setCreated_time(String created_time) {
        this.created_time = created_time;
        notifyPropertyChanged(BR.notesDetail);
    }

    public void setLast_modified(String last_modified) {
        this.last_modified = last_modified;
        notifyPropertyChanged(BR.notesDetail);
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
        notifyPropertyChanged(BR.notesDetail);
    }

    public void setCategory_color(String category_color) {
        this.category_color = category_color;
        notifyPropertyChanged(BR.notesDetail);
    }
}
