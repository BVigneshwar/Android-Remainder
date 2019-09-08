package com.vignesh.remainder.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.vignesh.remainder.DAO.CategoryDAO;
import com.vignesh.remainder.DAO.NotesDAO;
import com.vignesh.remainder.DAO.TaskDAO;
import com.vignesh.remainder.DAO.TaskListDAO;
import com.vignesh.remainder.entity.CategoryEntity;
import com.vignesh.remainder.entity.NotesEntity;
import com.vignesh.remainder.entity.TaskEntity;
import com.vignesh.remainder.entity.TaskListEntity;

import java.util.concurrent.Executors;

@Database(entities = {NotesEntity.class, CategoryEntity.class, TaskEntity.class, TaskListEntity.class}, version = 1)
public abstract class NotesDatabase extends RoomDatabase {
    private static NotesDatabase notesDatabase;

    public abstract NotesDAO notesDAO();
    public abstract CategoryDAO categoryDAO();

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
                                getNotesDatabase(context).categoryDAO().insertCategory(defaultCatgeory);
                            }
                        });
                    }
                }).build();
            }
            return notesDatabase;
        }
    }
}
