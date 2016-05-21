package com.yangtao.drivinglicencetest.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by YANGTAO on 2016/5/20.
 */
public class QuestionDBOpenHelper extends SQLiteOpenHelper {
    public static final String CREATE_QUESTION = "create table Question ("
            + "id integer primary key autoincrement,"
            + "sn integer,"
            + "question text,"
            + "answer integer,"
            + "item1 text,"
            + "item2 text,"
            + "item3 text,"
            + "item4 text,"
            + "explains text,"
            + "url text)";

    public QuestionDBOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_QUESTION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
