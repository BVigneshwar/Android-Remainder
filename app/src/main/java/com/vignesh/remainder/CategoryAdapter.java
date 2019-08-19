package com.vignesh.remainder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.zip.Inflater;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    Context context;
    List<CategoryModel> category_list;
    public CategoryAdapter(Context context, List<CategoryModel> category_list){
        this.context = context;
        this.category_list = category_list;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_list, null);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.category_list_container.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                
            }
        });
    }

    @Override
    public int getItemCount() {
        return category_list.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        ViewGroup category_list_container;
        public CategoryViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.category_title);
            category_list_container = view.findViewById(R.id.category_list_container);
        }
    }
}
