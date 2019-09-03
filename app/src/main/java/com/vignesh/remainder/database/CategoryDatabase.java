package com.vignesh.remainder.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.vignesh.remainder.DAO.CategoryDAO;
import com.vignesh.remainder.entity.CategoryEntity;

import java.util.concurrent.Executors;

@Database(entities = {CategoryEntity.class}, version = 1)
public abstract class CategoryDatabase extends RoomDatabase {

    private static CategoryDatabase categoryDatabase;

    public abstract CategoryDAO categoryDAO();

    static Object slock = new Object();

    public static CategoryDatabase getCategoryDatabase(final Context context){
        synchronized (slock){
            if(categoryDatabase == null){
                categoryDatabase = Room.databaseBuilder(context.getApplicationContext(), CategoryDatabase.class, "categorydatabase").addCallback(new Callback() {
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
                                getCategoryDatabase(context).categoryDAO().insertCategory(defaultCatgeory);
                            }
                        });
                    }
                }).build();
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
