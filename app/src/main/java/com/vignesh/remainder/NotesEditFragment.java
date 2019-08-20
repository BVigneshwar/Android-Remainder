package com.vignesh.remainder;

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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class NotesEditFragment extends Fragment {
    String selected_title, selected_description;
    int selected_id;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_edit, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        selected_title = getArguments().getString("title");
        selected_description = getArguments().getString("description");
        selected_id = getArguments().getInt("id");
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText et_title = getView().findViewById(R.id.et_notes_title);
        EditText et_description = getView().findViewById(R.id.et_notes_description);
        Button category_button = getView().findViewById(R.id.category_button);

        if(selected_title != null){
            et_title.setText(selected_title);
        }
        if(selected_title != null){
            et_description.setText(selected_description);
        }
        category_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame_layout, new CategoryFragment()).addToBackStack(null).commit();
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_option, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.save_menu){
            saveNotes();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveNotes(){
        EditText et_title = (EditText) getView().findViewById(R.id.et_notes_title);
        EditText et_description = (EditText) getView().findViewById(R.id.et_notes_description);
        String title = et_title.getText().toString();
        String description = et_description.getText().toString();
        NotesDatabaseHandler notesDatabaseHandler = new NotesDatabaseHandler(getContext());
        if(selected_id > 0){
            if(title.length() > 0 || description.length() > 0){
                if(notesDatabaseHandler.update(selected_id, title, description)){
                    Toast.makeText(getContext(), "Notes Edited", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Error in editing", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            if(title.length() > 0 || description.length() > 0){
                if(notesDatabaseHandler.insert(title, description)){
                    Toast.makeText(getContext(), "Notes Saved", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Error in saving", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
