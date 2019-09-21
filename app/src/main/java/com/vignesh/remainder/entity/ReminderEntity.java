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
}
