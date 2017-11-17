package com.example.dell.rongyuna;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InterfaceAddress;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static io.rong.imlib.statistics.UserData.name;

/**
 * Created by dell on 2017/11/16.
 */

public class GetToken {
    private OkHttpClient client;
 private Handler handler=new Handler(){
     @Override
     public void handleMessage(Message msg) {
         super.handleMessage(msg);
         Bean bean= (Bean) msg.obj;
         jiekou.onSuccess(bean);
     }
 };
    public  void GetRongCloudToken() {
        String url = "https://api.cn.ronghub.com/user/getToken.json";
        String App_Key = "sfci50a7s49ii"; //开发者平台分配的 App Key。
        String App_Secret = "dLjnXs2KPa";
        String Timestamp = String.valueOf(System.currentTimeMillis() / 1000);//时间戳，从 1970 年 1 月 1 日 0 点 0 分 0 秒开始到现在的秒数。
        String Nonce = String.valueOf(Math.floor(Math.random() * 1000000));//随机数，无长度限制。
        String Signature = sha1(App_Secret + Nonce + Timestamp);//数据签名。

         client=new OkHttpClient.Builder().build();
        FormBody.Builder builder=new FormBody.Builder();

        builder.add("userId","321")
                .add("name","123")
                .add("portraitUri","http://img1.imgtn.bdimg.com/it/u=985106046,11635795&fm=27&gp=0.jpg");
        RequestBody requestBody = builder.build();
        Request request=new Request.Builder()
                .addHeader("App-Key", App_Key)
                .addHeader("Timestamp", Timestamp)
                .addHeader("Nonce",Nonce)
                .addHeader("Signature", Signature)
                .url(url)
                .post(requestBody)
                .build();
  client.newCall(request).enqueue(new Callback() {
      @Override
      public void onFailure(Call call, IOException e) {
      Log.e("失败","失败------》"+call);
      }

      @Override
      public void onResponse(Call call, Response response) throws IOException {
          if(response.isSuccessful()){
              String string = response.body().string();
              Gson gson=new Gson();
              Bean bean = gson.fromJson(string, Bean.class);
              Message message=new Message();
             message.obj=bean;
             handler.sendMessage(message);
          }
      }
  });
    }

    //SHA1加密//http://www.rongcloud.cn/docs/server.html#通用_API_接口签名规则
    private static String sha1(String data){
        StringBuffer buf = new StringBuffer();
        try{
            MessageDigest md = MessageDigest.getInstance("SHA1");
            md.update(data.getBytes());
            byte[] bits = md.digest();
            for(int i = 0 ; i < bits.length;i++){
                int a = bits[i];
                if(a<0) a+=256;
                if(a<16) buf.append("0");
                buf.append(Integer.toHexString(a));
            }
        }catch(Exception e){

        }
        return buf.toString();
    }
    private  Jiekou jiekou;


    public void attathview( Jiekou jiekou) {
        this.jiekou=jiekou;
    }

}
