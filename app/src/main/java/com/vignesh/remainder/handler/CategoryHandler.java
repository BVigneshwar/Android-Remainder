package com.vignesh.remainder.handler;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.vignesh.remainder.entity.CategoryEntity;

public class CategoryHandler {
    public void onCategoryListClick(Context context, CategoryEntity categoryEntity){
        Intent intent = new Intent();
        intent.putExtra("category_id", categoryEntity.getCategory_id());
        ((AppCompatActivity)context).setResult(1, intent);
        ((AppCompatActivity) context).finish();
    }
}
