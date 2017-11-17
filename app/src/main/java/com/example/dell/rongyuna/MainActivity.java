package com.example.dell.rongyuna;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import io.rong.imkit.RongIM;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void btn(View view) {
        RongIM.getInstance().startPrivateChat(this,"123","垃圾");
    }
}
