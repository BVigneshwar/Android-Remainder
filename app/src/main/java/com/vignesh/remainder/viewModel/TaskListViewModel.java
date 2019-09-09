package com.vignesh.remainder.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.room.RoomDatabase;

import com.vignesh.remainder.DAO.TaskListDAO;
import com.vignesh.remainder.database.NotesDatabase;

public class TaskListViewModel extends AndroidViewModel {
    TaskListDAO taskListDAO;

    public TaskListViewModel(@NonNull Application application) {
        super(application);
        taskListDAO = NotesDatabase.getNotesDatabase(application).taskListDAO();
    }

}
