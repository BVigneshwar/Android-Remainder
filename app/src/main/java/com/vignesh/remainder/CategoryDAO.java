package com.vignesh.remainder;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CategoryDAO {

    @Query("SELECT * FROM category_table")
    LiveData<List<CategoryEntity>> getCategoryList();

    @Insert
    long insertCatgeory(CategoryEntity categoryEntity);
}
