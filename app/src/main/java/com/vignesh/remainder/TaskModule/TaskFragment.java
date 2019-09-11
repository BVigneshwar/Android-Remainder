package com.vignesh.remainder.TaskModule;

import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.vignesh.remainder.NotesModel.NotesWithCategory;
import com.vignesh.remainder.R;
import com.vignesh.remainder.entity.TaskEntity;
import com.vignesh.remainder.handler.TaskHandlerInterface;
import com.vignesh.remainder.viewModel.TaskViewModel;

import java.util.List;

public class TaskFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TaskFragment.TaskHandler taskHandler = new TaskHandler();
        final TaskAdapter taskAdapter = new TaskAdapter(getContext(), taskHandler);
        TaskViewModel taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        taskViewModel.getAllTask().observe(this, new Observer<List<TaskEntity>>() {
            @Override
            public void onChanged(List<TaskEntity> taskEntities) {
                taskAdapter.setTaskList(taskEntities);
            }
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.delete_option, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_button){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public class TaskHandler implements TaskHandlerInterface{

        @Override
        public void onCardClick(View v, NotesWithCategory notesData) {
            CheckBox delete_checkbox = (CheckBox) v.findViewById(R.id.delete_checkbox);
            if(delete_checkbox.getVisibility() == View.VISIBLE){
                delete_checkbox.setChecked(true);
            }else{

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
