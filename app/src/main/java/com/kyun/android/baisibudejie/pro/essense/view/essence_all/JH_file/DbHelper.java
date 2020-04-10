package com.kyun.android.baisibudejie.pro.essense.view.essence_all.JH_file;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class DbHelper extends SQLiteOpenHelper {

    private Context context;
    public DbHelper(Context context) {
        super(context, "ware.db", null, 1);
        this.context=context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("onCreate");
        db.execSQL("create table ware(_id integer primary key autoincrement,name varchar(20),money integer)");
        Toast.makeText(context,"create succeed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists ware");
        onCreate(db);

    }
}