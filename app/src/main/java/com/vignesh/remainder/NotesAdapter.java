package com.vignesh.remainder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.vignesh.remainder.databinding.NotesCardviewBinding;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{
    Context context;
    List<NotesModel> notes_list;
    Fragment fragment;

    public NotesAdapter(Context context, List<NotesModel> notes_list, Fragment fragment){
        this.context = context;
        this.notes_list = notes_list;
        this.fragment = fragment;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        NotesCardviewBinding binding = DataBindingUtil.inflate(inflater, R.layout.notes_cardview, parent,false);
        return new NotesViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotesViewHolder holder, final int position) {
        final NotesModel notes_data = notes_list.get(position);
        holder.binding.setNotesDetail(notes_data);
        holder.binding.setNotesHandler(new NotesHandler());

        if(notes_data.getCategoryColor() != null){
            int color_drawable = AppConstants.color_map.get(notes_data.getCategoryColor());
            holder.category_color.setBackground(context.getDrawable(color_drawable));
        }

    }

    @Override
    public int getItemCount() {
        return notes_list.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder{
        NotesCardviewBinding binding;
        ImageView category_color;

        public NotesViewHolder(NotesCardviewBinding binding){
            super(binding.getRoot());
            category_color = binding.getRoot().findViewById(R.id.category_color);
            this.binding = binding;
        }
    }
}
