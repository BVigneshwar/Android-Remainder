package com.vignesh.remainder.notesmodule;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.RadioGroup;
import android.widget.SearchView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.vignesh.remainder.AppConstants;
import com.vignesh.remainder.R;
import com.vignesh.remainder.common.SortHandler;
import com.vignesh.remainder.common.SortInterface;
import com.vignesh.remainder.databinding.FragmentNotesBinding;
import com.vignesh.remainder.viewmodel.NotesViewModel;

import java.util.List;


public class NotesFragment extends Fragment implements SortInterface {
    FragmentNotesBinding binding;
    FloatingActionButton add_notes_btn;
    NotesFragment.NotesHandler notesHandler;
    NotesAdapter notesAdapter;
    SortHandler sortByHandler;
    SharedPreferences sharedPreferences;
    String sort_by_shared_preference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notesHandler = new NotesHandler(this);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu_options, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        add_notes_btn = (FloatingActionButton) view.findViewById(R.id.add_notes_button);

        notesAdapter = new NotesAdapter(getContext(), notesHandler);

        sharedPreferences = getActivity().getSharedPreferences(AppConstants.shared_preference_key, Context.MODE_PRIVATE);
        sort_by_shared_preference = sharedPreferences.getString(AppConstants.notes_sort_by_preference, "notes_name asc");
        final NotesViewModel notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
        notesViewModel.getNotesWithCategory(sort_by_shared_preference).observe(this, new Observer<List<NotesWithCategory>>() {
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
                NotesViewModel notesViewModel = ViewModelProviders.of(getActivity()).get(NotesViewModel.class);
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
        if(item.getItemId() == R.id.sort_by_option){
            sortByHandler = new SortHandler(getContext(), new String[]{getResources().getString(R.string.title), getResources().getString(R.string.created_time), getResources().getString(R.string.last_modified)}, this);
            String[] sort = sort_by_shared_preference.split(" ");
            if(sort[0].equals("notes_name")){
                sort[0] = getResources().getString(R.string.title);
            }else if(sort[0].equals("created_time")){
                sort[0] = getResources().getString(R.string.created_time);
            }else if(sort[0].equals("last_modified")){
                sort[0] = getResources().getString(R.string.last_modified);
            }
            sortByHandler.createSortByDialog(sort[0], sort[1]);
            return true;
        }else if(item.getItemId() == R.id.delete_option){
            View v = this.getView();
            v.findViewById(R.id.notes_cardview).performLongClick();
            return true;
        } else if (item.getItemId() == R.id.search_option){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSortBySaveButtonClick() {
        String order = sortByHandler.getSelectedSortOrder();
        String sort_by = sortByHandler.getSelectedSortBy();
        if(sort_by.equals(getResources().getString(R.string.title))){
            sort_by = "notes_name";
        }else if(sort_by.equals(getResources().getString(R.string.created_time))){
            sort_by = "created_time";
        }else if(sort_by.equals(getResources().getString(R.string.last_modified))){
            sort_by = "last_modified";
        }
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(AppConstants.notes_sort_by_preference, sort_by+" "+order);
        editor.commit();
        NotesViewModel notesViewModel = ViewModelProviders.of(this).get(NotesViewModel.class);
        notesViewModel.getNotesWithCategory(sort_by+" "+order).observe(this, new Observer<List<NotesWithCategory>>() {
            @Override
            public void onChanged(List<NotesWithCategory> notes) {
                notesAdapter.setNotes(notes);
            }
        });
        notesAdapter.notifyDataSetChanged();
    }

    public class NotesHandler{
        Fragment fragment;

        public NotesHandler(Fragment fragment){
            this.fragment = fragment;
        }

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
            BottomNavigationView bottomNavigationView = v.getRootView().findViewById(R.id.bottom_nav);
            bottomNavigationView.setVisibility(View.GONE);
            return true;
        }
    }
}
