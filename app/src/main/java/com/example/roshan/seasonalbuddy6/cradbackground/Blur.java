package com.example.roshan.seasonalbuddy6.cradbackground;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.example.roshan.seasonalbuddy6.R;

import java.io.ByteArrayOutputStream;

public class Blur extends AppCompatActivity {

    TextView textTitle;
    ImageView image3;
    SeekBar seekbarBlurRadius;
    ImageButton button;
    static Bitmap bitmap;

    //4444444Variables for sending the effect bitmap to main activity
    Bitmap finalImage;
    ImageButton send;
    ByteArrayOutputStream stream;
    byte[] byteArray;
    //*4444Ends



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blur);
        textTitle = (TextView) findViewById(R.id.title);

        image3 = (ImageView) findViewById(R.id.image3);

        //bitmapOriginal = BitmapFactory.decodeResource(getResources(), R.drawable.bs);
        //receive the original bitmap from main activity
        bitmap = (Bitmap)this.getIntent().getParcelableExtra("Bitmap_blur");


        // create blur bitmaps
        image3.setImageBitmap(createBitmap_ScriptIntrinsicBlur(bitmap, 0.1f));

        seekbarBlurRadius = (SeekBar)findViewById(R.id.blurradius);
        button = (ImageButton)findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seekbarBlurRadius.setProgress(seekbarBlurRadius.getMax()/26);
                finalImage = createBitmap_ScriptIntrinsicBlur(bitmap, 0.1f);
                image3.setImageBitmap(finalImage);
            }
        });

        seekbarBlurRadius.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                float radius = (float)seekbarBlurRadius.getProgress();
                finalImage = createBitmap_ScriptIntrinsicBlur(bitmap, radius);
                image3.setImageBitmap(finalImage);
            }});


        //44444444send the image to main using send button
        send = (ImageButton)findViewById(R.id.send_btn);
        send.setOnClickListener(sendListener);
        //444444Ends

    }

    //33333333Send button will send the final bitmap via byte array to main activity
    View.OnClickListener sendListener
            = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            //convert bitmap to byte array
            // Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
            stream = new ByteArrayOutputStream();
            finalImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteArray = stream.toByteArray();
            //pass byte array to intent
            Intent intent = new Intent(Blur.this, Background.class);
            intent.putExtra("finalBitmap_blur", byteArray);
            setResult(RESULT_OK,intent);
            finish();
            //startActivity(intent);

        }};
    //*333333Ends

    private Bitmap createBitmap_ScriptIntrinsicBlur(Bitmap src, float r) {

        //Radius range (0 < r <= 25)
        if(r <= 0){
            r = 0.1f;
        }else if(r > 25){
            r = 25.0f;
        }

        Bitmap bitmap = Bitmap.createBitmap(
                src.getWidth(), src.getHeight(),
                Bitmap.Config.ARGB_8888);

        RenderScript renderScript = RenderScript.create(this);

        Allocation blurInput = Allocation.createFromBitmap(renderScript, src);
        Allocation blurOutput = Allocation.createFromBitmap(renderScript, bitmap);

        ScriptIntrinsicBlur blur = ScriptIntrinsicBlur.create(renderScript,
                Element.U8_4(renderScript));
        blur.setInput(blurInput);
        blur.setRadius(r);
        blur.forEach(blurOutput);

        blurOutput.copyTo(bitmap);
        renderScript.destroy();
        return bitmap;
    }

}