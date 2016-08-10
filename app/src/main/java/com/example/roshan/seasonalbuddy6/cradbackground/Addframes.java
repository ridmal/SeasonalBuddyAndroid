package com.example.roshan.seasonalbuddy6.cradbackground;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.roshan.seasonalbuddy6.R;

import java.io.ByteArrayOutputStream;

public class Addframes extends AppCompatActivity {

    //3333333Variables fore sending the effect bitmap to main activity
    Bitmap finalImage, bitmap_Source;
    ImageButton send, circle;
    ByteArrayOutputStream stream;
    byte[] byteArray;
    //*3333Ends

    ImageView picImgView, frameImgView;
    ImageButton framebtn1,framebtn2,framebtn3,framebtn4,framebtn5,framebtn6,framebtn7,framebtn8,framebtn9,framebtn10;
    ImageButton framebtn11, framebtn12, framebtn13, framebtn14, framebtn15;
    Bitmap bitmap_Frame;
    Bitmap backImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addframes);

        picImgView = (ImageView)findViewById(R.id.picImgView);
        frameImgView = (ImageView)findViewById(R.id.frameImgView);

        bitmap_Source = (Bitmap)this.getIntent().getParcelableExtra("Bitmap_frame");
        picImgView.setImageBitmap(bitmap_Source);

        framebtn1 = (ImageButton)findViewById(R.id.framebtn1);
        framebtn1.setOnClickListener(framebtn1Listner);

        framebtn2 = (ImageButton)findViewById(R.id.framebtn2);
        framebtn2.setOnClickListener(framebtn2Listner);

        framebtn3 = (ImageButton)findViewById(R.id.framebtn3);
        framebtn3.setOnClickListener(framebtn3Listner);

        framebtn4 = (ImageButton)findViewById(R.id.framebtn4);
        framebtn4.setOnClickListener(framebtn4Listner);

        framebtn5 = (ImageButton)findViewById(R.id.framebtn5);
        framebtn5.setOnClickListener(framebtn5Listner);

        framebtn6 = (ImageButton)findViewById(R.id.framebtn6);
        framebtn6.setOnClickListener(framebtn6Listner);

        framebtn7 = (ImageButton)findViewById(R.id.framebtn7);
        framebtn7.setOnClickListener(framebtn7Listner);

        framebtn8 = (ImageButton)findViewById(R.id.framebtn8);
        framebtn8.setOnClickListener(framebtn8Listner);

        framebtn9 = (ImageButton)findViewById(R.id.framebtn9);
        framebtn9.setOnClickListener(framebtn9Listner);

        framebtn10 = (ImageButton)findViewById(R.id.framebtn10);
        framebtn10.setOnClickListener(framebtn10Listner);

        framebtn11 = (ImageButton)findViewById(R.id.framebtn11);
        framebtn11.setOnClickListener(framebtn11Listner);

        framebtn12 = (ImageButton)findViewById(R.id.framebtn12);
        framebtn12.setOnClickListener(framebtn12Listner);

        framebtn13 = (ImageButton)findViewById(R.id.framebtn13);
        framebtn13.setOnClickListener(framebtn13Listner);

        framebtn14 = (ImageButton)findViewById(R.id.framebtn14);
        framebtn14.setOnClickListener(framebtn14Listner);

        framebtn15 = (ImageButton)findViewById(R.id.framebtn15);
        framebtn15.setOnClickListener(framebtn15Listner);



        //33333send the image to main using send button
        send = (ImageButton)findViewById(R.id.send_done);
        send.setOnClickListener(sendListener);
        //333333Ends

        circle = (ImageButton)findViewById(R.id.circle_done);
        circle.setOnClickListener(circleListener);


    }


    View.OnClickListener framebtn1Listner =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            bitmap_Frame = BitmapFactory.decodeResource(getResources(), R.drawable.f1);
            shazeEffcts(0,0,128);
            frameImgView.setImageBitmap(bitmap_Frame);
        }
    };

    View.OnClickListener framebtn2Listner =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            bitmap_Frame = BitmapFactory.decodeResource(getResources(), R.drawable.f2);
            shazeEffcts(255,0,255);
            frameImgView.setImageBitmap(bitmap_Frame);
        }
    };

    View.OnClickListener framebtn3Listner =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            bitmap_Frame = BitmapFactory.decodeResource(getResources(), R.drawable.f3);
            shazeEffcts(0,255,255);
            frameImgView.setImageBitmap(bitmap_Frame);
        }
    };

    View.OnClickListener framebtn4Listner =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            bitmap_Frame = BitmapFactory.decodeResource(getResources(), R.drawable.f4);
            shazeEffcts(192,192,192);
            frameImgView.setImageBitmap(bitmap_Frame);
        }
    };

    View.OnClickListener framebtn5Listner =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            bitmap_Frame = BitmapFactory.decodeResource(getResources(), R.drawable.f5);
            shazeEffcts(0,0,255);
            frameImgView.setImageBitmap(bitmap_Frame);
        }
    };

    View.OnClickListener framebtn6Listner =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            bitmap_Frame = BitmapFactory.decodeResource(getResources(), R.drawable.f6);
            shazeEffcts(128,0,0);
            frameImgView.setImageBitmap(bitmap_Frame);
        }
    };

    View.OnClickListener framebtn7Listner =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            bitmap_Frame = BitmapFactory.decodeResource(getResources(), R.drawable.f7);
            shazeEffcts(220,20,60);
            frameImgView.setImageBitmap(bitmap_Frame);
        }
    };

    View.OnClickListener framebtn8Listner =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            bitmap_Frame = BitmapFactory.decodeResource(getResources(), R.drawable.f8);
            shazeEffcts(255,69,0);
            frameImgView.setImageBitmap(bitmap_Frame);
        }
    };

    View.OnClickListener framebtn9Listner =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            bitmap_Frame = BitmapFactory.decodeResource(getResources(), R.drawable.f9);
            shazeEffcts(205,92,92);
            frameImgView.setImageBitmap(bitmap_Frame);
        }
    };

    View.OnClickListener framebtn10Listner =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            bitmap_Frame = BitmapFactory.decodeResource(getResources(), R.drawable.f10);
            shazeEffcts(128,128,0);
            frameImgView.setImageBitmap(bitmap_Frame);
        }
    };

    View.OnClickListener framebtn11Listner =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            bitmap_Frame = BitmapFactory.decodeResource(getResources(), R.drawable.f11);
            shazeEffcts(0,128,0);
            frameImgView.setImageBitmap(bitmap_Frame);
        }
    };

    View.OnClickListener framebtn12Listner =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            bitmap_Frame = BitmapFactory.decodeResource(getResources(), R.drawable.f12);
            shazeEffcts(240,128,128);
            frameImgView.setImageBitmap(bitmap_Frame);
        }
    };

    View.OnClickListener framebtn13Listner =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            bitmap_Frame = BitmapFactory.decodeResource(getResources(), R.drawable.f13);
            shazeEffcts(32,178,170);
            frameImgView.setImageBitmap(bitmap_Frame);
        }
    };

    View.OnClickListener framebtn14Listner =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            bitmap_Frame = BitmapFactory.decodeResource(getResources(), R.drawable.f14);
            shazeEffcts(70,130,180);
            frameImgView.setImageBitmap(bitmap_Frame);
        }
    };

    View.OnClickListener framebtn15Listner =  new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            bitmap_Frame = BitmapFactory.decodeResource(getResources(), R.drawable.f15);
            shazeEffcts(210,105,30);
            frameImgView.setImageBitmap(bitmap_Frame);
        }
    };


    //METHOD2
    //33333333Send button will send the final bitmap via byte array to main activity
    View.OnClickListener sendListener
            = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

            //combine the image and frame as a single bitmap
            finalImage = combineTwoBitmaps(bitmap_Source, bitmap_Frame, backImage);
            // finalImage = getCircleBitmap(combineTwoBitmaps(bitmap_Source,bitmap_Frame,backImage));

            //convert bitmap to byte array
            // Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
            stream = new ByteArrayOutputStream();
            finalImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteArray = stream.toByteArray();
            //pass byte array to intent
            Intent intent = new Intent(Addframes.this, Background.class);
            intent.putExtra("finalBitmap_bright", byteArray);
            setResult(RESULT_OK,intent);
            finish();
            //startActivity(intent);

        }};
    //*333333Ends

    //METHOD2
    //33333333Send button will send the final bitmap via byte array to main activity
    View.OnClickListener circleListener
            = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub

            //combine the image and frame as a single bitmap
            //finalImage = combineTwoBitmaps(bitmap_Source, bitmap_Frame, backImage);
            finalImage = getCircleBitmap(combineTwoBitmaps(bitmap_Source,bitmap_Frame,backImage));

            //convert bitmap to byte array
            // Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
            stream = new ByteArrayOutputStream();
            finalImage.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byteArray = stream.toByteArray();
            //pass byte array to intent
            Intent intent = new Intent(Addframes.this, Background.class);
            intent.putExtra("finalBitmap_bright", byteArray);
            setResult(RESULT_OK,intent);
            finish();
            //startActivity(intent);

        }};
    //*333333Ends


    private Bitmap combineTwoBitmaps(Bitmap background, Bitmap foreground, Bitmap backView) {
        Bitmap combinedBitmap = Bitmap.createBitmap(foreground.getWidth(), foreground.getHeight(), foreground.getConfig());
        Canvas canvas = new Canvas(combinedBitmap);
        Paint paint = new Paint(Paint.FILTER_BITMAP_FLAG);
        canvas.drawBitmap(backView, 0, 0, paint);
        canvas.drawBitmap(background, foreground.getWidth()/6, foreground.getHeight()/5, paint);
        canvas.drawBitmap(foreground, 0, 0, paint);
        return combinedBitmap;
    }

    public void shazeEffcts(int R, int G, int B){

        backImage = Bitmap.createBitmap(bitmap_Frame.getWidth(), bitmap_Frame.getHeight(), bitmap_Frame.getConfig());


        //  int A, R=0, G=0, B=0;
        int A;
        int pixelColor;
        int height = bitmap_Frame.getHeight();
        int width = bitmap_Frame.getWidth();
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                pixelColor = bitmap_Frame.getPixel(x, y);
                //A = Color.alpha(pixelColor);
                A = 100;

                // R = Color.red(pixelColor);
                //  G = Color.green(pixelColor);
                //   B = Color.blue(pixelColor);

                backImage.setPixel(x, y, Color.argb(A, R, G, B));
            }
        }

    }


    private Bitmap getCircleBitmap(Bitmap bitmap) {
        final Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        final Canvas canvas = new Canvas(output);

        final int color = Color.RED;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);

        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawOval(rectF, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        bitmap.recycle();

        return output;
    }


}
