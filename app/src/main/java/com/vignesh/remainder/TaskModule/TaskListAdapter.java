package com.vignesh.remainder.TaskModule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vignesh.remainder.R;
import com.vignesh.remainder.databinding.TaskListCardviewBinding;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder> {
    Context context;

    TaskListAdapter(Context context){
        this.context = context;
    }

    @NonNull
    @Override
    public TaskListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        TaskListCardviewBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.task_list_cardview, parent, false);
        return new TaskListViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TaskListViewHolder extends RecyclerView.ViewHolder{
        TaskListCardviewBinding binding;

        public TaskListViewHolder(@NonNull TaskListCardviewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
