package com.vignesh.remainder;

import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.CheckBox;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

public class NotesHandler {
    public void onCardClick(View v, NotesModel notesData) {
        CheckBox delete_checkbox = (CheckBox) v.findViewById(R.id.delete_checkbox);
        if(delete_checkbox.getVisibility() == View.VISIBLE){
            delete_checkbox.setChecked(true);
        }else{
            Bundle bundle = new Bundle();
            bundle.putString("title", notesData.getTitle());
            bundle.putString("description", notesData.getDescription());
            bundle.putInt("id", notesData.getId());
            bundle.putInt("category_id", notesData.getCategory_id());
            bundle.putString("category_name", notesData.getCategory_name());
            bundle.putString("category_color", notesData.getCategory_color());

            NotesDetailsFragment notesDetailsFragment = new NotesDetailsFragment();
            notesDetailsFragment.setArguments(bundle);
            ((FragmentActivity)v.getContext()).getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, notesDetailsFragment).addToBackStack("notes_detail").commit();
        }
    }
    public boolean onCardLongClick(View v) {
        RecyclerView recyclerView = (RecyclerView)(v.getParent().getParent());
        TransitionManager.beginDelayedTransition(recyclerView);
        for(int i=0; i<recyclerView.getChildCount(); i++) {
            View cardview = recyclerView.getChildAt(i);
            CheckBox checkBox = cardview.findViewById(R.id.delete_checkbox);
            if (checkBox.getVisibility() == View.GONE) {
                checkBox.setVisibility(View.VISIBLE);
            }
        }
        //fragment.setHasOptionsMenu(true);
        return true;
    }
}
