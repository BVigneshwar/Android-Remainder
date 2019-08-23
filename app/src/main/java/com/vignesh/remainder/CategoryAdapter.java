package com.vignesh.remainder;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
        holder.category_list_container.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(position == 0){
                    Dialog dialog = new Dialog(context, R.style.dialog_style);
                    dialog.setContentView(R.layout.category_dialog);
                    dialog.setTitle(context.getResources().getString(R.string.add_category));
                    GridView color_gridview = dialog.findViewById(R.id.color_gridview);
                    color_gridview.setAdapter(new ColorGridViewAdapter(context, AppConstants.category_color_list));
                    dialog.show();

                    WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
                    layoutParams.copyFrom(dialog.getWindow().getAttributes());
                    layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
                    dialog.getWindow().setAttributes(layoutParams);
                    color_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            EditText et_category_title = view.findViewById(R.id.et_category_title);
                            String title = et_category_title.getText().toString();
                            NotesDatabaseHandler notesDatabaseHandler = new NotesDatabaseHandler(context);
                            if(notesDatabaseHandler.insertCategory(title, "#FFFFFF"))
                                Toast.makeText(context, "Category added successfully", Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(context, "category addition failed", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return category_list.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{
        TextView category_title;
        ViewGroup category_list_container;
        public CategoryViewHolder(View view){
            super(view);
            category_title = view.findViewById(R.id.category_title);
            category_list_container = view.findViewById(R.id.category_list_container);
        }
    }
}