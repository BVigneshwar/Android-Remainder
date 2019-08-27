package com.vignesh.remainder;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class NotesDetailsFragment extends Fragment {
    String selected_title, selected_description, category_name, category_color;
    int selected_id, category_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_details, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selected_title = getArguments().getString("title");
        selected_description = getArguments().getString("description");
        selected_id = getArguments().getInt("id");
        category_id = getArguments().getInt("category_id");
        category_name = getArguments().getString("category_name");
        category_color = getArguments().getString("category_color");
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tv_title = view.findViewById(R.id.tv_notes_title);
        TextView tv_description = view.findViewById(R.id.tv_notes_description);
        TextView category_button = view.findViewById(R.id.category_button);

        if(selected_title != null){
            tv_title.setText(selected_title);
        }
        if(selected_title != null){
            tv_description.setText(selected_description);
        }
        if(category_name != null){
            category_button.setText(category_name);
            GradientDrawable gradientDrawable = (GradientDrawable) category_button.getBackground();
            int color = Color.parseColor(category_color);
            gradientDrawable.setStroke(2, color);
            category_button.setTextColor(color);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.edit_option, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.edit_menu){
            NotesEditFragment notesEditFragment = new NotesEditFragment();
            Bundle bundle = new Bundle();
            bundle.putString("title", selected_title);
            bundle.putString("description", selected_description);
            bundle.putInt("id", selected_id);
            notesEditFragment.setArguments(bundle);
            getFragmentManager().beginTransaction().replace(R.id.frame_layout, notesEditFragment).addToBackStack(null).commit();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
