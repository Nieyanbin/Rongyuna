package com.example.dell.rongyuna;

import android.app.Application;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

/**
 * Created by dell on 2017/11/16.
 */

public class App extends Application implements Jiekou{
    @Override
    public void onCreate() {
        super.onCreate();
        RongIM.init(this);
        GetToken getToken=new GetToken();
        getToken.attathview(this);
        getToken.GetRongCloudToken();
    }

    @Override
    public void onSuccess(Object o) {
        Bean bean= (Bean) o;
        String token = bean.getToken();
        RongIM.connect(token, new RongIMClient.ConnectCallback() {
            @Override
            public void onTokenIncorrect() {

            }

            @Override
            public void onSuccess(String s) {

            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {

            }
        });
    }
}
