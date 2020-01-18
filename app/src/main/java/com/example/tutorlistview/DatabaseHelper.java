package com.example.tutorlistview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mylist.db";
    public static final String TABLE_NAME = "mylist_data";
    public static final String TABLE_ID = "ID";
    public static final String TABLE_ITEM_TEXT = "ITEM1";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " ( "+ TABLE_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TABLE_ITEM_TEXT + "TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);
    }

    public boolean addData(String item1){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(TABLE_ITEM_TEXT, item1);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1)
            return false;

        return true;
    }

//    public String getText(String text, Context ctx){
//        SQLiteDatabase db = this.getWritableDatabase();
//        String[] select = {TABLE_ITEM_TEXT};
//        String where = TABLE_ITEM_TEXT + " = " + text;
////        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + TABLE_ID + "  " + position;
//        Cursor data = db.query(TABLE_NAME, select, where, null, null, null, null);
//        String ret="";
//        while(data.moveToNext()){
//            Toast.makeText(ctx,data.getString(0), Toast.LENGTH_LONG).show();
//            ret=data.getString(0);
//        }
//        ret.trim();
//        return ret;
//    }

    public void deleteRow(String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] args = {text};
        db.delete(TABLE_NAME, TABLE_ITEM_TEXT + " = ?", args);
    }

    public void editRow(String oldText, String newText) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TABLE_ITEM_TEXT, newText);
        String[] args = {oldText};
        String whereClause = TABLE_ITEM_TEXT + " = ?";
        db.update(TABLE_NAME, cv, whereClause, args);

    }

    public Cursor getSearch(String text) {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] from = {TABLE_ID, TABLE_ITEM_TEXT};
        String whereClause = TABLE_ITEM_TEXT + " LIKE '%" + text + "%' ";
        Cursor data = db.query(TABLE_NAME, from, whereClause, null, null, null, null);
        return data;
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] from = {TABLE_ID, TABLE_ITEM_TEXT};
        Cursor data = db.query(TABLE_NAME, from, null, null, null, null, null);
        return data;
    }

}
