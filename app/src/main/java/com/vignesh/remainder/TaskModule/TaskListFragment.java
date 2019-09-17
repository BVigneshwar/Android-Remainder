package com.vignesh.remainder.taskmodule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.vignesh.remainder.R;
import com.vignesh.remainder.entity.TaskListEntity;
import com.vignesh.remainder.viewmodel.TaskListViewModel;
import com.vignesh.remainder.viewmodel.TaskViewModel;

import java.util.List;

public class TaskListFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task_list, container, false);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        final TaskListAdapter taskListAdapter = new TaskListAdapter(getContext());
        TaskViewModel taskViewModel = ViewModelProviders.of(getActivity()).get(TaskViewModel.class);
        int task_id = taskViewModel.getSelectedTask();
        TaskListViewModel taskListViewModel = ViewModelProviders.of(getActivity()).get(TaskListViewModel.class);
        taskListViewModel.getAllTaskListFor(task_id).observe(this, new Observer<List<TaskListEntity>>() {
            @Override
            public void onChanged(List<TaskListEntity> taskListEntities) {
                taskListAdapter.setTaskList(taskListEntities);
            }
        });
        RecyclerView taskListRecyclerView = getView().findViewById(R.id.task_list_recycler_view);
        taskListRecyclerView.setAdapter(taskListAdapter);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.add_option, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_icon){

            return  true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
