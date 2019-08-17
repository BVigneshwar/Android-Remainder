package com.vignesh.remainder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.SimpleDateFormat;
import java.util.Date;

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
        String create_category_table = "CREATE TABLE category_table (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name TEXT);";
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

    boolean insert(String title, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        values.put(NotesDatabaseHandler.title, title);
        values.put(NotesDatabaseHandler.description, description);
        values.put(NotesDatabaseHandler.created_time, dateFormat.format(date));
        values.put(NotesDatabaseHandler.last_updated, dateFormat.format(date));
        long result = db.insert(table, null, values);
        return result == -1? false : true;
    }

    boolean update(int id, String title, String description){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NotesDatabaseHandler.title, title);
        values.put(NotesDatabaseHandler.description, description);
        int result = db.update(table, values, "id = ?", new String[]{String.valueOf(id)});
        return result == -1 ? false : true;
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+table, null);
        return res;
    }
    public boolean deleteNotes(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(table, "id = ?", new String[]{ String.valueOf(id)});
        return result == -1 ? false : true;
    }
}
