package com.vignesh.remainder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vignesh.remainder.databinding.NotesCardviewBinding;
import com.vignesh.remainder.entity.NotesEntity;
import com.vignesh.remainder.entity.NotesWithCategory;

import java.util.ArrayList;
import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{
    Context context;
    List<NotesWithCategory> notes_list;

    public NotesAdapter(Context context){
        this.context = context;
        notes_list = new ArrayList<>();
    }

    public void setNotes(List<NotesWithCategory> newData){
        if(notes_list != null){
            DiffCallback diffCallback = new DiffCallback(notes_list, newData);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

            notes_list.clear();
            notes_list.addAll(newData);
            diffResult.dispatchUpdatesTo(this);
        }else{
            notes_list = newData;
        }
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
        final NotesWithCategory notes_data = notes_list.get(position);
        holder.notesCardviewBinding.setNotesDetail(notes_data);
        holder.notesCardviewBinding.setNotesHandler(new NotesHandler());

        if(notes_data.getCategory_color() != null){
            int color_drawable = AppConstants.color_map.get(notes_data.getCategory_color());
            holder.category_color.setBackground(context.getDrawable(color_drawable));
        }

    }

    @Override
    public int getItemCount() {
        return notes_list.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder{
        NotesCardviewBinding notesCardviewBinding;
        ImageView category_color;

        public NotesViewHolder(NotesCardviewBinding binding){
            super(binding.getRoot());
            category_color = binding.getRoot().findViewById(R.id.category_color);
            this.notesCardviewBinding = binding;
        }
    }

    class DiffCallback extends DiffUtil.Callback{

        final List<NotesWithCategory> oldData, newData;

        DiffCallback(List<NotesWithCategory> oldData, List<NotesWithCategory> newData){
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
            return oldData.get(oldItemPosition) == newData.get(newItemPosition);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldData.get(oldItemPosition).equals(newData.get(newItemPosition));
        }
    }
}
