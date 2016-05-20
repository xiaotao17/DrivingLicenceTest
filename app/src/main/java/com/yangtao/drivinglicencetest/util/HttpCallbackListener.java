package com.yangtao.drivinglicencetest.util;

/**
 * Created by YANGTAO on 2016/5/20.
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
