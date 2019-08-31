package com.vignesh.remainder;


import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vignesh.remainder.databinding.FragmentNotesBinding;
import com.vignesh.remainder.entity.NotesEntity;
import com.vignesh.remainder.entity.NotesWithCategory;
import com.vignesh.remainder.viewModel.NotesViewModel;

import java.util.ArrayList;
import java.util.List;


public class NotesFragment extends Fragment {
    FragmentNotesBinding binding;
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
        add_notes_btn = (FloatingActionButton) view.findViewById(R.id.add_notes_button);

        NotesAdapter notesAdapter = new NotesAdapter(getContext());

        NotesViewModel notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
        notesViewModel.getNotesWithCategory().observe(this, new Observer<List<NotesWithCategory>>() {
            @Override
            public void onChanged(List<NotesWithCategory> notes) {

            }
        });
        binding.setNotesAdapter(notesAdapter);

        add_notes_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                NotesEditFragment notesEditFragment = new NotesEditFragment();
                Bundle bundle = new Bundle();
                bundle.putString("title", null);
                bundle.putString("description", null);
                bundle.putInt("id", 0);
                notesEditFragment.setArguments(bundle);
                getFragmentManager().beginTransaction().replace(R.id.frame_layout, notesEditFragment).addToBackStack("notes_fragment").commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notes, container, false);
        View view = binding.getRoot();
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_button){
            deleteNotes();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /*private void getAllNotes(){
        NotesDatabaseHandler notesDatabaseHandler = new NotesDatabaseHandler(getContext());
        Cursor cursor = notesDatabaseHandler.getAllNotes();
        if(cursor.getCount() != 0){
            while (cursor.moveToNext()){
                notes_list.add(new NotesModel(cursor.getInt(0),cursor.getString(1), cursor.getString(2)));
            }
        }
    }*/

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
