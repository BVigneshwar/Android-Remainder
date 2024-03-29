package com.vignesh.remainder.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.vignesh.remainder.DAO.CategoryDAO;
import com.vignesh.remainder.database.NotesDatabase;
import com.vignesh.remainder.entity.CategoryEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CategoryViewModel extends AndroidViewModel {
    private ExecutorService executorService;
    CategoryDAO categoryDAO;

    public CategoryViewModel(Application application){
        super(application);
        categoryDAO = NotesDatabase.getNotesDatabase(application).categoryDAO();
        executorService = Executors.newSingleThreadExecutor();
    }

    public LiveData<List<CategoryEntity>> getCategoryList() {
        LiveData<List<CategoryEntity>> category_list = categoryDAO.getCategoryList();
        return category_list;
    }

    public void saveCategory(final CategoryEntity categoryEntity){
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                categoryDAO.insertCategory(categoryEntity);
            }
        });
    }
}
