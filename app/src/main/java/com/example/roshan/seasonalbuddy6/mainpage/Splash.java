
package com.example.roshan.seasonalbuddy6.mainpage;

/**
 * Created by MRoshan on 2015-10-20.
 */
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.roshan.seasonalbuddy6.R;
import com.example.roshan.seasonalbuddy6.mainpage.MainActivity;

public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Thread myThread = new Thread(){
            @Override
            public void run() {
                super.run();
                try {
                    sleep(1500);
                    Intent startMainScreen = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(startMainScreen);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
