package com.vignesh.remainder.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.vignesh.remainder.entity.ReminderEntity;

@Dao
public interface ReminderDAO {
    @Query("SELECT * FROM reminder_table")
    LiveData<ReminderEntity> getAllReminder();

    @Insert
    void addReminder(ReminderEntity reminderEntity);

    @Update
    void updateRemainder(ReminderEntity reminderEntity);
}
