package com.vignesh.remainder;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    Context context;
    List<CategoryEntity> category_list;

    public CategoryAdapter(Context context){
        this.context = context;
        this.category_list = new ArrayList<>();
    }

    public void setData(List<CategoryEntity> newData){
        if(category_list != null){
            DiffCallback diffCallback = new DiffCallback(category_list, newData);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

            category_list.clear();
            category_list.addAll(newData);
            diffResult.dispatchUpdatesTo(this);
        }else{
            category_list = newData;
        }
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.category_list, null);
        return new CategoryViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position) {
        holder.category_title.setText(category_list.get(position).getCategory_name());
        String color = category_list.get(position).getCategory_color();
        if(color != null){
            int color_drawable = AppConstants.color_map.get(color);
            holder.category_color.setBackground(context.getDrawable(color_drawable));
            holder.category_list_container.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra("category_id", category_list.get(position).getCategory_id());
                    ((AppCompatActivity)context).setResult(1, intent);
                    ((AppCompatActivity) context).finish();
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

    class DiffCallback extends DiffUtil.Callback{

        private final List<CategoryEntity> oldData, newData;

        public DiffCallback(List<CategoryEntity> oldData, List<CategoryEntity> newData){
            this.oldData = oldData;
            this.newData = newData;
        }

        @Override
        public int getOldListSize() {
            return oldData.size();
        }

        @Override
        public int getNewListSize() {
            return newData.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldData.get(oldItemPosition).getCategory_id() == newData.get(newItemPosition).getCategory_id();
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldData.get(oldItemPosition).equals(newData.get(newItemPosition));
        }
    }
}