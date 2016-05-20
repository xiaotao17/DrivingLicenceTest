package com.yangtao.drivinglicencetest.util;

import android.content.Context;

import com.yangtao.drivinglicencetest.db.QuestionDB;
import com.yangtao.drivinglicencetest.model.Question;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by YANGTAO on 2016/5/20.
 */
public class Utility {
    public static void handleQuestionResponse(QuestionDB questionDB, String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i< jsonArray.length(); i++){
                JSONObject JSONquestion = jsonArray.getJSONObject(i);
                String question = JSONquestion.getString("question");
                int answer = Integer.parseInt(JSONquestion.getString("answer"));
                String item1 = JSONquestion.getString("item1");
                String item2 = JSONquestion.getString("item2");
                String item3 = JSONquestion.getString("item3");
                String item4 = JSONquestion.getString("item4");
                String explains = JSONquestion.getString("explains");
                String url = JSONquestion.getString("url");
                Question question1 = new Question();
                question1.setQuestion(question);
                question1.setAnswer(answer);
                question1.setItem1(item1);
                question1.setItem2(item2);
                question1.setItem3(item3);
                question1.setItem4(item4);
                question1.setExplains(explains);
                question1.setPictureUrl(url);
                questionDB.saveQuestion(question1);
            }
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
