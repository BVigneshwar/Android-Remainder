package com.vignesh.remainder;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.vignesh.remainder.databinding.FragmentNotesEditBinding;
import com.vignesh.remainder.entity.NotesWithCategory;
import com.vignesh.remainder.viewModel.NotesViewModel;

import java.util.List;

public class NotesEditFragment extends Fragment {
    String selected_title, selected_description;
    int selected_id;
    TextView category_button;
    NotesWithCategory notesWithCategory;

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
        /*NotesViewModel notesViewModel = ViewModelProviders.of(getActivity()).get(NotesViewModel.class);
        notesViewModel.getNotesWithCategory().observe(this, new Observer<List<NotesWithCategory>>() {
            @Override
            public void onChanged(List<NotesWithCategory> notesWithCategories) {

            }
        });*/

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
            saveNotes();
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

    private void saveNotes(){
        /*EditText et_title = (EditText) getView().findViewById(R.id.et_notes_title);
        EditText et_description = (EditText) getView().findViewById(R.id.et_notes_description);
        String title = et_title.getText().toString();
        String description = et_description.getText().toString();

        NotesDatabaseHandler notesDatabaseHandler = new NotesDatabaseHandler(getContext());
        if(selected_id > 0){
            if(title.length() > 0 || description.length() > 0){
                if(notesDatabaseHandler.update(selected_id, title, description, selected_category)){
                    Toast.makeText(getContext(), "Notes Edited", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Error in editing", Toast.LENGTH_SHORT).show();
                }
            }
        }else{
            if(title.length() > 0 || description.length() > 0){
                if(notesDatabaseHandler.insert(title, description, selected_category)){
                    Toast.makeText(getContext(), "Notes Saved", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Error in saving", Toast.LENGTH_SHORT).show();
                }
            }
        }*/
    }
}
