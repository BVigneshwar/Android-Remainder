package com.vignesh.remainder.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.vignesh.remainder.DAO.NotesDAO;
import com.vignesh.remainder.entity.CategoryEntity;
import com.vignesh.remainder.entity.NotesEntity;
import com.vignesh.remainder.entity.NotesWithCategory;

@Database(entities = {NotesEntity.class, CategoryEntity.class, NotesWithCategory.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    private static NotesDatabase notesDatabase;

    public abstract NotesDAO notesDAO();

    static Object slock = new Object();

    public static NotesDatabase getCategoryDatabase(Context context){
        synchronized (slock){
            if(notesDatabase == null){
                notesDatabase = Room.databaseBuilder(context.getApplicationContext(), NotesDatabase.class, "notes_database").allowMainThreadQueries().build();
            }
            return notesDatabase;
        }
    }
}
