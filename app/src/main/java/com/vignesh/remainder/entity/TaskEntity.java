package com.vignesh.remainder.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_table")
public class TaskEntity {

    @PrimaryKey(autoGenerate = true)
    int task_id;

    @ColumnInfo(name = "task_name")
    String task_name;

    @ColumnInfo(name = "task_description")
    String task_description;

    public int getTask_id() {
        return task_id;
    }

    public String getTask_name() {
        return task_name;
    }

    public String getTask_description() {
        return task_description;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public void setTask_description(String task_description) {
        this.task_description = task_description;
    }
}