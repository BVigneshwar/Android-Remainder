package com.vignesh.remainder;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        RecyclerView recyclerView = findViewById(R.id.category_recycler_view);
        List<CategoryModel> category_list = new ArrayList<>();
        NotesDatabaseHandler notesDatabaseHandler = new NotesDatabaseHandler(this);
        Cursor cursor = notesDatabaseHandler.getAllCategory();
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()){
                category_list.add(new CategoryModel(cursor.getInt(0), cursor.getString(1),cursor.getString(2)));
            }
        }
        category_list.add(new CategoryModel(0, getResources().getString(R.string.add_category), null));
        CategoryAdapter categoryAdapter = new CategoryAdapter(this, category_list);
        recyclerView.setAdapter(categoryAdapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra("category_id", 0);
        this.setResult(1, intent);
        this.finish();
    }
}
