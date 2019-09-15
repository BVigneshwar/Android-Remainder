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
    MutableLiveData<Integer> selected_task_id;
    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskDAO = NotesDatabase.getNotesDatabase(application).taskDAO();
        selected_task_id = new MutableLiveData<>();
    }

    public MutableLiveData<List<TaskEntity>> getAllTask(){
        return taskDAO.getAllTask();
    }

    public int getSelectedTask(){
        return selected_task_id.getValue();
    }

    public void setSelectedTaskId(int task_id){
        selected_task_id.setValue(task_id);
    }
}
