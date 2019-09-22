package com.vignesh.remainder.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.vignesh.remainder.DAO.ReminderDAO;
import com.vignesh.remainder.database.NotesDatabase;
import com.vignesh.remainder.entity.ReminderEntity;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReminderViewModel extends AndroidViewModel {
    ReminderDAO reminderDAO;
    ExecutorService executorService;

    public ReminderViewModel(@NonNull Application application) {
        super(application);
        reminderDAO = NotesDatabase.getNotesDatabase(application).reminderDAO();
        executorService = Executors.newSingleThreadExecutor();
    }

    LiveData<ReminderEntity> getAllReminder(){
        return reminderDAO.getAllReminder();
    }
}
