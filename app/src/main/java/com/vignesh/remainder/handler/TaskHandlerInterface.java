package com.vignesh.remainder.handler;

import android.view.View;

import com.vignesh.remainder.entity.TaskEntity;

public interface TaskHandlerInterface {
    void onCardClick(View v, TaskEntity taskEntity);

    boolean onCardLongClick(View v);
}
