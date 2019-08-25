package com.vignesh.remainder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

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
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position) {
        holder.category_title.setText(category_list.get(position).getTitle());
        String color = category_list.get(position).getColor();
        if(color != null){
            int color_drawable = AppConstants.color_map.get(color);
            holder.category_color.setBackground(context.getDrawable(color_drawable));
            holder.category_list_container.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("category_id", category_list.get(position).getId());
                    ((AppCompatActivity)context).setResult(1, intent);
                    ((AppCompatActivity) context).finish();
                }
            });
        }else{
            holder.category_color.setBackground(context.getDrawable(R.drawable.plus_icon));
            holder.category_list_container.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    new CategoryDialog(context).openDialog();
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return category_list.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView category_title;
        ViewGroup category_list_container;
        ImageView category_color;

        public CategoryViewHolder(View view){
            super(view);
            category_title = view.findViewById(R.id.category_title);
            category_list_container = view.findViewById(R.id.category_list_container);
            category_color = view.findViewById(R.id.category_color);
        }
    }
}