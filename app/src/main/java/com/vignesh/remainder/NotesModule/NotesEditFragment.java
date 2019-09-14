package com.vignesh.remainder.NotesModule;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vignesh.remainder.R;
import com.vignesh.remainder.databinding.FragmentNotesEditBinding;
import com.vignesh.remainder.viewModel.NotesViewModel;

public class NotesEditFragment extends Fragment {
    TextView category_button;
    NotesWithCategory notesWithCategory;
    NotesViewModel notesViewModel;
    FragmentNotesEditBinding fragmentNotesEditBinding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        fragmentNotesEditBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.fragment_notes_edit, container, false);
        View root = fragmentNotesEditBinding.getRoot();
        return root;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        notesViewModel = ViewModelProviders.of(getActivity()).get(NotesViewModel.class);
        notesViewModel.getSelectedNotes().observe(this, new Observer<NotesWithCategory>() {
            @Override
            public void onChanged(NotesWithCategory notes) {
                notesWithCategory = notes;
                fragmentNotesEditBinding.setNotesDetail(notes);
            }
        });

        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        category_button = getView().findViewById(R.id.category_button);

        category_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CategoryActivity.class);
                startActivityForResult(intent, 1);
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
            notesViewModel.saveNotes(notesViewModel.getSelectedNotes().getValue());
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && data != null){
             notesWithCategory.setCategory_id(data.getIntExtra("category_id", 0));
             notesWithCategory.setCategory_name(data.getStringExtra("category_name"));
             notesWithCategory.setCategory_color(data.getStringExtra("category_color"));
        }
    }
}
