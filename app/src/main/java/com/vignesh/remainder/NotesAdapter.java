package com.vignesh.remainder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {
    Context context;
    List<NotesModel> notes_list;

    public NotesAdapter(Context context, List<NotesModel> notes_list){
        this.context = context;
        this.notes_list = notes_list;
    }

    @NonNull
    @Override
    public NotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.layout_notes_cardview, null);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
        NotesModel notes_data = notes_list.get(position);
        holder.title.setText(notes_data.getTitle());
        holder.description.setText(notes_data.getDescription());
    }

    @Override
    public int getItemCount() {
        return notes_list.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView description;
        public NotesViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.notes_title);
            description = view.findViewById(R.id.notes_description);
        }
    }
}
