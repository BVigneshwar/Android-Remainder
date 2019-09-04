package com.vignesh.remainder.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.vignesh.remainder.entity.CategoryEntity;
import com.vignesh.remainder.entity.NotesEntity;
import com.vignesh.remainder.entity.NotesWithCategory;

import java.util.List;

@Dao
public interface NotesDAO {

    @Query("SELECT * FROM notes_table")
    LiveData<List<NotesEntity>> getAllNotes();

    @Query("SELECT notes_table.notes_id, notes_table.notes_name, notes_table.notes_description, notes_table.category_id, notes_table.created_time, notes_table.last_modified, category_table.category_name, category_table.category_color FROM notes_table JOIN category_table ON notes_table.category_id = category_table.category_id")
    LiveData<List<NotesWithCategory>> getAllNotesWithCategory();

    @Insert
    void insertNotes(NotesWithCategory notesWithCategory);
}
