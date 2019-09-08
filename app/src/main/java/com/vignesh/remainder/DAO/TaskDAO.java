package com.vignesh.remainder.DAO;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vignesh.remainder.entity.TaskEntity;

@Dao
public interface TaskDAO {

    @Insert
    void insertTask(TaskEntity taskEntity);

    @Query("SELECT * FROM task_table")
    TaskEntity selectAllTask();
}
