package com.yangtao.drivinglicencetest.activity;

import android.app.ProgressDialog;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.yangtao.drivinglicencetest.R;
import com.yangtao.drivinglicencetest.db.QuestionDB;
import com.yangtao.drivinglicencetest.model.Question;
import com.yangtao.drivinglicencetest.model.Score;
import com.yangtao.drivinglicencetest.util.HttpCallbackListener;
import com.yangtao.drivinglicencetest.util.HttpUtil;
import com.yangtao.drivinglicencetest.util.Utility;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private static final String HTTPURL = "http://apis.baidu.com/bbtapi/jztk/jztk_query";
    private static final String APIKEY = "c2e242b43d7c7bf395bc5feb464eb92b";

    private QuestionDB questionDB;
    private Question mQuestion;
    private Uri mPictureUri;
    //当前题目号
    private int currentQuestionSN;

    private TextView tv_question;
    private ImageView img_question;
    private RadioGroup radioGroup;
    private RadioButton radioButton1, radioButton2, radioButton3, radioButton4;
    private Button btn_OK;
    private ProgressDialog mProgressDialog;

    private File cache;

    private Score[] scores;
    private int answer;
    private int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        cache = new File(getCacheDir(),"image");
        if (!cache.exists()){
           cache.mkdirs();
        }

        scores = new Score[100];
        for (int i = 0; i < 100; i++) {
            scores[i] = new Score();
        }
        showProgressDialog();
        setContentView(R.layout.activity_main);
        tv_question = (TextView) findViewById(R.id.textview_question);
        img_question = (ImageView) findViewById(R.id.imgview_question);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioButton1 = (RadioButton) findViewById(R.id.radio_item1);
        radioButton2 = (RadioButton) findViewById(R.id.radio_item2);
        radioButton3 = (RadioButton) findViewById(R.id.radio_item3);
        radioButton4 = (RadioButton) findViewById(R.id.radio_item4);

        String args = "subject=1&model=c1&testType=rand";
        questionDB = QuestionDB.getInstance(this);
        HttpUtil.sendHttpRequest(HTTPURL, APIKEY, args, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                boolean result = false;
                result = Utility.handleQuestionResponse(questionDB, response);
                if (result) {
                    closeProgressDialog();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            currentQuestionSN = 1;
                            showQuestion();

                        }
                    });
                }
            }

            @Override
            public void onError(Exception e) {

            }
        });

        btn_OK = (Button) findViewById(R.id.btn_confirm);
        btn_OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (radioGroup.getCheckedRadioButtonId()){
                    case R.id.radio_item1:
                        answer = 1;
                        break;
                    case R.id.radio_item2:
                        answer = 2;
                        break;
                    case R.id.radio_item3:
                        answer = 3;
                        break;
                    case R.id.radio_item4:
                        answer = 4;
                        break;
                    default:
                        answer = 0;
                }
                scores[currentQuestionSN-1].setSn(currentQuestionSN);
                if (answer == mQuestion.getRightAnswer()) {
                    scores[currentQuestionSN-1].setRight(true);
                } else {
                    scores[currentQuestionSN-1].setRight(false);
                }
                if (currentQuestionSN < 100) {
                    currentQuestionSN++;
                    showQuestion();
                } else {
                    showScore();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        File[] files = cache.listFiles();
        for (File file:files){
            file.delete();
        }
        cache.delete();
    }

    private void showQuestion() {
        mQuestion = questionDB.queryQuestion(currentQuestionSN);
        tv_question.setText(mQuestion.getSerialNumber() + "、"+ mQuestion.getQuestion());

        if (!mQuestion.getPictureUrl().isEmpty()){
            img_question.setVisibility(View.INVISIBLE);
            new Thread(){
                public void run() {
                    try {
                        mPictureUri = HttpUtil.getImageURI(mQuestion.getPictureUrl(),cache);
                        if (mPictureUri != null){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    img_question.setVisibility(View.VISIBLE);
                                    img_question.setImageURI(mPictureUri);
                                }
                            });

                        }
                    }catch (Exception e)  {
                        e.printStackTrace();
                    }

                }
            }.start();
        }else {
            img_question.setVisibility(View.GONE);
        }

        radioGroup.clearCheck();
        if (mQuestion.getItem1().equals("") && mQuestion.getItem2().equals("")) {
            radioButton1.setText("正确");
            radioButton2.setText("错误");
        } else {
            radioButton1.setText(mQuestion.getItem1());
            radioButton2.setText(mQuestion.getItem2());
        }

        if (mQuestion.getItem3().equals("") && mQuestion.getItem4().equals("")) {
            radioButton3.setVisibility(View.GONE);
            radioButton4.setVisibility(View.GONE);

        } else {
            radioButton3.setVisibility(View.VISIBLE);
            radioButton4.setVisibility(View.VISIBLE);
            radioButton3.setText(mQuestion.getItem3());
            radioButton4.setText(mQuestion.getItem4());
        }

    }

    private void showScore() {
        score = 0;
        for (Score s : scores){
            if (s.isRight()) {
                score++;
            }
        }
        tv_question.setText("你的成绩：" + score);
        img_question.setVisibility(View.GONE);
        radioGroup.setVisibility(View.GONE);
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage("正在加载……");
            mProgressDialog.setCanceledOnTouchOutside(false);
        }
        mProgressDialog.show();
    }

    private void closeProgressDialog(){
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
        }
    }
}
