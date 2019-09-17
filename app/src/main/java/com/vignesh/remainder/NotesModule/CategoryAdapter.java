package com.vignesh.remainder.notesmodule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vignesh.remainder.AppConstants;
import com.vignesh.remainder.R;
import com.vignesh.remainder.databinding.CategoryListBinding;
import com.vignesh.remainder.entity.CategoryEntity;
import com.vignesh.remainder.handler.CategoryHandler;

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
        CategoryListBinding binding = DataBindingUtil.inflate(inflater, R.layout.category_list, parent, false);
        return new CategoryViewHolder(binding);
    }


    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, final int position) {

        holder.categoryListBinding.setCategoryData(category_list.get(position));
        holder.categoryListBinding.setCategoryHandler(new CategoryHandler());
        holder.categoryListBinding.setContext(context);

        String color = category_list.get(position).getCategory_color();
        if(color != null){
            int color_drawable = AppConstants.color_map.get(color);
            holder.category_color.setBackground(context.getDrawable(color_drawable));
        }
    }

    @Override
    public int getItemCount() {
            return category_list.size();
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder{
        ImageView category_color;
        CategoryListBinding categoryListBinding;

        public CategoryViewHolder(CategoryListBinding binding){
            super(binding.getRoot());
            categoryListBinding = binding;
            category_color = binding.getRoot().findViewById(R.id.category_color);
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