package com.bhavya.shopfinderr;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);


                int secondsDelayed = 1;
                new Handler().postDelayed(new Runnable() {
                    public void run() {
                        startActivity(new Intent(Splash.this, MainActivity.class));
                        finish();
                    }
                }, secondsDelayed * 1000);
            }
        }


