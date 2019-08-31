package com.vignesh.remainder.entity;

import android.widget.Switch;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "category_table")
public class CategoryEntity {

    @PrimaryKey(autoGenerate = true)
    private int category_id;

    @ColumnInfo(name = "category_name")
    private String category_name;

    @ColumnInfo(name = "category_color")
    private String category_color;

    @ColumnInfo(name = "is_deleted")
    private boolean is_deleted;

    public int getCategory_id() {
        return category_id;
    }

    public String getCategory_name() {
        return category_name;
    }

    public String getCategory_color() {
        return category_color;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }

    public void setCategory_color(String category_color) {
        this.category_color = category_color;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }
}
