package com.vignesh.remainder.handler;

import android.view.View;

import com.vignesh.remainder.NotesModel.NotesWithCategory;

public interface NotesHandlerInterface {
    void onCardClick(View v, NotesWithCategory notesData);

    boolean onCardLongClick(View v);
}
