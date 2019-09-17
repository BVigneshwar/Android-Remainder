package com.vignesh.remainder.taskmodule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.vignesh.remainder.R;
import com.vignesh.remainder.databinding.TaskCardviewBinding;
import com.vignesh.remainder.entity.TaskEntity;

import java.util.LinkedList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder>{
    Context context;
    List<TaskEntity> task_list;
    TaskFragment.TaskHandler taskHandler;

    TaskAdapter(Context context, TaskFragment.TaskHandler taskHandler){
        this.context = context;
        this.taskHandler = taskHandler;
        this.task_list = new LinkedList<>();
    }

    void setTaskList(List<TaskEntity> new_task_list){
        if(task_list != null){
            TaskAdapter.DiffCallback diffCallback = new TaskAdapter.DiffCallback(task_list, new_task_list);
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
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        TaskCardviewBinding binding = DataBindingUtil.inflate(inflater, R.layout.task_cardview, parent,false);
        return new TaskAdapter.TaskViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.taskCardviewBinding.setTaskData(task_list.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class TaskViewHolder extends RecyclerView.ViewHolder{
        TaskCardviewBinding taskCardviewBinding;

        public TaskViewHolder(TaskCardviewBinding taskCardviewBinding) {
            super(taskCardviewBinding.getRoot());
            this.taskCardviewBinding = taskCardviewBinding;
        }
    }

    class DiffCallback extends DiffUtil.Callback{

        final List<TaskEntity> oldData, newData;

        DiffCallback(List<TaskEntity> oldData, List<TaskEntity> newData){
            this.oldData = oldData;
            this.newData = newData;
        }
        @Override
        public int getOldListSize() {
            return oldData.size();
        }

        @Override
        public int getNewListSize() {
            return newData.size();
        }

        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return oldData.get(oldItemPosition) == newData.get(newItemPosition);
        }

        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            return oldData.get(oldItemPosition).equals(newData.get(newItemPosition));
        }
    }
}
