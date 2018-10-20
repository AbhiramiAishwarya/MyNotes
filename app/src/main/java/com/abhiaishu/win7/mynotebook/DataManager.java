package com.abhiaishu.win7.mynotebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DataManager {

    private datahelp dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DataManager(Context c) {

        context = c;
    }

    public DataManager open() throws SQLException {
        dbHelper = new datahelp(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert(String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(datahelp.SUBJECT, name);
        contentValue.put(datahelp.DESC, desc);
        database.insert(datahelp.TABLE_NAME, null, contentValue);
    }

    public Cursor fetch() {
        String[] columns = new String[] { datahelp._ID, datahelp.SUBJECT, datahelp.DESC };
        Cursor cursor = database.query(datahelp.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String desc) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(datahelp.SUBJECT, name);
        contentValues.put(datahelp.DESC, desc);
        int i = database.update(datahelp.TABLE_NAME, contentValues, datahelp._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(datahelp.TABLE_NAME, datahelp._ID + "=" + _id, null);
    }


}
