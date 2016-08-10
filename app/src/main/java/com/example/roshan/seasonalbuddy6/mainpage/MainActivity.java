package com.example.roshan.seasonalbuddy6.mainpage;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.roshan.seasonalbuddy6.R;
import com.example.roshan.seasonalbuddy6.Reminder.ReminderActivity;
import com.example.roshan.seasonalbuddy6.card.CardActivity;
import com.example.roshan.seasonalbuddy6.games.GameActivity;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    GifView gifView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
        ImageButton card = (ImageButton)findViewById(R.id.btnCard);
        ImageButton game = (ImageButton)findViewById(R.id.btnGame);
        ImageButton reminder = (ImageButton)findViewById(R.id.reminderBtn);

       // card.startAnimation(animation1);
        card.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,CardActivity.class);
                startActivity(intent);

                File filepath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
        //filepath.list();
        File dir = new File(filepath + "/Seasonal Buddy");
        if(!dir.exists()) {
            dir.mkdirs(); //make new directory if their not exist
        }
                File template = new File(filepath + "/Seasonal Buddy/Template");
                if(!template.exists()) {
                    template.mkdirs(); //make new directory if their not exist
                }
            }
        });

        game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                startActivity(intent);
            }
        });

        reminder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent remind = new Intent(MainActivity.this, ReminderActivity.class);
                startActivity(remind);
            }
        });


        ////////

//To create the animator directory go to res-> new-> android resource directory -> type the name animator.xml
        //get the butterfly view
        gifView = (GifView) findViewById(R.id.gif_view);
        //load the butterfly movement animation
        AnimatorSet sunSet = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.animator.fly);
//set the view as target
        sunSet.setTarget(gifView);
//start the animation
        sunSet.start();

        ObjectAnimator flyAnim2 = ObjectAnimator.ofFloat(findViewById(R.id.gif_view2), "y", 1000);
        flyAnim2.setDuration(8500);
        flyAnim2.setRepeatCount(ValueAnimator.INFINITE);
        flyAnim2.setRepeatMode(ValueAnimator.RESTART);
        flyAnim2.start();


        ObjectAnimator flyAnim3 = ObjectAnimator.ofFloat(findViewById(R.id.gif_view3), "y", 1000);
        flyAnim3.setDuration(9000);
        flyAnim3.setRepeatCount(ValueAnimator.INFINITE);
        flyAnim3.setRepeatMode(ValueAnimator.RESTART);
        flyAnim3.start();

        ObjectAnimator flyAnim4 = ObjectAnimator.ofFloat(findViewById(R.id.gif_view4), "y", 1000);
        flyAnim4.setDuration(11000);
        flyAnim4.setRepeatCount(ValueAnimator.INFINITE);
        flyAnim4.setRepeatMode(ValueAnimator.RESTART);
        flyAnim4.start();

        ObjectAnimator flyAnim5 = ObjectAnimator.ofFloat(findViewById(R.id.gif_view5), "y", 1000);
        flyAnim5.setDuration(10000);
        flyAnim5.setRepeatCount(ValueAnimator.INFINITE);
        flyAnim5.setRepeatMode(ValueAnimator.RESTART);
        flyAnim5.start();

        ObjectAnimator flyAnim6 = ObjectAnimator.ofFloat(findViewById(R.id.gif_view6), "y", 300);
        flyAnim6.setDuration(8000);
        flyAnim6.setRepeatCount(ValueAnimator.INFINITE);
        flyAnim6.setRepeatMode(ValueAnimator.RESTART);
        flyAnim6.start();
    }
}
