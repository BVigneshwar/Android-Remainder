package com.vignesh.remainder;


import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class NotesFragment extends Fragment {
    RecyclerView recyclerView;
    FloatingActionButton add_notes_btn;
    NotesDatabaseHandler notesDatabaseHandler;
    List<NotesModel> list;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notesDatabaseHandler = new NotesDatabaseHandler(getContext());
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.notes_recycler_view);
        add_notes_btn = (FloatingActionButton) view.findViewById(R.id.add_notes_button);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        list = new ArrayList<>();
        /*list.add(new NotesModel("Hi", "hello"));
        list.add(new NotesModel("Hi", "hello"));
        list.add(new NotesModel("Hi", "hello"));*/

        NotesAdapter notesAdapter = new NotesAdapter(getContext(), list);
        recyclerView.setAdapter(notesAdapter);

        add_notes_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().replace(R.id.frame_layout, new NotesDetailsFragment()).addToBackStack(null).commit();
            }
        });

        getAllNotes();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        return inflater.inflate(R.layout.fragment_notes, container, false);
    }

    private void getAllNotes(){
        NotesDatabaseHandler notesDatabaseHandler = new NotesDatabaseHandler(getContext());
        Cursor cursor = notesDatabaseHandler.getAllData();
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()){
                list.add(new NotesModel(cursor.getString(1), cursor.getString(2)));
            }
        }
    }
}
