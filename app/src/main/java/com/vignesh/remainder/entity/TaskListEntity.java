package com.vignesh.remainder.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_list_table", foreignKeys = @ForeignKey(entity = TaskEntity.class, parentColumns = "task_id", childColumns = "task_id", onDelete = ForeignKey.NO_ACTION))
public class TaskListEntity {

    @PrimaryKey(autoGenerate = true)
    int task_list_id;

    @ColumnInfo(name = "task_list_name")
    String task_list_name;

    @ColumnInfo(name = "task_list_is_completed")
    Boolean task_list_is_completed;

    @ColumnInfo(name = "task_id")
    int task_id;

    public int getTask_list_id() {
        return task_list_id;
    }

    public String getTask_list_name() {
        return task_list_name;
    }

    public Boolean getTask_list_is_completed() {
        return task_list_is_completed;
    }

    public int getTask_id() {
        return task_id;
    }

    public void setTask_list_id(int task_list_id) {
        this.task_list_id = task_list_id;
    }

    public void setTask_list_name(String task_list_name) {
        this.task_list_name = task_list_name;
    }

    public void setTask_list_is_completed(Boolean task_list_is_completed) {
        this.task_list_is_completed = task_list_is_completed;
    }

    public void setTask_id(int task_id) {
        this.task_id = task_id;
    }
}
