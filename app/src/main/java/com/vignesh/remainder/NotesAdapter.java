package com.vignesh.remainder;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

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
        View view = inflater.inflate(R.layout.layout_notes_cardview, null);
        return new NotesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NotesViewHolder holder, final int position) {
        final NotesModel notes_data = notes_list.get(position);
        holder.title.setText(notes_data.getTitle());
        holder.description.setText(notes_data.getDescription());
        if(notes_data.getCategoryColor() != null){
            int color_drawable = AppConstants.color_map.get(notes_data.getCategoryColor());
            holder.category_color.setBackground(context.getDrawable(color_drawable));
        }
        holder.cardView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if(holder.delete_checkbox.getVisibility() == View.VISIBLE){
                    holder.delete_checkbox.setChecked(true);
                }else{
                    String title = notes_list.get(position).getTitle();
                    String description = notes_list.get(position).getDescription();
                    int id = notes_list.get(position).getId();
                    Bundle bundle = new Bundle();
                    bundle.putString("title", title);
                    bundle.putString("description", description);
                    bundle.putInt("id", id);
                    NotesDetailsFragment notesDetailsFragment = new NotesDetailsFragment();
                    notesDetailsFragment.setArguments(bundle);
                    ((FragmentActivity)context).getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, notesDetailsFragment).addToBackStack("notes_detail").commit();
                }
            }
        });
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener(){
            @Override
            public boolean onLongClick(View v) {
                setVisibilityForCheckBox();
                return true;
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
        CheckBox delete_checkbox;
        ImageView category_color;
        ViewGroup transition_container;

        public NotesViewHolder(View view){
            super(view);
            title = view.findViewById(R.id.notes_title);
            description = view.findViewById(R.id.notes_description);
            cardView = view.findViewById(R.id.notes_cardview);
            delete_checkbox = view.findViewById(R.id.delete_checkbox);
            transition_container = view.findViewById(R.id.transition_container);
            category_color = view.findViewById(R.id.category_color);
        }
    }

    void setVisibilityForCheckBox(){
        View view = fragment.getView();
        RecyclerView recyclerView = view.findViewById(R.id.notes_recycler_view);
        TransitionManager.beginDelayedTransition(recyclerView);
        for(int i=0; i<recyclerView.getChildCount(); i++) {
            View cardview = recyclerView.getChildAt(i);
            CheckBox checkBox = cardview.findViewById(R.id.delete_checkbox);
            if (checkBox.getVisibility() == View.GONE) {
                checkBox.setVisibility(View.VISIBLE);
            }
        }
        fragment.setHasOptionsMenu(true);
    }
}
