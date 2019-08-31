package com.vignesh.remainder.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes_table", foreignKeys = @ForeignKey(entity = CategoryEntity.class, parentColumns = "category_id", childColumns = "category_id", onDelete = ForeignKey.NO_ACTION))

public class NotesEntity {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "notes_id")
    private int notes_id;

    @ColumnInfo(name = "notes_name")
    private String notes_name;

    @ColumnInfo(name = "notes_description")
    private String notes_description;

    @ColumnInfo(name = "category_id")
    private int category_id;

    @ColumnInfo(name = "created_time")
    private String created_time;

    @ColumnInfo(name = "last_modified")
    private String last_modified;

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
}
