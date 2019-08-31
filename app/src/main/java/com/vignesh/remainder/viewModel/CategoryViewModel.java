package com.vignesh.remainder.viewModel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.vignesh.remainder.DAO.CategoryDAO;
import com.vignesh.remainder.database.CategoryDatabase;
import com.vignesh.remainder.entity.CategoryEntity;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CategoryViewModel extends AndroidViewModel {
    private CategoryDAO categoryDAO;
    private ExecutorService executorService;

    public CategoryViewModel(Application application){
        super(application);
        categoryDAO = CategoryDatabase.getCategoryDatabase(application).categoryDAO();
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
