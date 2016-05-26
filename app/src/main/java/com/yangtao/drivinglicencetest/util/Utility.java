package com.yangtao.drivinglicencetest.util;


import com.yangtao.drivinglicencetest.db.QuestionDB;
import com.yangtao.drivinglicencetest.model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by YANGTAO on 2016/5/20.
 */
public class Utility {
    public static boolean handleQuestionResponse(QuestionDB questionDB, String response) {
        questionDB.deleteQuestions();
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("result");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject JSONQuestion = jsonArray.getJSONObject(i);
                String question = JSONQuestion.getString("question");
                int answer = Integer.parseInt(JSONQuestion.getString("answer"));
                String item1 = JSONQuestion.getString("item1");
                String item2 = JSONQuestion.getString("item2");
                String item3 = JSONQuestion.getString("item3");
                String item4 = JSONQuestion.getString("item4");
                String explains = JSONQuestion.getString("explains");
                String url = JSONQuestion.getString("url");
                Question question1 = new Question();
                question1.setSerialNumber(i + 1);
                question1.setQuestion(question);
                question1.setRightAnswer(answer);
                question1.setItem1(item1);
                question1.setItem2(item2);
                question1.setItem3(item3);
                question1.setItem4(item4);
                question1.setExplains(explains);
                question1.setPictureUrl(url);
                questionDB.saveQuestion(question1);
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }
}
