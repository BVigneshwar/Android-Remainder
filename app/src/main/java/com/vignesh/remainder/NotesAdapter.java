package com.vignesh.remainder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> implements View.OnClickListener{
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
        holder.cardView.setOnClickListener(this);
    }

    @Override
    public int getItemCount() {
        return notes_list.size();
    }

    @Override
    public void onClick(View v) {
        ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new NotesDetailsFragment()).addToBackStack(null).commit();
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
