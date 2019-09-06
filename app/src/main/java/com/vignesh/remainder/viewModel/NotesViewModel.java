package com.vignesh.remainder.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vignesh.remainder.DAO.CategoryDAO;
import com.vignesh.remainder.DAO.NotesDAO;
import com.vignesh.remainder.database.CategoryDatabase;
import com.vignesh.remainder.database.NotesDatabase;
import com.vignesh.remainder.entity.CategoryEntity;
import com.vignesh.remainder.entity.NotesEntity;
import com.vignesh.remainder.entity.NotesWithCategory;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NotesViewModel extends AndroidViewModel {

    NotesDAO notesDAO;
    CategoryDAO categoryDAO;
    ExecutorService executorService;
    MutableLiveData<NotesWithCategory> selected_notes;

    public NotesViewModel(@NonNull Application application) {
        super(application);
        notesDAO = NotesDatabase.getCategoryDatabase(application).notesDAO();
        categoryDAO = CategoryDatabase.getCategoryDatabase(application).categoryDAO();
        executorService = Executors.newSingleThreadExecutor();
        selected_notes = new MutableLiveData<>();
    }

    public LiveData<List<NotesWithCategory>> getNotesWithCategory(){
        LiveData<List<NotesWithCategory>> notes_list = notesDAO.getAllNotesWithCategory();
        return notes_list;
    }

    public void selectNotes(NotesWithCategory notesWithCategory){
        selected_notes.setValue(notesWithCategory);
    }

    public MutableLiveData<NotesWithCategory> getNewNotes(){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                NotesWithCategory newNotes = new NotesWithCategory();
                CategoryEntity defaultCategory = categoryDAO.getDefaultCatgeory();
                newNotes.setCategory_name(defaultCategory.getCategory_name());
                newNotes.setCategory_id(defaultCategory.getCategory_id());
                newNotes.setCategory_color(defaultCategory.getCategory_color());
                selected_notes.postValue(newNotes);
            }
        });
        return selected_notes;
    }

    public MutableLiveData<NotesWithCategory> getSelectedNotes(){
        return selected_notes;
    }

    public void saveNotes(final NotesWithCategory notesWithCategory){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                Date current_dateobj = new Date();

                NotesEntity notesEntity = new NotesEntity();
                notesEntity.setNotes_id(notesWithCategory.getNotes_id());
                notesEntity.setNotes_name(notesWithCategory.getNotes_name());
                notesEntity.setNotes_description(notesWithCategory.getNotes_description());
                notesEntity.setCategory_id(notesWithCategory.getCategory_id());
                notesEntity.setCreated_time(df.format(current_dateobj));
                notesEntity.setLast_modified(df.format(current_dateobj));

                notesDAO.insertNotes(notesEntity);
            }
        });

    }
}
