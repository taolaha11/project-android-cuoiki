package com.mobile.quanlybanhangonline.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.mobile.quanlybanhangonline.R;
import com.mobile.quanlybanhangonline.activity.login.LoginActivity;

public class MainActivity extends Activity {
    public static int user_id = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (Exception e) {
                    // TODO: handle exception
                } finally {
                    Intent i = new Intent(getApplicationContext(),
                            LoginActivity.class);
                    startActivity(i);
                }
            }
        };
        thread.start();

    }

    protected void onPause() {
        super.onPause();
        finish();
    }


}