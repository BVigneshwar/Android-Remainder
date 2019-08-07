package com.vignesh.remainder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesDatabaseHandler extends SQLiteOpenHelper {
    Context context;
    static String DB_name = "Notes_DB";
    static int DB_version = 1;
    static String table = "Notes_table";
    static String id = "id";
    static String title = "title";
    static String description = "description";
    static String category = "category";
    static String created_time = "created_time";
    static String last_updated = "last_updated";

    NotesDatabaseHandler(Context context){
        super(context, DB_name, null, DB_version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_category_table = "CREATE TABLE category_table (id INTEGER PRIMARY KEY, name TEXT);";
        db.execSQL(create_category_table);
        String create_notes_table = "CREATE TABLE " + table + "("
                + id + " INTEGER PRIMARY KEY," + title + " TEXT,"
                + description + " TEXT," + category +" INTEGER, "+ created_time +" DATETIME, "+ last_updated +" DATETIME, FOREIGN KEY("+ category +") REFERENCES category_table(id))";
        db.execSQL(create_notes_table);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table);
        onCreate(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table);
        onCreate(db);
    }
}
