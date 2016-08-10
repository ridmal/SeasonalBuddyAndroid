package com.example.roshan.seasonalbuddy6.cradbackground;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.roshan.seasonalbuddy6.R;

import java.io.ByteArrayOutputStream;

public class Bluredges extends AppCompatActivity {
    //shazeeeeee
    int color = 0xFFFFFFFF;
    //*

    ImageView imageView_Source, imageAfter;
    Bitmap bitmap_Source, bitmap_Dest;
    SeekBar blurBar;
    TextView blurText;
    ImageButton color1,color2,color3,color4,color5,color6,color7,color8,color9,color10;
    ImageButton color11,color12,color13,color14,color15,color16,color17,color18,colo19,color20;

    int blurValue = 1;

    //5555555Variables fore sending the effect bitmap to main activity
    Bitmap finalImage;
    ImageButton send,reset;
    ByteArrayOutputStream stream;
    byte[] byteArray;
    //*555Ends


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluredges);
        imageAfter = (ImageView)findViewById(R.id.imageAfter);

        //receive from main
       // bitmap_Source = BitmapFactory.decodeResource(getResources(), R.drawable.pic);
        bitmap_Source = (Bitmap)this.getIntent().getParcelableExtra("Bitmap_blurEdges");
        finalImage = processingBitmap_Blur(bitmap_Source);
        imageAfter.setImageBitmap(finalImage);

        blurBar = (SeekBar)findViewById(R.id.seekBar1);
        blurText = (TextView)findViewById(R.id.blurText);
        reset = (ImageButton)findViewById(R.id.doProcess);

        blurBar.setOnSeekBarChangeListener(blurBarChangeListener);
        reset.setOnClickListener(resetListner);

        //Shazeeeeeee
        //select a color
        color1 = (ImageButton)findViewById(R.id.color1);
        color1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = 0xFFFFFFFF;
                finalImage = processingBitmap_Blur(bitmap_Source);
                imageAfter.setImageBitmap(finalImage);
            }
        });

        color2 = (ImageButton)findViewById(R.id.color2);
        color2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = 0xFF854442;
                finalImage = processingBitmap_Blur(bitmap_Source);
                imageAfter.setImageBitmap(finalImage);
            }
        });

//        color3 = (ImageButton)findViewById(R.id.color3);
//        color3.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                color = 0xFF009380;
//                finalImage = processingBitmap_Blur(bitmap_Source);
//                imageAfter.setImageBitmap(finalImage);
//            }
//        });

        color4 = (ImageButton)findViewById(R.id.color4);
        color4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = 0xFF171515;
                finalImage = processingBitmap_Blur(bitmap_Source);
                imageAfter.setImageBitmap(finalImage);
            }
        });

        color5 = (ImageButton)findViewById(R.id.color5);
        color5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = 0xFF663399;
                finalImage = processingBitmap_Blur(bitmap_Source);
                imageAfter.setImageBitmap(finalImage);
            }
        });

//        color6 = (ImageButton)findViewById(R.id.color6);
//        color6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                color = 0xFFA6D8AF;
//                finalImage = processingBitmap_Blur(bitmap_Source);
//                imageAfter.setImageBitmap(finalImage);
//            }
//        });

        color7 = (ImageButton)findViewById(R.id.color7);
        color7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = 0xFFA26B14;
                finalImage = processingBitmap_Blur(bitmap_Source);
                imageAfter.setImageBitmap(finalImage);
            }
        });

//        color8 = (ImageButton)findViewById(R.id.color8);
//        color8.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                color = 0xFF627E45;
//                finalImage = processingBitmap_Blur(bitmap_Source);
//                imageAfter.setImageBitmap(finalImage);
//            }
//        });

        color9 = (ImageButton)findViewById(R.id.color9);
        color9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = 0xFF4785F4;
                finalImage = processingBitmap_Blur(bitmap_Source);
                imageAfter.setImageBitmap(finalImage);
            }
        });

        color10 = (ImageButton)findViewById(R.id.color10);
        color10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = 0xFFBF0D0D;
                finalImage = processingBitmap_Blur(bitmap_Source);
                imageAfter.setImageBitmap(finalImage);
            }
        });

        color11 = (ImageButton)findViewById(R.id.color11);
        color11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = 0xFFDC143C;
                finalImage = processingBitmap_Blur(bitmap_Source);
                imageAfter.setImageBitmap(finalImage);
            }
        });

        color12 = (ImageButton)findViewById(R.id.color12);
        color12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = 0xFFFF3E96;
                finalImage = processingBitmap_Blur(bitmap_Source);
                imageAfter.setImageBitmap(finalImage);
            }
        });

        color13 = (ImageButton)findViewById(R.id.color13);
        color13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = 0xFF800080;
                finalImage = processingBitmap_Blur(bitmap_Source);
                imageAfter.setImageBitmap(finalImage);
            }
        });

        color14 = (ImageButton)findViewById(R.id.color14);
        color14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = 0xFF0000CD;
                finalImage = processingBitmap_Blur(bitmap_Source);
                imageAfter.setImageBitmap(finalImage);
            }
        });

//        color15 = (ImageButton)findViewById(R.id.color15);
//        color15.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                color = 0xFF008000;
//                finalImage = processingBitmap_Blur(bitmap_Source);
//                imageAfter.setImageBitmap(finalImage);
//            }
//        });

        color16 = (ImageButton)findViewById(R.id.color16);
        color16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                color = 0xFF00FF00;
                finalImage = processingBitmap_Blur(bitmap_Source);
                imageAfter.setImageBitmap(finalImage);
            }
        });

        //*

        //55555send the image to main using send button
        send = (ImageButton)findViewById(R.id.send_btn);
        send.setOnClickListener(sendListener);
        //555Ends

    }

    //555555555Send button will send the final bitmap via byte array to main activity
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
            Intent intent = new Intent(Bluredges.this, Background.class);
            intent.putExtra("finalBitmap_blueEdges", byteArray);
            setResult(RESULT_OK,intent);
            finish();
            //startActivity(intent);

        }};
    //*555555Ends

    View.OnClickListener resetListner
            = new View.OnClickListener(){

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            //  imageAfter.setImageBitmap(processingBitmap_Blur(bitmap_Source));
            blurBar.setProgress(blurBar.getMax()/100);
        }};

    SeekBar.OnSeekBarChangeListener blurBarChangeListener
            = new SeekBar.OnSeekBarChangeListener(){

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress,
                                      boolean fromUser) {
            blurValue = progress+1;
            blurText.setText(String.valueOf(blurValue));
            finalImage = processingBitmap_Blur(bitmap_Source);
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

    private Bitmap processingBitmap_Blur(Bitmap src){
        int width = src.getWidth();
        int height = src.getHeight();

        BlurMaskFilter blurMaskFilter;
        Paint paintBlur = new Paint();

        Bitmap dest = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(dest);

        //Create background in White
        Bitmap alpha = src.extractAlpha();
        paintBlur.setColor(color);
        // paintBlur.setColor(0xF00FFFFF);
        canvas.drawBitmap(alpha, 0, 0, paintBlur);

        //Create outer blur, in White
        blurMaskFilter = new BlurMaskFilter(blurValue, BlurMaskFilter.Blur.OUTER);
        paintBlur.setMaskFilter(blurMaskFilter);
        canvas.drawBitmap(alpha, 0, 0, paintBlur);

        //Create inner blur
        blurMaskFilter = new BlurMaskFilter(blurValue, BlurMaskFilter.Blur.INNER);
        paintBlur.setMaskFilter(blurMaskFilter);
        canvas.drawBitmap(src, 0, 0, paintBlur);



        return dest;
    }

}