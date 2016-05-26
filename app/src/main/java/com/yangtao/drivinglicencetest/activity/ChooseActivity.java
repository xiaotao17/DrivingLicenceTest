package com.yangtao.drivinglicencetest.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import com.yangtao.drivinglicencetest.R;

public class ChooseActivity extends AppCompatActivity {

    private static final String[] subject = {"科目一","科目四"};
    private static final String[] model = {"C1","C2","A1","A2","B1","B2"};
    private static final String[] type = {"随机","全部"};
    private Spinner spinnerSubject,spinnerModel,spinnerType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
    }
}
