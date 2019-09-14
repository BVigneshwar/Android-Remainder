package com.vignesh.remainder.DAO;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vignesh.remainder.entity.TaskEntity;
import com.vignesh.remainder.entity.TaskListEntity;

@Dao
public interface TaskListDAO {

    @Insert
    void insertTaskList(TaskListEntity taskListEntity);

    @Query("SELECT * FROM task_list_table")
    TaskEntity getAllTaskList();

    @Query("SELECT * FROM task_list_table WHERE task_id =:task_id")
    MutableLiveData<TaskListEntity> getAllTaskList(int task_id);
}
