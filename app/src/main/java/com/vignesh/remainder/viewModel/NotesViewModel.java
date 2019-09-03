package com.vignesh.remainder.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vignesh.remainder.DAO.NotesDAO;
import com.vignesh.remainder.database.NotesDatabase;
import com.vignesh.remainder.entity.NotesEntity;
import com.vignesh.remainder.entity.NotesWithCategory;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotesViewModel extends AndroidViewModel {

    NotesDAO notesDAO;
    ExecutorService executorService;
    MutableLiveData<NotesWithCategory> selected_notes;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        notesDAO = NotesDatabase.getCategoryDatabase(application).notesDAO();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<NotesWithCategory>> getNotesWithCategory(){
        LiveData<List<NotesWithCategory>> notes_list = notesDAO.getAllNotesWithCategory();
        return notes_list;
    }

    public void selectNotes(NotesWithCategory notesWithCategory){
        selected_notes.setValue(notesWithCategory);
    }

    public MutableLiveData<NotesWithCategory> getSelectedNotes(){
        return selected_notes;
    }
}
