package com.vignesh.remainder;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

@Database(entities = {CategoryEntity.class}, version = 1)
public abstract class CategoryDatabase extends RoomDatabase {

    private static CategoryDatabase categoryDatabase;

    public abstract CategoryDAO categoryDAO();

    static Object slock = new Object();

    public static CategoryDatabase getCategoryDatabase(Context context){
        synchronized (slock){
            if(categoryDatabase == null){
                categoryDatabase = Room.databaseBuilder(context.getApplicationContext(), CategoryDatabase.class, "personal-assistant-database").allowMainThreadQueries().build();
            }
            return categoryDatabase;
        }
    }

    @NonNull
    @Override
    protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration config) {
        return null;
    }

    @NonNull
    @Override
    protected InvalidationTracker createInvalidationTracker() {
        return null;
    }

    @Override
    public void clearAllTables() {

    }
}
