package com.vignesh.remainder.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.vignesh.remainder.DAO.NotesDAO;
import com.vignesh.remainder.entity.CategoryEntity;
import com.vignesh.remainder.entity.NotesEntity;

import java.util.concurrent.Executors;

@Database(entities = {NotesEntity.class, CategoryEntity.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    private static NotesDatabase notesDatabase;

    public abstract NotesDAO notesDAO();

    static Object slock = new Object();

    public static NotesDatabase getNotesDatabase(final Context context){
        synchronized (slock){
            if(notesDatabase == null){
                notesDatabase = Room.databaseBuilder(context.getApplicationContext(), NotesDatabase.class, "personal_db").allowMainThreadQueries().addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                CategoryEntity defaultCatgeory = new CategoryEntity();
                                defaultCatgeory.setCategory_name("Uncategorized");
                                defaultCatgeory.setCategory_color("#9e9e9e");
                                defaultCatgeory.setIs_deleted(false);
                                getNotesDatabase(context).notesDAO().insertCategory(defaultCatgeory);
                            }
                        });
                    }
                }).build();
            }
            return notesDatabase;
        }
    }
}
