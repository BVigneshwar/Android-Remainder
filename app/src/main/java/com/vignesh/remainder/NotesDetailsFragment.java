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
import android.widget.EditText;
import android.widget.Toast;

public class NotesDetailsFragment extends Fragment {
    String title, description;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notes_details, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title = getArguments().getString("title");
        description = getArguments().getString("description");
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText ed_title = getView().findViewById(R.id.notes_title);
        EditText ed_description = getView().findViewById(R.id.notes_description);
        if(title != null){
            ed_title.setText(title);
        }
        if(description != null){
            ed_description.setText(description);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_menu, menu);
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
        EditText et_title = (EditText) getView().findViewById(R.id.notes_title);
        EditText et_description = (EditText) getView().findViewById(R.id.notes_description);
        String title = et_title.getText().toString();
        String description = et_description.getText().toString();
        NotesDatabaseHandler notesDatabaseHandler = new NotesDatabaseHandler(getContext());
        if(title.length() > 0 || description.length() > 0){
            if(notesDatabaseHandler.insert(title, description)){
                Toast.makeText(getContext(), "Notes Saved", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(getContext(), "Error in saving", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
