package com.example.roshan.seasonalbuddy6.Frames;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.roshan.seasonalbuddy6.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FrameActivity extends AppCompatActivity implements View.OnClickListener,View.OnTouchListener{

    // these matrices will be used to move and zoom image
    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();
    // we can be in one of these 3 states
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;
    // remember some things for zooming
    private PointF start = new PointF();
    private PointF mid = new PointF();
    private float oldDist = 1f;
    private float d = 0f;
    private float newRot = 0f;
    private float[] lastEvent = null;
    public static final int CAMERA_REQUEST = 1888;
    //Shazeeeeeee coding
    ImageButton addImage,camera;
    Bitmap finalImage;
    ImageView view, view2;
    ImageButton frame1, frame2, frame3, frame4, frame5, frame6, frame7, frame8, frame9, frame10;
    ImageButton frame11, frame12, frame13, frame14, frame15, frame16, frame17, frame18, frame19, frame20, frame21, frame22, frame23, frame24;
    //**

    //88888galerryyyyyy variables
    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    static Bitmap bitmapImage;
    //8888Ends

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frame);
        //       ImageView view = (ImageView) findViewById(R.id.imageView);
        view = (ImageView) findViewById(R.id.imageView);
        view.setOnTouchListener(this);

        //shazeeeeeeee
        addImage = (ImageButton)findViewById(R.id.addImage);

        camera = (ImageButton)findViewById(R.id.cameraBtn);

        view2 = (ImageView) findViewById(R.id.imageView2);

        frame1 = (ImageButton)findViewById(R.id.framebtn1);
        frame1.setOnClickListener(this);

        frame2 = (ImageButton)findViewById(R.id.framebtn2);
        frame2.setOnClickListener(this);

        frame3 = (ImageButton)findViewById(R.id.framebtn3);
        frame3.setOnClickListener(this);

        frame4 = (ImageButton)findViewById(R.id.framebtn4);
        frame4.setOnClickListener(this);

        frame5 = (ImageButton)findViewById(R.id.framebtn5);
        frame5.setOnClickListener(this);

        frame6 = (ImageButton)findViewById(R.id.framebtn6);
        frame6.setOnClickListener(this);

        frame7 = (ImageButton)findViewById(R.id.framebtn7);
        frame7.setOnClickListener(this);

        frame8 = (ImageButton)findViewById(R.id.framebtn8);
        frame8.setOnClickListener(this);

        frame9 = (ImageButton)findViewById(R.id.framebtn9);
        frame9.setOnClickListener(this);

        frame10 = (ImageButton)findViewById(R.id.framebtn10);
        frame10.setOnClickListener(this);

        frame11 = (ImageButton)findViewById(R.id.framebtn11);
        frame11.setOnClickListener(this);

        frame12 = (ImageButton)findViewById(R.id.framebtn12);
        frame12.setOnClickListener(this);

        frame13 = (ImageButton)findViewById(R.id.framebtn13);
        frame13.setOnClickListener(this);

        frame14 = (ImageButton)findViewById(R.id.framebtn14);
        frame14.setOnClickListener(this);

        frame15 = (ImageButton)findViewById(R.id.framebtn15);
        frame15.setOnClickListener(this);

        frame16 = (ImageButton)findViewById(R.id.framebtn16);
        frame16.setOnClickListener(this);

        frame17 = (ImageButton)findViewById(R.id.framebtn17);
        frame17.setOnClickListener(this);

        frame18 = (ImageButton)findViewById(R.id.framebtn18);
        frame18.setOnClickListener(this);

        frame19 = (ImageButton)findViewById(R.id.framebtn19);
        frame19.setOnClickListener(this);

        frame20 = (ImageButton)findViewById(R.id.framebtn20);
        frame20.setOnClickListener(this);

        frame21 = (ImageButton)findViewById(R.id.framebtn21);
        frame21.setOnClickListener(this);

        frame22 = (ImageButton)findViewById(R.id.framebtn22);
        frame22.setOnClickListener(this);

        frame23 = (ImageButton)findViewById(R.id.framebtn23);
        frame23.setOnClickListener(this);

        frame24 = (ImageButton)findViewById(R.id.framebtn24);
        frame24.setOnClickListener(this);


        //send
        ImageButton send = (ImageButton)findViewById(R.id.sendFrame);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                OutputStream outputStream;
                RelativeLayout fl = (RelativeLayout)findViewById(R.id.tileview);
                fl.setDrawingCacheEnabled(true);
                Bitmap bitmap = fl.getDrawingCache();

                File filepath = Environment.getExternalStorageDirectory();
                //filepath.list();
                File dir = new File(filepath + "/Seasonal Buddy/My Cards");
                if(!dir.exists()){
                    dir.mkdirs(); //make new directory if their not exist
                }
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
                String timeStamp = dateFormat.format(new Date());
                String imageFileName = "picture_" + timeStamp + ".png";
                File file = new File(dir,imageFileName); //make image name like 'image'
                try {
                    sendBroadcast(new Intent(
                            Intent.ACTION_MEDIA_MOUNTED,
                            Uri.parse("file://" + filepath)));//save image in this path
                } catch (Exception e) {
                    e.getMessage();
                }
                Toast.makeText(FrameActivity.this, "Image saved to Sd Card!", Toast.LENGTH_SHORT).show();

                try {
                    outputStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);//convert image to PNG
                    outputStream.flush();
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                File img = new File(String.valueOf(file));
                Uri imageuri = Uri.fromFile(img);

                Intent send_img = new Intent(Intent.ACTION_SEND);
                send_img.putExtra(Intent.EXTRA_SUBJECT, "email_subject");
                send_img.putExtra(Intent.EXTRA_STREAM, imageuri);
                send_img.putExtra(Intent.EXTRA_TEXT, "message");
                send_img.setType("image/png");
                startActivity(Intent.createChooser(send_img, "Send Card..."));
            }
        });

        /////camera

            camera.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                        startActivityForResult(takePictureIntent, CAMERA_REQUEST);
                    }
                }
            });

        ////end camera
    }

    //888888888888galerryyyyyyyyyyyy

    public void loadImagefromGallery(final View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                // Set the Image in ImageView after decoding the String
                bitmapImage = BitmapFactory.decodeFile(imgDecodableString);
                view.setImageBitmap(bitmapImage);

            } else {

                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }

            if (resultCode==RESULT_OK && requestCode==CAMERA_REQUEST){
                Bitmap photo = (Bitmap) data.getExtras().get("data");
                view.setImageBitmap(photo);
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }


    public boolean onTouch(View v, MotionEvent event) {
        // handle touch events here
        ImageView view = (ImageView) v;
        switch (event.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                savedMatrix.set(matrix);
                start.set(event.getX(), event.getY());
                mode = DRAG;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                oldDist = spacing(event);
                if (oldDist > 10f) {
                    savedMatrix.set(matrix);
                    midPoint(mid, event);
                    mode = ZOOM;
                }
                lastEvent = new float[4];
                lastEvent[0] = event.getX(0);
                lastEvent[1] = event.getX(1);
                lastEvent[2] = event.getY(0);
                lastEvent[3] = event.getY(1);
                d = rotation(event);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                mode = NONE;
                lastEvent = null;
                break;
            case MotionEvent.ACTION_MOVE:
                if (mode == DRAG) {
                    matrix.set(savedMatrix);
                    float dx = event.getX() - start.x;
                    float dy = event.getY() - start.y;
                    matrix.postTranslate(dx, dy);
                } else if (mode == ZOOM) {
                    float newDist = spacing(event);
                    if (newDist > 10f) {
                        matrix.set(savedMatrix);
                        float scale = (newDist / oldDist);

                        matrix.postScale(scale, scale, mid.x, mid.y);
                    }
                    if (lastEvent != null && event.getPointerCount() == 2) {
                        newRot = rotation(event);
                        float r = newRot - d;
                        float[] values = new float[9];
                        matrix.getValues(values);
                        float tx = values[2];
                        float ty = values[5];
                        float sx = values[0];
                        float xc = (view.getWidth() / 2) * sx;
                        float yc = (view.getHeight() / 2) * sx;
                        matrix.postRotate(r, tx + xc, ty + yc);
                    }
                }
                break;
        }

        view.setImageMatrix(matrix);
        return true;
    }

    /**
     * Determine the space between the first two fingers
     */
    private float spacing(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        return (float)Math.sqrt(x * x + y * y);
        //return FloatMath.sqrt(x * x + y * y);
    }

    /**
     * Calculate the mid point of the first two fingers
     */
    private void midPoint(PointF point, MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        point.set(x / 2, y / 2);
    }

    /**
     * Calculate the degree to be rotated by.
     *
     * @param event
     * @return Degrees
     */
    private float rotation(MotionEvent event) {
        double delta_x = (event.getX(0) - event.getX(1));
        double delta_y = (event.getY(0) - event.getY(1));
        double radians = Math.atan2(delta_y, delta_x);
        return (float) Math.toDegrees(radians);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id)
        {
            case R.id.framebtn1:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f1);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn2:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f2);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn3:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f3);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn4:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f4);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn5:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f5);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn6:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f6);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn7:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f7);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn8:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f8);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn9:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f9);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn10:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f10);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn11:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f11);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn12:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f12);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn13:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f13);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn14:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f14);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn15:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f15);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn16:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f16);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn17:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f17);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn18:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f18);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn19:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f19);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn20:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f20);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn21:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f21);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn22:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f22);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn23:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f23);
                view2.setImageBitmap(finalImage);
                break;
            case R.id.framebtn24:
                finalImage = BitmapFactory.decodeResource(getResources(), R.drawable.f24);
                view2.setImageBitmap(finalImage);
                break;
            default:
                break;
        }
    }
}
