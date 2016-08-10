package com.example.roshan.seasonalbuddy6.drawing_tool;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.roshan.seasonalbuddy6.R;
import com.example.roshan.seasonalbuddy6.card.CardActivity;
import com.guna.libcolorpicker.ColorPickerOval;
import com.guna.libcolorpicker.ColorPickerRect;
import com.guna.libcolorpicker.OnColorChangedListener;

import java.io.ByteArrayOutputStream;

public class DrawingActivity extends Activity implements View.OnClickListener,OnColorChangedListener{

    private DrawingView drawView;
    private float smallBrush, mediumBrush, largeBrush;
    private ImageButton currPaint,drawBtn,eraseBtn,newBtn, saveBtn;
    private byte[] byteArray;
    private Object Width,Height;
    //private Dialog brushDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawing);

        //get drawing view
        drawView = (DrawingView) findViewById(R.id.drawing);

        //get the palette and first color button
//       // LinearLayout paintLayout = (LinearLayout) findViewById(R.id.paint_colors);
//        currPaint = (ImageButton) paintLayout.getChildAt(0);
//        currPaint.setImageDrawable(getResources().getDrawable(R.drawable.paint_pressed));

        //size from dimensions
        smallBrush = getResources().getInteger(R.integer.small_size);
        mediumBrush = getResources().getInteger(R.integer.medium_size);
        largeBrush = getResources().getInteger(R.integer.large_size);

        //draw button
        drawBtn = (ImageButton) findViewById(R.id.draw_btn);
        drawBtn.setOnClickListener(this);

        //set initial size
        drawView.setBrushSize(mediumBrush);

        //erase button
        eraseBtn = (ImageButton) findViewById(R.id.erase_btn);
        eraseBtn.setOnClickListener(this);

        //new button
        newBtn = (ImageButton) findViewById(R.id.new_btn);
        newBtn.setOnClickListener(this);

        //save button

        ImageButton addDraw = (ImageButton)findViewById(R.id.addDraw);
        addDraw.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                drawView.setDrawingCacheEnabled(true);
                drawView.buildDrawingCache();
                addImageToCard();
            }
        });

        ImageButton add_color = (ImageButton)findViewById(R.id.color_chooser);
        add_color.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPicker1(v);//open color picker
            }
        });
    }


    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.draw_btn){
            //draw button clicked
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle("Brush size:");
            brushDialog.setContentView(R.layout.brush_chooser);

            //listen for clicks on size buttons
            ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(smallBrush);
                    drawView.setLastBrushSize(smallBrush);
                    drawView.setErase(false);
                    brushDialog.dismiss();
                }
            });
            ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(mediumBrush);
                    drawView.setLastBrushSize(mediumBrush);
                    drawView.setErase(false);
                    brushDialog.dismiss();
                }
            });

            ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setBrushSize(largeBrush);
                    drawView.setLastBrushSize(largeBrush);
                    drawView.setErase(false);
                    brushDialog.dismiss();
                }
            });
            //show and wait for user interaction
            brushDialog.show();
        }
        else if(v.getId()==R.id.erase_btn){
            //switch to erase - choose size
            final Dialog brushDialog = new Dialog(this);
            brushDialog.setTitle("Eraser size:");
            brushDialog.setContentView(R.layout.brush_chooser);
            //size buttons
            ImageButton smallBtn = (ImageButton)brushDialog.findViewById(R.id.small_brush);
            smallBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(smallBrush);
                    brushDialog.dismiss();
                }
            });
            ImageButton mediumBtn = (ImageButton)brushDialog.findViewById(R.id.medium_brush);
            mediumBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(mediumBrush);
                    brushDialog.dismiss();
                }
            });
            ImageButton largeBtn = (ImageButton)brushDialog.findViewById(R.id.large_brush);
            largeBtn.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    drawView.setErase(true);
                    drawView.setBrushSize(largeBrush);
                    brushDialog.dismiss();
                }
            });
            brushDialog.show();
        }
        else if(v.getId()==R.id.new_btn){
            //new button
            AlertDialog.Builder newDialog = new AlertDialog.Builder(this);
            newDialog.setTitle("New drawing");
            newDialog.setMessage("Start new drawing (you will lose the current drawing)?");
            newDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    drawView.startNew();
                    dialog.dismiss();
                }
            });
            newDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            newDialog.show();
        }
//        else if(v.getId()==R.id.save_btn){
//            //save drawing
//
//            AlertDialog.Builder saveDialog = new AlertDialog.Builder(this);
//            saveDialog.setTitle("Save drawing");
//            saveDialog.setMessage("Save drawing to device Gallery?");
//            saveDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener(){
//                public void onClick(DialogInterface dialog, int which){
//                    //save drawing
//                    drawView.setDrawingCacheEnabled(true);
//                    String imgSaved = MediaStore.Images.Media.insertImage(
//                            getContentResolver(), drawView.getDrawingCache(),
//                            UUID.randomUUID().toString()+".png", "drawing");
//                    if(imgSaved!=null){
//                        Toast savedToast = Toast.makeText(getApplicationContext(),
//                                "Drawing saved to Gallery!", Toast.LENGTH_SHORT);
//                        savedToast.show();
//                    }
//                    else{
//                        Toast unsavedToast = Toast.makeText(getApplicationContext(),
//                                "Oops! Image could not be saved.", Toast.LENGTH_SHORT);
//                        unsavedToast.show();
//                    }
//                    drawView.destroyDrawingCache();
//                }
//            });
//            saveDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
//                public void onClick(DialogInterface dialog, int which){
//                    dialog.cancel();
//                }
//            });
//            saveDialog.show();
//        }
    }

    public void addImageToCard(){
        //make canvas
        Canvas c = new Canvas();
        //get drawing to a bitmap
        Bitmap bit = drawView.getDrawingCache();
        //set bitmap with drawing into canvas
        c.setBitmap(bit);
        //convert canvas into ByteArray and PNG image
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bit.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byteArray = stream.toByteArray();
        //pass byte array to intent
        Intent intent = new Intent(DrawingActivity.this, CardActivity.class);
        intent.putExtra("finalBitmapDrawing", byteArray);
        setResult(RESULT_OK, intent);
        finish();  // close the activity
    }

    //circular color picker with color value
    public void openPicker1(View view) {
        new ColorPickerOval(this, this, "Choose Color", Color.WHITE).show();
    }
    //color boxes
    public void openPicker2(View view) {
        new ColorPickerRect(this,this, "", Color.BLACK, Color.WHITE).show();
    }

    @Override
    public void colorChanged(String key, int color) {
        this.findViewById(android.R.id.content)
                .setBackgroundColor(color);
       // paintClicked(color);
        //get choose color and convert to string color value
        String hexColor = String.format("#%06X", (0xFFFFFF & color));
        //add color to the brush
        drawView.setColor(hexColor);
    }
}
