package com.vignesh.remainder.NotesModule;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
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
import com.vignesh.remainder.R;
import com.vignesh.remainder.databinding.FragmentNotesBinding;
import com.vignesh.remainder.handler.NotesHandlerInterface;
import com.vignesh.remainder.viewModel.NotesViewModel;

import java.util.List;


public class NotesFragment extends Fragment {
    FragmentNotesBinding binding;
    RecyclerView recyclerView;
    FloatingActionButton add_notes_btn;
    NotesFragment.NotesHandler notesHandler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
        notesHandler = new NotesHandler(this);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.delete_option, menu);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        add_notes_btn = (FloatingActionButton) view.findViewById(R.id.add_notes_button);

        final NotesAdapter notesAdapter = new NotesAdapter(getContext(), notesHandler);

        final NotesViewModel notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
        notesViewModel.getNotesWithCategory().observe(this, new Observer<List<NotesWithCategory>>() {
            @Override
            public void onChanged(List<NotesWithCategory> notes) {
                notesAdapter.setNotes(notes);
            }
        });
        binding.setNotesAdapter(notesAdapter);

        add_notes_btn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                NotesEditFragment notesEditFragment = new NotesEditFragment();
                NotesViewModel notesViewModel1 = ViewModelProviders.of(getActivity()).get(NotesViewModel.class);
                notesViewModel.getNewNotes();
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
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class NotesHandler implements NotesHandlerInterface{
        Fragment fragment;

        public NotesHandler(Fragment fragment){
            this.fragment = fragment;
        }

        @Override
        public void onCardClick(View v, NotesWithCategory notesData) {
            CheckBox delete_checkbox = (CheckBox) v.findViewById(R.id.delete_checkbox);
            if(delete_checkbox.getVisibility() == View.VISIBLE){
                delete_checkbox.setChecked(true);
            }else{
                NotesEditFragment notesEditFragment = new NotesEditFragment();
                NotesViewModel notesViewModel = ViewModelProviders.of(fragment.getActivity()).get(NotesViewModel.class);
                notesViewModel.setSelectedNotes(notesData);
                fragment.getFragmentManager().beginTransaction().replace(R.id.frame_layout, notesEditFragment).addToBackStack("notes_fragment").commit();
            }
        }

        @Override
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
            setHasOptionsMenu(true);
            return true;
        }
    }

}
