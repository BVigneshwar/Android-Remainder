package com.vignesh.remainder.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.room.RoomDatabase;

import com.vignesh.remainder.DAO.TaskListDAO;
import com.vignesh.remainder.database.NotesDatabase;
import com.vignesh.remainder.entity.TaskListEntity;

public class TaskListViewModel extends AndroidViewModel {
    TaskListDAO taskListDAO;

    public TaskListViewModel(@NonNull Application application) {
        super(application);
        taskListDAO = NotesDatabase.getNotesDatabase(application).taskListDAO();
    }

    public MutableLiveData<TaskListEntity> getAllTaskListFor(int task_id){
        return taskListDAO.getAllTaskList(task_id);
    }
}
