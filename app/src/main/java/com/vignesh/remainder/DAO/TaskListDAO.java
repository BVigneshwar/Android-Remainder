package com.vignesh.remainder.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vignesh.remainder.entity.TaskEntity;
import com.vignesh.remainder.entity.TaskListEntity;

import java.util.List;

@Dao
public interface TaskListDAO {

    @Insert
    void insertTaskList(TaskListEntity taskListEntity);

    @Query("SELECT * FROM task_list_table")
    TaskEntity getAllTaskList();

    @Query("SELECT * FROM task_list_table WHERE task_id =:task_id")
    LiveData<List<TaskListEntity>> getAllTaskList(int task_id);
}
