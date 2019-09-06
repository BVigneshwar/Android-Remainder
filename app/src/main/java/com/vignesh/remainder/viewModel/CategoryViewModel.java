package com.vignesh.remainder.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.vignesh.remainder.DAO.NotesDAO;
import com.vignesh.remainder.database.NotesDatabase;
import com.vignesh.remainder.entity.CategoryEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CategoryViewModel extends AndroidViewModel {
    private ExecutorService executorService;
    NotesDAO notesDAO;

    public CategoryViewModel(Application application){
        super(application);
        notesDAO = NotesDatabase.getNotesDatabase(application).notesDAO();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<CategoryEntity>> getCategoryList() {
        LiveData<List<CategoryEntity>> category_list = notesDAO.getCategoryList();
        return category_list;
    }

    public void saveCategory(final CategoryEntity categoryEntity){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                notesDAO.insertCategory(categoryEntity);
            }
        });
    }
}
