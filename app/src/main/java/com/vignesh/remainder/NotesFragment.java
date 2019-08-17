package com.vignesh.remainder;


import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class NotesFragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton add_notes_btn;
    NotesDatabaseHandler notesDatabaseHandler;
    List<NotesModel> notes_list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notesDatabaseHandler = new NotesDatabaseHandler(getContext());
        setHasOptionsMenu(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.delete_option, menu);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.notes_recycler_view);
        add_notes_btn = (FloatingActionButton) view.findViewById(R.id.add_notes_button);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        notes_list = new ArrayList<>();
        /*list.add(new NotesModel("Hi", "hello"));
        list.add(new NotesModel("Hi", "hello"));
        list.add(new NotesModel("Hi", "hello"));*/

        final NotesAdapter notesAdapter = new NotesAdapter(getContext(), notes_list, this);
        recyclerView.setAdapter(notesAdapter);
        add_notes_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                NotesDetailsFragment notesDetailsFragment = new NotesDetailsFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", null);
                bundle.putString("description", null);
                bundle.putInt("id", 0);
                notesDetailsFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frame_layout, notesDetailsFragment).addToBackStack(null).commit();
            }
        });

        getAllNotes();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_button){
            deleteNotes();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void getAllNotes(){
        NotesDatabaseHandler notesDatabaseHandler = new NotesDatabaseHandler(getContext());
        Cursor cursor = notesDatabaseHandler.getAllData();
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()){
                notes_list.add(new NotesModel(cursor.getInt(0),cursor.getString(1), cursor.getString(2)));
            }
        }
    }

    private void deleteNotes(){
        NotesDatabaseHandler notesDatabaseHandler = new NotesDatabaseHandler(getContext());
        for(int list_count=0; list_count<recyclerView.getChildCount(); list_count++){
            View view = recyclerView.getChildAt(list_count);
            CheckBox checkBox = view.findViewById(R.id.delete_checkbox);
            if(checkBox.isChecked()){
                notesDatabaseHandler.deleteNotes(notes_list.get(list_count).getId());
            }
        }
        getFragmentManager().beginTransaction().replace(R.id.frame_layout, new NotesFragment()).commit();
    }
}
