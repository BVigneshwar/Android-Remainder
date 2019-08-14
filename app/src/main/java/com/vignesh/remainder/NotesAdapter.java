package com.vignesh.remainder;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder>{
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
    public void onBindViewHolder(@NonNull NotesViewHolder holder, final int position) {
        final NotesModel notes_data = notes_list.get(position);
        holder.title.setText(notes_data.getTitle());
        holder.description.setText(notes_data.getDescription());
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String title = notes_list.get(position).getTitle();
                String description = notes_list.get(position).getDescription();
                int id = notes_list.get(position).getId();
                Bundle bundle = new Bundle();
                bundle.putString("title", title);
                bundle.putString("description", description);
                bundle.putInt("id", id);
                NotesDetailsFragment notesFragment = new NotesDetailsFragment();
                notesFragment.setArguments(bundle);
                ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, notesFragment).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public int getItemCount() {
        return notes_list.size();
    }

    class NotesViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView description;
        CardView cardView;
        public NotesViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.notes_title);
            description = view.findViewById(R.id.notes_description);
            cardView = view.findViewById(R.id.notes_cardview);
        }
    }
}
