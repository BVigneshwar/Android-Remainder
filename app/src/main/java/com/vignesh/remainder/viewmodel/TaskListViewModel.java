package com.vignesh.remainder.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vignesh.remainder.DAO.TaskListDAO;
import com.vignesh.remainder.database.NotesDatabase;
import com.vignesh.remainder.entity.TaskListEntity;

import java.util.List;

public class TaskListViewModel extends AndroidViewModel {
    TaskListDAO taskListDAO;

    public TaskListViewModel(@NonNull Application application) {
        super(application);
        taskListDAO = NotesDatabase.getNotesDatabase(application).taskListDAO();
    }

    public LiveData<List<TaskListEntity>> getAllTaskListFor(int task_id){
        return taskListDAO.getAllTaskList(task_id);
    }
}
