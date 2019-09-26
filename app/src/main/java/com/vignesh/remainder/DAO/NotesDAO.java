package com.vignesh.remainder.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.vignesh.remainder.entity.NotesEntity;
import com.vignesh.remainder.notesmodule.NotesWithCategory;

import java.util.List;

@Dao
public interface NotesDAO {

    @Query("SELECT * FROM notes_table")
    LiveData<List<NotesEntity>> getAllNotes();

    @Query("SELECT notes_table.notes_id, notes_table.notes_name, notes_table.notes_description, notes_table.category_id, notes_table.created_time, notes_table.last_modified, category_table.category_name, category_table.category_color FROM notes_table JOIN category_table ON notes_table.category_id = category_table.category_id ORDER BY :sort_by_field")
    LiveData<List<NotesWithCategory>> getAllNotesWithCategory(String sort_by_field);

    @Insert
    void insertNotes(NotesEntity notesEntity);

    @Update
    void updateNotes(NotesEntity notesEntity);

    @Query("SELECT * FROM notes_table WHERE notes_name =:name")
    LiveData<NotesEntity> getSearchResultForNotes(String name);

}
