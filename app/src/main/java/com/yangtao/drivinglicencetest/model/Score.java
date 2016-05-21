package com.yangtao.drivinglicencetest.model;

/**
 * Created by Yangtao on 2016/5/21.
 */
public class Score {
    //编号
    private int mSn;
    //对错
    private boolean isRight;

    public Score(boolean isRight, int sn) {
        this.isRight = isRight;
        mSn = sn;
    }

    public Score() {
        isRight = false;
        mSn = 0;
    }

    public boolean isRight() {
        return isRight;
    }

    public void setRight(boolean right) {
        isRight = right;
    }

    public int getSn() {
        return mSn;
    }

    public void setSn(int sn) {
        mSn = sn;
    }
}
