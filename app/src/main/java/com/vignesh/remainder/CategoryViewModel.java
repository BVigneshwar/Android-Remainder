package com.vignesh.remainder;

import android.database.Cursor;
import android.icu.util.ULocale;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class CategoryViewModel extends ViewModel {
    private MutableLiveData<List<CategoryModel>> mutableLiveData;

    public MutableLiveData<List<CategoryModel>> getCategoryList(){
        if(mutableLiveData == null){
            mutableLiveData = new MutableLiveData<>();
            populateCatgeoryList();
        }
        return mutableLiveData;
    }

    private void populateCatgeoryList(){
        List<CategoryModel>  category_list = new ArrayList<>();
        //To-do populate category list
        mutableLiveData.setValue(category_list);
    }
}
