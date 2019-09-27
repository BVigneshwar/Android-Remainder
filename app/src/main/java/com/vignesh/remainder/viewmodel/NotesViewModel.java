package com.vignesh.remainder.viewmodel;

import android.app.Application;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vignesh.remainder.DAO.CategoryDAO;
import com.vignesh.remainder.DAO.NotesDAO;
import com.vignesh.remainder.database.NotesDatabase;
import com.vignesh.remainder.entity.CategoryEntity;
import com.vignesh.remainder.entity.NotesEntity;
import com.vignesh.remainder.notesmodule.NotesWithCategory;

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
        notesDAO = NotesDatabase.getNotesDatabase(application).notesDAO();
        categoryDAO = NotesDatabase.getNotesDatabase(application).categoryDAO();
        executorService = Executors.newSingleThreadExecutor();
        selected_notes = new MutableLiveData<>();
    }

    public LiveData<List<NotesWithCategory>> getNotesWithCategory(String sort_by){
        LiveData<List<NotesWithCategory>> notes_list = notesDAO.getAllNotesWithCategory(sort_by);
        return notes_list;
    }

    public void setSelectedNotes(NotesWithCategory notesWithCategory){
        selected_notes.setValue(notesWithCategory);
    }

    public MutableLiveData<NotesWithCategory> getSelectedNotes(){
        if(selected_notes.getValue() == null){
            return getNewNotes();
        }else{
            return selected_notes;
        }
    }

    public MutableLiveData<NotesWithCategory> getNewNotes(){
        final NotesWithCategory newNotes = new NotesWithCategory();
        newNotes.setNotes_id(-1);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                CategoryEntity defaultCategory = categoryDAO.getDefaultCatgeory();
                newNotes.setCategory_name(defaultCategory.getCategory_name());
                newNotes.setCategory_id(defaultCategory.getCategory_id());
                newNotes.setCategory_color(defaultCategory.getCategory_color());
                selected_notes.postValue(newNotes);
            }
        });
        selected_notes.setValue(newNotes);
        return selected_notes;
    }

    public void saveNotes(final NotesWithCategory notesWithCategory){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                DateFormat df = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
                Date current_dateobj = new Date();

                NotesEntity notesEntity = new NotesEntity();

                notesEntity.setNotes_name(notesWithCategory.getNotes_name());
                notesEntity.setNotes_description(notesWithCategory.getNotes_description());
                notesEntity.setCategory_id(notesWithCategory.getCategory_id());
                notesEntity.setLast_modified(df.format(current_dateobj));

                if(notesWithCategory.getNotes_id() != -1){
                    notesEntity.setNotes_id(notesWithCategory.getNotes_id());
                    notesDAO.updateNotes(notesEntity);
                }else{
                    notesEntity.setCreated_time(df.format(current_dateobj));
                    notesDAO.insertNotes(notesEntity);
                }
            }
        });

    }
}
