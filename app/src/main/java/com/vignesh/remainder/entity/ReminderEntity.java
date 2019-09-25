package com.vignesh.remainder.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "reminder_table")
public class ReminderEntity {

    @ColumnInfo(name = "reminder_id")
    @PrimaryKey(autoGenerate = true)
    private int reminder_id;

    @ColumnInfo(name = "reminder_name")
    private String reminder_name;

    @ColumnInfo(name = "reminder_description")
    private String reminder_description;

    @ColumnInfo(name = "reminder_timer")
    private String reminder_timer;

    public int getReminder_id() {
        return reminder_id;
    }

    public String getReminder_name() {
        return reminder_name;
    }

    public String getReminder_description() {
        return reminder_description;
    }

    public String getReminder_timer() {
        return reminder_timer;
    }

    public void setReminder_id(int reminder_id) {
        this.reminder_id = reminder_id;
    }

    public void setReminder_name(String reminder_name) {
        this.reminder_name = reminder_name;
    }

    public void setReminder_description(String reminder_description) {
        this.reminder_description = reminder_description;
    }

    public void setReminder_timer(String reminder_timer) {
        this.reminder_timer = reminder_timer;
    }
}
