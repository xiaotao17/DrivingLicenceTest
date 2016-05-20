package com.yangtao.drivinglicencetest.model;

/**
 * 试题类
 * Created by YANGTAO on 2016/5/20.
 */
public class Question {
    //试题id
    private int mId;
    //题目
    private String mQuestion;
    //正确答案编号
    private int mAnswer;
    //选项1，2，3，4
    private String mItem1;
    private String mItem2;
    private String mItem3;
    private String mItem4;
    //解释
    private String mExplains;
    //图片地址
    private String mPictureUrl;

    public int getAnswer() {
        return mAnswer;
    }

    public void setAnswer(int answer) {
        mAnswer = answer;
    }

    public String getExplains() {
        return mExplains;
    }

    public void setExplains(String explains) {
        mExplains = explains;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getItem1() {
        return mItem1;
    }

    public void setItem1(String item1) {
        mItem1 = item1;
    }

    public String getItem2() {
        return mItem2;
    }

    public void setItem2(String item2) {
        mItem2 = item2;
    }

    public String getItem3() {
        return mItem3;
    }

    public void setItem3(String item3) {
        mItem3 = item3;
    }

    public String getItem4() {
        return mItem4;
    }

    public void setItem4(String item4) {
        mItem4 = item4;
    }

    public String getPictureUrl() {
        return mPictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        mPictureUrl = pictureUrl;
    }

    public String getQuestion() {
        return mQuestion;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }
}
