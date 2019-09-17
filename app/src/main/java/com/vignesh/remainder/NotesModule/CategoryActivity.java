package com.vignesh.remainder.notesmodule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.vignesh.remainder.R;
import com.vignesh.remainder.entity.CategoryEntity;
import com.vignesh.remainder.viewmodel.CategoryViewModel;

import java.util.List;

public class CategoryActivity extends AppCompatActivity {
    CategoryAdapter categoryAdapter;
    RecyclerView recyclerView;
    CategoryViewModel categoryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        recyclerView = findViewById(R.id.category_recycler_view);
        categoryAdapter = new CategoryAdapter(this);
        categoryViewModel = ViewModelProviders.of(this).get(CategoryViewModel.class);
        categoryViewModel.getCategoryList().observe(this, new Observer<List<CategoryEntity>>() {
            @Override
            public void onChanged(List<CategoryEntity> category) {
                categoryAdapter.setData(category);
            }
        });
        recyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_icon){
            new CategoryDialog(this, categoryViewModel).openDialog();
            return true;
        }else{
            super.onOptionsItemSelected(item);
            return false;
        }
    }

    /*@Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        intent.putExtra("category_id", 0);
        this.setResult(1, intent);
        this.finish();
    }*/
}
