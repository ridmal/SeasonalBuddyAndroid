package com.example.roshan.seasonalbuddy6.mainpage;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;

import com.example.roshan.seasonalbuddy6.R;
import com.example.roshan.seasonalbuddy6.Reminder.ReminderActivity;
import com.example.roshan.seasonalbuddy6.card.CardActivity;
import com.example.roshan.seasonalbuddy6.games.GameActivity;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {

    GifView gifView;
    int newyear[] = {R.drawable.newyear1,R.drawable.newyear2,R.drawable.newyear3,R.drawable.newyear4,
            R.drawable.newyear5};
    int christmas[] = {R.drawable.xms1,R.drawable.xms2,R.drawable.xms3,R.drawable.xms4,
    R.drawable.xms5,R.drawable.xms6,R.drawable.xms7,R.drawable.xms8,R.drawable.xms9,R.drawable.xms10,R.drawable.xms11};
    int vesak[] = {R.drawable.wesak1,R.drawable.wesak2,R.drawable.wesak3,R.drawable.wesak4};
    int valentine[] = {R.drawable.valentine1,R.drawable.valentine2,
            R.drawable.valentine3,R.drawable.valentine4,
            R.drawable.valentine5,R.drawable.valentine6,
            R.drawable.valentine7,R.drawable.valentine8};
    int ramazan[];
    int thaipongal[];
    int birthday[] = {R.drawable.bdy1,R.drawable.bdy2,
            R.drawable.bdy3,R.drawable.bdy4,
            R.drawable.bdy5,R.drawable.bdy6,
            R.drawable.bdy7,R.drawable.bdy8,
            R.drawable.bdy9,R.drawable.bdy10,
            R.drawable.bdy11,R.drawable.bdy12,};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom);
        ImageButton card = (ImageButton)findViewById(R.id.btnCard);
        ImageButton game = (ImageButton)findViewById(R.id.btnGame);
        ImageButton reminder = (ImageButton)findViewById(R.id.reminderBtn);
        //String newPicture = saveToSDCard(R.drawable.bdy1, "bd1.png");
       // startMediaScanner(newPicture);
        for(int i = 0; i<newyear.length;i++){
            saveToSDCard(newyear[i],"newyr"+i+".png","Sinhala_Hindu_NewYear");
        }
        for(int i = 0; i<christmas.length;i++){
            saveToSDCard(christmas[i],"christmas"+i+".png","Christmas");
        }
        for(int i = 0; i<vesak.length;i++){
            saveToSDCard(vesak[i],"vesak"+i+".png","Vesak_Festival");
        }
        for(int i = 0; i<valentine.length;i++){
            saveToSDCard(valentine[i],"valentine"+i+".png","Valentine");
        }
        for(int i = 0; i<birthday.length;i++){
            saveToSDCard(birthday[i],"birthday"+i+".png","Birthday");
        }
       // card.startAnimation(animation1);
        card.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MainActivity.this,CardActivity.class);
                startActivity(intent);
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
    private String saveToSDCard(int resourceID, String finalName,String type)
    {
        StringBuffer createdFile = new StringBuffer();

        Bitmap resourceImage = BitmapFactory.decodeResource(this.getResources(), resourceID);
        File filepath = Environment.getExternalStorageDirectory();
        //filepath.list();
        File dir = new File(filepath + "/Seasonal Buddy/Clips/"+type);
        if(!dir.exists()){
            dir.mkdirs(); //make new directory if their not exist
        }
        File externalStorageFile = new File(dir, finalName);
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        resourceImage.compress(Bitmap.CompressFormat.PNG, 100, bytes);
        byte b[] = bytes.toByteArray();

        try
        {
            externalStorageFile.createNewFile();
            OutputStream filoutputStream = new FileOutputStream(externalStorageFile);
            filoutputStream.write(b);
            filoutputStream.flush();
            filoutputStream.close();
            createdFile.append(externalStorageFile.getAbsolutePath());
        }
        catch (IOException e)
        {
        }

        return createdFile.toString();
    }

    private void startMediaScanner(String addedPicture)
    {
        sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://"+ addedPicture)));
    }
}
