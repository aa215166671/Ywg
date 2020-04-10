package com.kyun.android.baisibudejie.pro.essense.view.essence_all.SD_file;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;


public class WareDao {
    private DbHelper dbHelper;

    public WareDao(Context context) {
        dbHelper = new DbHelper(context);
    }

    public void insert(Ware ware) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", ware.getWarename());
        long id = db.insert("ware", null, contentValues);
        ware.setId((int) id);
        db.close();
    }

    public int delete(long id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int count = db.delete("ware", "_id=?", new String[]{id + ""});
        db.close();
        return count;
    }

    public int update(Ware ware) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", ware.getWarename());
        int count = db.update("ware", contentValues, "_id=?", new String[]{ware.getId() + ""});
        db.close();
        return count;
    }

    public List <Ware> query() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("ware", null, null, null, null, null, null);
        List <Ware> list = new ArrayList <Ware>();
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex("_id"));
            String name = cursor.getString(1);
            int sum = cursor.getInt(2);
            list.add(new Ware(id, name));
        }
        cursor.close();
        db.close();
        return list;
    }
}