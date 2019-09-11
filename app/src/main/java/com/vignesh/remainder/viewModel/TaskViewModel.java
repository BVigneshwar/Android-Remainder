package com.vignesh.remainder.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.vignesh.remainder.DAO.TaskDAO;
import com.vignesh.remainder.database.NotesDatabase;
import com.vignesh.remainder.entity.TaskEntity;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    TaskDAO taskDAO;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskDAO = NotesDatabase.getNotesDatabase(application).taskDAO();
    }

    public MutableLiveData<List<TaskEntity>> getAllTask(){
        return taskDAO.getAllTask();
    }
}
