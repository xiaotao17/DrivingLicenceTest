package com.yangtao.drivinglicencetest.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.yangtao.drivinglicencetest.model.Question;

/**
 * Created by YANGTAO on 2016/5/20.
 */
public class QuestionDB {
    public static final String DB_NAME = "driving_question";

    public static final int VERSION = 1;

    private static QuestionDB mQuestionDB;

    private SQLiteDatabase mDb;

    private QuestionDB(Context context) {
        QuestionDBOpenHelper dbHelper = new QuestionDBOpenHelper(context,DB_NAME,null,VERSION);
        mDb = dbHelper.getWritableDatabase();
    }

    public synchronized static QuestionDB getInstance(Context context) {
        if (mQuestionDB == null) {
            mQuestionDB = new QuestionDB(context);
        }
        return  mQuestionDB;
    }

    public  void saveQuestion (Question question) {
        if (question != null){
            ContentValues values = new ContentValues();
            values.put("sn",question.getSn());
            values.put("question", question.getQuestion());
            values.put("answer",question.getAnswer());
            values.put("item1",question.getItem1());
            values.put("item2",question.getItem2());
            values.put("item3",question.getItem3());
            values.put("item4",question.getItem4());
            values.put("explains",question.getExplains());
            values.put("url",question.getPictureUrl());
            mDb.insert("Question",null,values);
        }
    }

    public  Question queryQuestionBySn(int sn) {
        Cursor cursor = mDb.rawQuery("select * from Question where sn = " + sn,null);
        if (cursor.moveToFirst()) {
            Question question = new Question();
            question.setId(cursor.getInt(cursor.getColumnIndex("id")));
            question.setSn(cursor.getInt(cursor.getColumnIndex("sn")));
            question.setQuestion(cursor.getString(cursor.getColumnIndex("question")));
            question.setAnswer(cursor.getInt(cursor.getColumnIndex("answer")));
            question.setItem1(cursor.getString(cursor.getColumnIndex("item1")));
            question.setItem2(cursor.getString(cursor.getColumnIndex("item2")));
            question.setItem3(cursor.getString(cursor.getColumnIndex("item3")));
            question.setItem4(cursor.getString(cursor.getColumnIndex("item4")));
            question.setExplains(cursor.getString(cursor.getColumnIndex("explains")));
            question.setPictureUrl(cursor.getString(cursor.getColumnIndex("url")));
            return question;
        }
        return null;
    }

    public void deleteQuestions(){
        mDb.execSQL("delete from Question" );
    }

}
