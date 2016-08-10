package com.example.roshan.seasonalbuddy6.cradbackground;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.roshan.seasonalbuddy6.R;

import java.io.ByteArrayOutputStream;

public class Effects extends AppCompatActivity {

    ImageButton effect0, effect1, effect2, effect3, effect4, effect5, effect6, effect7, effect8, effect9, effect10, effect11, effect12, effect13;
    static Bitmap bitmap;
    ImageView imgView2;
    Bitmap finalImage;

    //22222Variables fore sending the effect bitmap to main activity
    ImageButton send;
    ByteArrayOutputStream stream;
    byte[] byteArray;
    //*2222Ends

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //receive the original bitmap from main activity
        bitmap = (Bitmap)this.getIntent().getParcelableExtra("Bitmap");
        setContentView(R.layout.activity_effects);
        imgView2 = (ImageView)findViewById(R.id.imgView2);
        imgView2.setImageBitmap(bitmap);


        effect0 = (ImageButton)findViewById(R.id.effect_btn0);
        effect1 = (ImageButton)findViewById(R.id.effect_btn1);
        effect2 = (ImageButton)findViewById(R.id.effect_btn2);
        effect3 = (ImageButton)findViewById(R.id.effect_btn3);
        effect4 = (ImageButton)findViewById(R.id.effect_btn4);
        effect5 = (ImageButton)findViewById(R.id.effect_btn5);
        effect6 = (ImageButton)findViewById(R.id.effect_btn6);
        effect7 = (ImageButton)findViewById(R.id.effect_btn7);
        effect8 = (ImageButton)findViewById(R.id.effect_btn8);
        effect9 = (ImageButton)findViewById(R.id.effect_btn9);
        effect10 = (ImageButton)findViewById(R.id.effect_btn10);
        effect11 = (ImageButton)findViewById(R.id.effect_btn11);
        effect12 = (ImageButton)findViewById(R.id.effect_btn12);
        effect13 = (ImageButton)findViewById(R.id.effect_btn13);


        shazeEffcts(bitmap,0,false);
        shazeEffcts(bitmap,1,false);
        shazeEffcts(bitmap,2,false);
        shazeEffcts(bitmap,3,false);
        shazeEffcts(bitmap,4,false);
        shazeEffcts(bitmap,5,false);
        shazeEffcts(bitmap,6,false);
        shazeEffcts(bitmap,7,false);
        shazeEffcts(bitmap,8,false);
        shazeEffcts(bitmap,9,false);
        shazeEffcts(bitmap,10,false);
        shazeEffcts(bitmap,11,false);
        shazeEffcts(bitmap,12,false);
        shazeEffcts(bitmap,13,false);


        effect0.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shazeEffcts(bitmap, 0, true);
                    }
                }
        );
        effect1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shazeEffcts(bitmap, 1, true);
                    }
                }
        );
        effect2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shazeEffcts(bitmap,2,true);
                    }
                }
        );
        effect3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shazeEffcts(bitmap,3,true);
                    }
                }
        );
        effect4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shazeEffcts(bitmap,4,true);
                    }
                }
        );
        effect5.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shazeEffcts(bitmap,5,true);
                    }
                }
        );
        effect6.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shazeEffcts(bitmap,6,true);
                    }
                }
        );
        effect7.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shazeEffcts(bitmap,7,true);
                    }
                }
        );
        effect8.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shazeEffcts(bitmap,8,true);
                    }
                }
        );
        effect9.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shazeEffcts(bitmap,9,true);
                    }
                }
        );

        effect10.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shazeEffcts(bitmap,10,true);
                    }
                }
        );

        effect11.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shazeEffcts(bitmap,11,true);
                    }
                }
        );

        effect12.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shazeEffcts(bitmap,12,true);
                    }
                }
        );

        effect13.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        shazeEffcts(bitmap,13,true);
                    }
                }
        );


        //22222send the image to main using send button
        send = (ImageButton)findViewById(R.id.image_send);
        send.setOnClickListener(sendListener);
        //*******
    }

    //222222Send button will send the final bitmap via byte array to main activity
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
            Intent intent = new Intent(Effects.this, Background.class);
            intent.putExtra("finalBitmap", byteArray);
            setResult(RESULT_OK, intent);
            //  super.finish();
            finish();
            // startActivity(intent);

        }};
    //*2222Ends



    public void shazeEffcts(final Bitmap original, int scale, Boolean selected){

        finalImage = Bitmap.createBitmap(original.getWidth(), original.getHeight(), original.getConfig());


        int A, R=0, G=0, B=0;
        int pixelColor;
        int height = bitmap.getHeight();
        int width = bitmap.getWidth();
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                pixelColor = bitmap.getPixel(x, y);
                A = Color.alpha(pixelColor);

                if(scale==0){
                    R = Color.red(pixelColor);
                    G = Color.green(pixelColor);
                    B = Color.blue(pixelColor);
                }

                if(scale==1) {//dark
                    R = Color.red(pixelColor) - 60;
                    G = Color.green(pixelColor) - 60;
                    B = Color.blue(pixelColor) - 60;
                    if(R<0)
                        R=0;
                    if(G<0)
                        G=0;
                    if(B<0)
                        B=0;

                }

                if(scale==2) {//bright
                    R = Color.red(pixelColor);
                    G = Color.green(pixelColor);
                    B = Color.blue(pixelColor);
                    A = A-80;
                }


                if(scale==3) {//Gray
                    if(Color.red(pixelColor)>100)
                        R = 200;
                    else if(Color.red(pixelColor)<100 && Color.red(pixelColor)>20)
                        R = 120;
                    else
                        R =80;
                    //for green
                    if(Color.green(pixelColor)>100)
                        G = 200;
                    else if(Color.green(pixelColor)<100 && Color.green(pixelColor)>20)
                        G = 120;
                    else
                        G = 80;
                    //for blue
                    if(Color.blue(pixelColor)>100)
                        B = 200;
                    else if(Color.blue(pixelColor)<100 && Color.blue(pixelColor)>20)
                        B = 120;
                    else
                        B = 80;
                }

                if(scale==4) {//yellow
                    if(Color.red(pixelColor)>175)
                        R = 180;
                    else if(Color.red(pixelColor)<175 && Color.red(pixelColor)>80)
                        R = 80;
                    else
                        R = Color.red(pixelColor);
                    //for green
                    if(Color.green(pixelColor)>175)
                        G = 180;
                    else if(Color.green(pixelColor)<175 && Color.green(pixelColor)>80)
                        G = 80;
                    else
                        G = Color.green(pixelColor);
                    //for blue
                    if(Color.blue(pixelColor)>175)
                        B = 180;
                    else if(Color.blue(pixelColor)<175 && Color.blue(pixelColor)>80)
                        B = 80;
                    else
                        B = Color.blue(pixelColor);
                }
                if(scale==5) {//yellow
                    R = 255 - Color.red(pixelColor);
                    G = 255 - Color.green(pixelColor);
                    B = 0;//255 - Color.blue(pixelColor);
                }
                else if(scale==6){//pink
                    R = 255 - Color.red(pixelColor);
                    G = 0;//255 - Color.green(pixelColor);
                    B = 255 - Color.blue(pixelColor);
                }

                else if(scale==7){//green
                    R = 0;//255 - Color.red(pixelColor);
                    G = 255 - Color.green(pixelColor);
                    B = 255 - Color.blue(pixelColor);
                }

                else if(scale==8){//gamma
                    R =  100;
                    G = Color.green(pixelColor);
                    B = Color.blue(pixelColor);
                }
                else if(scale==9){//green
                    R = Color.red(pixelColor);
                    G = 100;
                    B = Color.blue(pixelColor);

                }

                else if(scale==10){//blue
                    R = Color.red(pixelColor);
                    G = Color.green(pixelColor);
                    B = 100;
                }

                else if(scale==11){//
                    R = 0;
                    G = Color.green(pixelColor);
                    B = Color.blue(pixelColor);
                }

                else if(scale==12){//
                    R = Color.red(pixelColor);
                    G = 0;
                    B = Color.blue(pixelColor);
                }

                else if(scale==13){//
                    R = Color.red(pixelColor);
                    G = Color.green(pixelColor);
                    B = 0;
                }


                finalImage.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }
        //these will change the image of each image button
        if(scale==0)
            effect0.setImageBitmap(finalImage);
        if(scale==1)
            effect1.setImageBitmap(finalImage);
        if(scale==2)
            effect2.setImageBitmap(finalImage);
        if(scale==3)
            effect3.setImageBitmap(finalImage);
        if(scale==4)
            effect4.setImageBitmap(finalImage);
        if(scale==5)
            effect5.setImageBitmap(finalImage);
        if(scale==6)
            effect6.setImageBitmap(finalImage);
        if(scale==7)
            effect7.setImageBitmap(finalImage);
        if(scale==8)
            effect8.setImageBitmap(finalImage);
        if(scale==9)
            effect9.setImageBitmap(finalImage);
        if(scale==10)
            effect10.setImageBitmap(finalImage);
        if(scale==11)
            effect11.setImageBitmap(finalImage);
        if(scale==12)
            effect12.setImageBitmap(finalImage);
        if(scale==13)
            effect13.setImageBitmap(finalImage);
        //this is the control to change the image view
        if(selected)
            imgView2.setImageBitmap(finalImage);
    }
}