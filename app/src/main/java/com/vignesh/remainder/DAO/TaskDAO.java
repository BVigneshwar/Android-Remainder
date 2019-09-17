package com.vignesh.remainder.DAO;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vignesh.remainder.entity.TaskEntity;

import java.util.List;

@Dao
public interface TaskDAO {

    @Insert
    void insertTask(TaskEntity taskEntity);

    @Query("SELECT * FROM task_table")
    LiveData<List<TaskEntity>> getAllTask();
}
