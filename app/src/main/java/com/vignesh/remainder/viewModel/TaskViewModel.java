package com.vignesh.remainder.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.vignesh.remainder.DAO.TaskDAO;
import com.vignesh.remainder.database.NotesDatabase;

public class TaskViewModel extends AndroidViewModel {
    TaskDAO taskDAO;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskDAO = NotesDatabase.getNotesDatabase(application).taskDAO();
    }

}
