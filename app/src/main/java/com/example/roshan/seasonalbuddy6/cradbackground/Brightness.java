package com.example.roshan.seasonalbuddy6.cradbackground;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.roshan.seasonalbuddy6.R;

import java.io.ByteArrayOutputStream;

/**
 * Created by Shazee on 6/13/2016.
 */
public class Brightness extends AppCompatActivity {

    //static Bitmap bitmap;
    ImageView imageAfter;
    static Bitmap bitmap_Source;
    SeekBar brightnessBar;
    TextView brightnessText;
    ImageButton doProcess;
    int brightnessValue = 0;

    //3333333Variables fore sending the effect bitmap to main activity
    Bitmap finalImage;
    ImageButton send;
    ByteArrayOutputStream stream;
    byte[] byteArray;
    //*3333Ends

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_brightness);
        imageAfter = (ImageView)findViewById(R.id.imageAfter);

        // bitmap_Source = BitmapFactory.decodeResource(getResources(), R.drawable.pic1);
        bitmap_Source = (Bitmap)this.getIntent().getParcelableExtra("Bitmap_bright");
        imageAfter.setImageBitmap(processingBitmap_Brightness(bitmap_Source));

        brightnessBar = (SeekBar)findViewById(R.id.brightnessBar);
        brightnessText = (TextView)findViewById(R.id.brightnessText);
        doProcess = (ImageButton)findViewById(R.id.doProcess);

        brightnessBar.setOnSeekBarChangeListener(brightnessBarChangeListener);
        doProcess.setOnClickListener(doProcessClickListener);

        //33333send the image to main using send button
        send = (ImageButton)findViewById(R.id.send_btn);
        send.setOnClickListener(sendListener);
        //333333Ends

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
        Intent intent = new Intent(Brightness.this, Background.class);
        intent.putExtra("finalBitmap_bright", byteArray);
        setResult(RESULT_OK,intent);
        finish();
        //startActivity(intent);

        }};
    //*333333Ends

    View.OnClickListener doProcessClickListener
            = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            // imageAfter.setImageBitmap(processingBitmap_Brightness(bitmap_Source));
            brightnessBar.setProgress(brightnessBar.getMax()/2);
        }};

    SeekBar.OnSeekBarChangeListener brightnessBarChangeListener
            = new SeekBar.OnSeekBarChangeListener(){

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            brightnessValue = progress - 255;
            brightnessText.setText(String.valueOf(brightnessValue));
            finalImage = processingBitmap_Brightness(bitmap_Source);
            imageAfter.setImageBitmap(finalImage);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
            // TODO Auto-generated method stub

        }

    };

    private Bitmap processingBitmap_Brightness(Bitmap src){
        Bitmap dest = Bitmap.createBitmap(
                src.getWidth(), src.getHeight(), src.getConfig());

        for(int x = 0; x < src.getWidth(); x++){
            for(int y = 0; y < src.getHeight(); y++){
                int pixelColor = src.getPixel(x, y);
                int pixelAlpha = Color.alpha(pixelColor);

                int pixelRed = Color.red(pixelColor) + brightnessValue;
                int pixelGreen = Color.green(pixelColor) + brightnessValue;
                int pixelBlue = Color.blue(pixelColor) + brightnessValue;

                if(pixelRed > 255){
                    pixelRed = 255;
                }else if(pixelRed < 0){
                    pixelRed = 0;
                }

                if(pixelGreen > 255){
                    pixelGreen = 255;
                }else if(pixelGreen < 0){
                    pixelGreen = 0;
                }

                if(pixelBlue > 255){
                    pixelBlue = 255;
                }else if(pixelBlue < 0){
                    pixelBlue = 0;
                }

                int newPixel = Color.argb(
                        pixelAlpha, pixelRed, pixelGreen, pixelBlue);

                dest.setPixel(x, y, newPixel);

            }
        }
        return dest;
    }

/*
//2222222Sending the effect bitmap for main activity when pressing back button
    @Override
    public void finish() {
        //convert bitmap to byte array
        // Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        stream = new ByteArrayOutputStream();
        finalImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byteArray = stream.toByteArray();
        //pass byte array to intent
        Intent intent = new Intent(Brightness.this, MainActivity.class);
        intent.putExtra("finalBitmap_bright", byteArray);
        setResult(RESULT_OK,intent);
        super.finish();
        //startActivity(intent);
    }
//*22222Ends
*/


}
