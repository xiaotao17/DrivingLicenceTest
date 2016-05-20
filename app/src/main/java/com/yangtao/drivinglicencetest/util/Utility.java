package com.yangtao.drivinglicencetest.util;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by YANGTAO on 2016/5/20.
 */
public class Utility {
    public static void handleQuestionResponse(Context context,String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i< jsonArray.length(); i++){
                JSONObject JSONquestion = jsonArray.getJSONObject(i);
                String question = JSONquestion.getString("question");
                String answer = JSONquestion.getString("answer");
                String
            }
        }
    }
}
