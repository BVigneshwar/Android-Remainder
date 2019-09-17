package com.vignesh.remainder.taskmodule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vignesh.remainder.R;
import com.vignesh.remainder.databinding.TaskListCardviewBinding;
import com.vignesh.remainder.entity.TaskEntity;
import com.vignesh.remainder.entity.TaskListEntity;

import java.util.ArrayList;
import java.util.List;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.TaskListViewHolder> {
    Context context;
    List<TaskListEntity> task_list;

    TaskListAdapter(Context context){
        this.context = context;
        task_list = new ArrayList<>();
    }

    void setTaskList(List<TaskListEntity> new_task_list){
        if(task_list != null){
            TaskListAdapter.DiffCallback diffCallback = new TaskListAdapter.DiffCallback(task_list, new_task_list);
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);

            task_list.clear();
            task_list.addAll(new_task_list);
            diffResult.dispatchUpdatesTo(this);
        }else{
            task_list = new_task_list;
        }
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

    class DiffCallback extends DiffUtil.Callback {

        List<TaskListEntity> old_list, new_list;

        DiffCallback(List<TaskListEntity> old_list, List<TaskListEntity> new_list){
            this.old_list = old_list;
            this.new_list = new_list;
        }

        @Override
        public int getOldListSize() {
            return old_list.size();
        }

        @Override
        public int getNewListSize() {
            return new_list.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return old_list.get(oldItemPosition) == new_list.get(newItemPosition);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return old_list.get(oldItemPosition).equals(new_list.get(newItemPosition));
        }
    }
}
