package com.vignesh.remainder.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vignesh.remainder.entity.CategoryEntity;

import java.util.List;

@Dao
public interface CategoryDAO {
    @Query("SELECT * FROM category_table")
    LiveData<List<CategoryEntity>> getCategoryList();

    @Insert
    long insertCategory(CategoryEntity categoryEntity);

    @Query("SELECT * FROM category_table WHERE category_name = 'Uncategorized'")
    CategoryEntity getDefaultCatgeory();
}
