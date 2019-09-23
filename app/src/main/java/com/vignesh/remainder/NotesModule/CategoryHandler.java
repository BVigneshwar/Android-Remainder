package com.vignesh.remainder.notesmodule;

import android.content.Context;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.vignesh.remainder.entity.CategoryEntity;

public class CategoryHandler {
    public void onCategoryListClick(Context context, CategoryEntity categoryEntity){
        Intent intent = new Intent();
        intent.putExtra("category_id", categoryEntity.getCategory_id());
        intent.putExtra("category_name", categoryEntity.getCategory_name());
        intent.putExtra("category_color", categoryEntity.getCategory_color());
        intent.putExtra("is_deleted", categoryEntity.isIs_deleted());

        ((AppCompatActivity)context).setResult(1, intent);
        ((AppCompatActivity) context).finish();
    }
}
