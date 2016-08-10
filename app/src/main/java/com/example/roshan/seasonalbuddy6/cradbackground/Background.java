package com.example.roshan.seasonalbuddy6.cradbackground;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.roshan.seasonalbuddy6.R;
import com.example.roshan.seasonalbuddy6.card.CardActivity;

import java.io.ByteArrayOutputStream;

/**
 * Created by Roshan on 6/15/2016.
 */
public class Background extends AppCompatActivity implements View.OnClickListener {


    private static final int REQUEST_LOAD_CROP_IMAGE = 22;
    ImageView imgView;
    Bitmap bitmapImage;

    private static final int CAMERA_REQUEST = 1888;
    //00000variables to load iamge from gallery
    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    //00000Ends

    //1111111variables for compressing image taken from gallery
    Bitmap originalImage;
    int width, height;
    int newWidth = 400, newHeight = 240;
    float scaleWidth, scaleHeight;
    Matrix matrix;
    ByteArrayOutputStream outputStream;
    //111111End

    //222222Variables for getting the add effect bitmap from actvity 2
    ImageButton addEffect;
    private static final int REQUEST_CODE_EFFECT = 10;
    ImageButton camera;
    private static final int REQUEST_IMAGE__CARD = 15;
    byte[] byteArray;
    //*2222Ends

    //333333variables for add brightness
    ImageButton addBright;
    private static final int REQUEST_CODE_BRIGHT = 11;
    //33333Ends

    //333333variables for add brightness
    ImageButton frame;
    private static final int REQUEST_ADD_FRAME = 19;
    //33333Ends

    //44444variables for blur effects
    ImageButton blurImage;
    private static final int REQUEST_CODE_BLUR = 12;
    //*44444Ends


    //55555555variables for blur edges effects
    ImageButton blurEdges,imageEdit;
    private static final int REQUEST_CODE_BLUREDGE = 13;
    //*55555Ends

    Animation fab_open,fab_close;
    private boolean isButtonOpen =false;
    Drawable defaultImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.background);

        imgView = (ImageView)findViewById(R.id.imgView);
        defaultImage = getResources().getDrawable(R.drawable.birthday5);
        bitmapImage = ((BitmapDrawable)defaultImage).getBitmap();

        /////
        //originalImage = BitmapFactory.decodeFile(imgDecodableString);
        width = bitmapImage.getWidth();
        height = bitmapImage.getHeight();

        matrix = new Matrix();
        scaleWidth = ((float) newWidth) / width;
        scaleHeight = ((float) newHeight) / height;
        matrix.postScale(scaleWidth, scaleHeight);
        // matrix.postRotate(45);
        bitmapImage = Bitmap.createBitmap(bitmapImage, 0, 0, width, height, matrix, true);
        outputStream = new ByteArrayOutputStream();
        bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        //11111111Ends
        imgView.setImageBitmap(bitmapImage);

        ImageButton addimgbtn = (ImageButton)findViewById(R.id.addImageBtn);
        addimgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bulidcache();
                addimagetocard();
            }
        });
        //click on add image button this is optional not necessary only to check on studio

        frame = (ImageButton)findViewById(R.id.frameBtn);
        frame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setClass(Background.this, Addframes.class);
                i.putExtra("Bitmap_frame", bitmapImage);
                //startActivity(i);
                startActivityForResult(i,REQUEST_ADD_FRAME);
            }
        });

        //Click on the effect button to add effects for the images
        addEffect = (ImageButton) findViewById(R.id.effect_btn);
        addEffect.setOnClickListener(this);

        //3333333Click on brightness button to add brightness
        addBright = (ImageButton) findViewById(R.id.brightness_btn);
        addBright.setOnClickListener(brightListner);

        //444444444Click on blur image bitton to blur
        blurImage = (ImageButton) findViewById(R.id.blur_btn);
        blurImage.setOnClickListener(blurListner);

        //55555555Click on blur image bitton to blur
        blurEdges = (ImageButton) findViewById(R.id.bluredge_btn);
        blurEdges.setOnClickListener(blurEdgesListner);
        camera = (ImageButton)findViewById(R.id.bunnyButton);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, CAMERA_REQUEST);
                }
            }
        });

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        imageEdit = (ImageButton)findViewById(R.id.imageEdit);
        imageEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                animateEditButton();
            }
        });

        ImageButton rotate = (ImageButton)findViewById(R.id.rotateBtn);
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                bitmapImage = Bitmap.createBitmap(bitmapImage , 0, 0, bitmapImage.getWidth(), bitmapImage.getHeight(), matrix, true);
                imgView.setImageBitmap(bitmapImage);
            }
        });
    }

    private void animateEditButton() {
        if(isButtonOpen){
            addEffect.startAnimation(fab_close);
            addBright.startAnimation(fab_close);
            blurEdges.startAnimation(fab_close);
            blurImage.startAnimation(fab_close);
            addEffect.setClickable(false);
            addBright.setClickable(false);
            blurEdges.setClickable(false);
            blurImage.setClickable(false);
            isButtonOpen = false;
        } else {

            addEffect.startAnimation(fab_open);
            addBright.startAnimation(fab_open);
            blurEdges.startAnimation(fab_open);
            blurImage.startAnimation(fab_open);
            addEffect.setClickable(true);
            addBright.setClickable(true);
            blurEdges.setClickable(true);
            blurImage.setClickable(true);
            isButtonOpen = true;
        }
    }

    public void bulidcache(){
        imgView.buildDrawingCache();
    }

    public void addimagetocard(){


                Bitmap bit = imgView.getDrawingCache();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bit.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byteArray = stream.toByteArray();
                //pass byte array to intent
                Intent intent = new Intent(Background.this, CardActivity.class);
                intent.putExtra("finalBitmap", byteArray);
                setResult(RESULT_OK, intent);
                finish();
    }

    //22222Send the original bitmap to effects activity
    @Override
    public void onClick(View v) {

        // Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.shazee );
        Intent i = new Intent();
        i.setClass(Background.this, Effects.class);
        i.putExtra("Bitmap", bitmapImage);
        //startActivity(i);
        startActivityForResult(i, REQUEST_CODE_EFFECT);
    }
    //*22222Ends

    //3333333Send the original bitmap to brightness activity
    View.OnClickListener brightListner
            = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent i = new Intent();
            i.setClass(Background.this, Brightness.class);
            i.putExtra("Bitmap_bright", bitmapImage);
            //startActivity(i);
            startActivityForResult(i, REQUEST_CODE_BRIGHT);

        }
    };
    //*333Ends

    //44444444Send the original bitmap to blur activity
    View.OnClickListener blurListner
            = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent i = new Intent();
            i.setClass(Background.this, Blur.class);
            i.putExtra("Bitmap_blur", bitmapImage);
            //startActivity(i);
            startActivityForResult(i, REQUEST_CODE_BLUR);

        }
    };
    //*444Ends

    //55555555Send the original bitmap to blurEdges activity
    View.OnClickListener blurEdgesListner
            = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            // TODO Auto-generated method stub
            Intent i = new Intent();
            i.setClass(Background.this, Bluredges.class);
            i.putExtra("Bitmap_blurEdges", bitmapImage);
            //startActivity(i);
            startActivityForResult(i, REQUEST_CODE_BLUREDGE);

        }
    };
    //*555Ends

    //000000Load image from gallery
    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }
    //000001ends

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {

            //000000Load image from gallery
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                imgView = (ImageView) findViewById(R.id.imgView);
                // Set the Image in ImageView after decoding the String
                //              bitmapImage = BitmapFactory.decodeFile(imgDecodableString);

                //11111111code snippet to compress the image taken from galeery
                //otherwise to big to pass from one activity to another
                originalImage = BitmapFactory.decodeFile(imgDecodableString);
                width = originalImage.getWidth();
                height = originalImage.getHeight();

                matrix = new Matrix();
                scaleWidth = ((float) newWidth) / width;
                scaleHeight = ((float) newHeight) / height;
                matrix.postScale(scaleWidth, scaleHeight);
                // matrix.postRotate(45);
                bitmapImage = Bitmap.createBitmap(originalImage, 0, 0, width, height, matrix, true);
                outputStream = new ByteArrayOutputStream();
                bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                //11111111Ends

                imgView.setImageBitmap(bitmapImage);

            }
            //000000000 Ends Load image from gallery

            //222222code to get the new bitmap for the main activity from effects activity
            if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_EFFECT) {
                if (data.hasExtra("finalBitmap")) {
                    byteArray = data.getExtras().getByteArray("finalBitmap");
                    bitmapImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                    imgView.setImageBitmap(bitmapImage);
                }
            }
            //*22222222Ends

            //33333333code to get the new bitmap for the main activity from brightness activity
            if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_BRIGHT) {
                if (data.hasExtra("finalBitmap_bright")) {
                    byteArray = data.getExtras().getByteArray("finalBitmap_bright");
                    bitmapImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                    imgView.setImageBitmap(bitmapImage);
                }
            }
            //*3333333333Ends
            //44444444444code to get the new bitmap for the main activity from blur activity
            if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_BLUR) {
                if (data.hasExtra("finalBitmap_blur")) {
                    byteArray = data.getExtras().getByteArray("finalBitmap_blur");
                    bitmapImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                    imgView.setImageBitmap(bitmapImage);
                }
            }

            //555555555555code to get the new bitmap for the main activity from blur activity
            if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_BLUREDGE) {
                if (data.hasExtra("finalBitmap_blueEdges")) {
                    byteArray = data.getExtras().getByteArray("finalBitmap_blueEdges");
                    bitmapImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                    imgView.setImageBitmap(bitmapImage);
                }
            }
            if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK)
            {
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                Uri selectImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                imgView = (ImageView) findViewById(R.id.imgView);
                // Set the Image in ImageView after decoding the String
                //              bitmapImage = BitmapFactory.decodeFile(imgDecodableString);

                //11111111code snippet to compress the image taken from galeery
                //otherwise to big to pass from one activity to another
                originalImage = BitmapFactory.decodeFile(imgDecodableString);
                width = originalImage.getWidth();
                height = originalImage.getHeight();

                matrix = new Matrix();
                scaleWidth = ((float) newWidth) / width;
                scaleHeight = ((float) newHeight) / height;
                matrix.postScale(scaleWidth, scaleHeight);
                matrix.postRotate(90);

                bitmapImage = Bitmap.createBitmap(originalImage, 0, 0, width, height, matrix, true);
                outputStream = new ByteArrayOutputStream();
                bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
                //11111111Ends

                imgView.setImageBitmap(bitmapImage);

            }
            ///////////////////end camera
            ////frame
            if (resultCode == RESULT_OK && requestCode == REQUEST_ADD_FRAME) {
                if (data.hasExtra("finalBitmap_bright")) {
                    byteArray = data.getExtras().getByteArray("finalBitmap_bright");
                    bitmapImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                    imgView.setImageBitmap(bitmapImage);
                }
            }
            ////

        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
