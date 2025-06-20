package com.example.mymedreminder;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "meds.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "medications";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_DOSAGE = "dosage";
    private static final String COL_TIME = "time";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NAME + " TEXT, " +
                COL_DOSAGE + " TEXT, " +
                COL_TIME + " TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertMedication(String name, String dosage, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_NAME, name);
        values.put(COL_DOSAGE, dosage);
        values.put(COL_TIME, time);
        long result = db.insert(TABLE_NAME, null, values);
        return result != -1;
    }

    public Cursor getNextMedication() {
        SQLiteDatabase db = this.getReadableDatabase();
        // For simplicity, just get the last added medication
        return db.rawQuery("SELECT * FROM " + TABLE_NAME + " ORDER BY " + COL_ID + " DESC LIMIT 1", null);
    }
    public Cursor getAllMedications() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM medications", null);
    }
    public void deleteMedication(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("medications", "id=?", new String[]{String.valueOf(id)});
    }

}
