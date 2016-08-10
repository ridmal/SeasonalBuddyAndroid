package com.example.roshan.seasonalbuddy6.card;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.roshan.seasonalbuddy6.Frames.FrameActivity;
import com.example.roshan.seasonalbuddy6.R;
import com.example.roshan.seasonalbuddy6.Template.Main2Activity;
import com.example.roshan.seasonalbuddy6.Update.UpdateActivity;
import com.example.roshan.seasonalbuddy6.clipselect.StyleActivity;
import com.example.roshan.seasonalbuddy6.clipselect.clipmover.StickerImageView;
import com.example.roshan.seasonalbuddy6.cradbackground.Background;
import com.example.roshan.seasonalbuddy6.drawing_tool.DrawingActivity;
import com.example.roshan.seasonalbuddy6.text.tool.InsertText;
import com.example.roshan.seasonalbuddy6.text.tool.StickerTextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.example.roshan.seasonalbuddy6.Update.app.AppController;
import com.example.roshan.seasonalbuddy6.webconnectors.SendingMailDetails;

public class CardActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    private static final int REQUEST_CODE_TEXT = 10;
    private static final int REQUEST_CODE_IMAGE = 11;
    private static final int REQUEST_CODE_BACK_IMAGE = 12;
    private static final int REQUEST_CARD_IMAGE = 13;
    private static final int RESULT_LOAD_IMG = 15;
    private static final int SELECT_PICTURE = 18;
    private static final int REQUEST_CODE_LOAD_TEMPLATE = 25;
    private android.widget.RelativeLayout.LayoutParams layoutParams;
    String msg;
    private static final int CAMERA_REQUEST = 1888;
    private static final int RESULT_LOAD_IMAGE = 1;
    private Boolean isFabOpen = false;
    private Boolean isFabBackOpen = false;
    private Boolean isBlur = false;
    private FloatingActionButton fab,fab1,fab2,fab3,fab4;
    private ImageButton fabCamera,fabPic,fabColor;
    private Animation fab_open, fab_close, rotate_forward,rotate_backward,fab_show,fab_hide;
    File file;
    RelativeLayout card_content;

    TextView t1;
    ScaleGestureDetector scaleGestureDetector;

    // these matrices will be used to move and zoom image
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
    private int REQUEST_CODE_BACK_DRAWING = 16;
    ImageView template;
    private static final String TAG = "CardActivity";
    private Bitmap bitmapImage;

    private String urlString = "http://192.168.43.80:8086/savereciver";
    private ProgressDialog pDialog;

    Bitmap originalImage, resizedBitmap;
    int width, height;
    int newWidth = 460, newHeight = 272;
    float scaleWidth, scaleHeight;
    Matrix matrix;
    ByteArrayOutputStream outputStream;
    String imgDecodableString;
    ImageView mergeImage;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_card);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
        mergeImage = (ImageView)findViewById(R.id.cardImg);

        card_content = (RelativeLayout) findViewById(R.id.card_content);
        fab = (FloatingActionButton)findViewById(R.id.fab);
        fab1 = (FloatingActionButton)findViewById(R.id.fab1);
        fab2 = (FloatingActionButton)findViewById(R.id.fab2);
        fab3 = (FloatingActionButton)findViewById(R.id.fab3);
        fab4 = (FloatingActionButton)findViewById(R.id.fab4);
        fabCamera = (ImageButton) findViewById(R.id.fabCamera);
        fabPic = (ImageButton) findViewById(R.id.fabPic);
        fabColor = (ImageButton) findViewById(R.id.fabColor);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);
//        fab_show = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_show);
//        fab_hide = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_hide);
        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);
        fab4.setOnClickListener(this);
        fabCamera.setOnClickListener(this);
        fabPic.setOnClickListener(this);
        fabColor.setOnClickListener(this);

        template = (ImageView)findViewById(R.id.cardImg);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add("Save").setIcon(R.drawable.save_card).setOnMenuItemClickListener(this.SaveImageClickListener)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menu.add("Email").setIcon(R.drawable.email).setOnMenuItemClickListener(this.EmailImageClickListener)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        menu.add("Send").setIcon(R.drawable.send_card).setOnMenuItemClickListener(this.SendImageClickListener)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
        return true;
    }

    MenuItem.OnMenuItemClickListener EmailImageClickListener = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            callPopup();
            return false;
        }
    };

    MenuItem.OnMenuItemClickListener SaveImageClickListener = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {

            OutputStream outputStream;

            card_content.setDrawingCacheEnabled(true);
            bitmap = card_content.getDrawingCache();

            File filepath = Environment.getExternalStorageDirectory();
            //filepath.list();
            File dir = new File(filepath + "/Seasonal Buddy/My Cards");
            if(!dir.exists()){
                dir.mkdirs(); //make new directory if their not exist
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd'T'HHmmss");
            String timeStamp = dateFormat.format(new Date());
            String imageFileName = "picture_" + timeStamp + ".png";
            file = new File(dir,imageFileName); //make image name like 'image'
            try {
                sendBroadcast(new Intent(
                        Intent.ACTION_MEDIA_MOUNTED,
                        Uri.parse("file://" + filepath)));//save image in this path
            } catch (Exception e) {
                e.getMessage();
            }
            Toast.makeText(CardActivity.this, "Image saved to Sd Card!", Toast.LENGTH_SHORT).show();

            try {
                outputStream = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);//convert image to PNG
                outputStream.flush();
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
    };
    //send image to the other device via bluetooth,wifi and social media
    MenuItem.OnMenuItemClickListener SendImageClickListener = new MenuItem.OnMenuItemClickListener() {
        @Override
        public boolean onMenuItemClick(MenuItem item) {
            //Bitmap bigImage = BitmapFactory.decodeResource(getResources(), R.drawable.trolltunga);
            Bitmap smallImage = BitmapFactory.decodeResource(getResources(), R.drawable.banner);
            // Bitmap mergedImages = createSingleImageFromMultipleImages(bigImage, smallImage);
            Bitmap mergedImages = combineImages(bitmap, smallImage);
            mergeImage.setImageBitmap(mergedImages);
            File img = new File(String.valueOf(file));
            Uri imageuri = Uri.fromFile(img);

            Intent send_img = new Intent(Intent.ACTION_SEND);
            send_img.putExtra(Intent.EXTRA_SUBJECT, "email_subject");
            send_img.putExtra(Intent.EXTRA_STREAM, imageuri);
            send_img.putExtra(Intent.EXTRA_TEXT, "message");
            send_img.setType("image/png");
            startActivity(Intent.createChooser(send_img, "Send Card..."));
//
            return false;
        }
    };


    private void callPopup() {

        LayoutInflater layoutInflater = (LayoutInflater) getBaseContext()
                .getSystemService(LAYOUT_INFLATER_SERVICE);

        View popupView = layoutInflater.inflate(R.layout.popup, null);

        final PopupWindow popupWindow = new PopupWindow(popupView,
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT,
                true);

        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);

        popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);
        final EditText Name = (EditText) popupView.findViewById(R.id.edtimageName);
        final EditText Message = (EditText) popupView.findViewById(R.id.etMessage);
        final EditText Email = (EditText) popupView.findViewById(R.id.etEmail);

        ((ImageButton) popupView.findViewById(R.id.sendEmailBtn))
                .setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {
//                        Toast.makeText(getApplicationContext(),
//                                Attributes.Name.getText().toString(), Toast.LENGTH_LONG).show();

                        Bitmap smallImage = BitmapFactory.decodeResource(getResources(), R.drawable.banner);
                        // Bitmap mergedImages = createSingleImageFromMultipleImages(bigImage, smallImage);
                        Bitmap mergedImages = combineImages(bitmap, smallImage);
                        mergeImage.setImageBitmap(mergedImages);
                        File img = new File(String.valueOf(file));
                        Uri imageuri = Uri.fromFile(img);
                        String senderName = String.valueOf(Name.getText());
                        String message = String.valueOf(Message.getText());
                        String reciever = String.valueOf(Email.getText());

                       // makeStringRequest(senderName,reciever);
                        SendingMailDetails smd = new SendingMailDetails(CardActivity.this,senderName,reciever);
                        smd.sendemaildetails();    // save receiver details on maindatabase

                        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
                        emailIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        emailIntent.setType("plain/text");
                        emailIntent.setClassName("com.google.android.gm", "com.google.android.gm.ComposeActivityGmail");
                        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL,new String[]{reciever});///new String[]{"someone@gmail.com"}
                        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Seasonal Buddy Card From "+senderName);
                        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT,message);
                        emailIntent.putExtra(Intent.EXTRA_STREAM, imageuri);
                        startActivity(emailIntent);

                        try {
                            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
                            finish();
                            //Log.i("Finished sending email...", "");
                        }
                        catch (android.content.ActivityNotFoundException ex) {
                            Toast.makeText(CardActivity.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                        }
                        popupWindow.dismiss();
                    }

                });

        ((ImageButton) popupView.findViewById(R.id.cancleEmailBtn))
                .setOnClickListener(new View.OnClickListener() {

                    public void onClick(View arg0) {

                        popupWindow.dismiss();
                    }
                });
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

    public Bitmap combineImages(Bitmap c, Bitmap s) { // can add a 3rd parameter 'String loc' if you want to save the new image - left some code to do that at the bottom
        Bitmap cs = null;
        int width, height = 0;

        if(c.getWidth() > s.getWidth()) {
            width = s.getWidth();
            height = c.getHeight() + 100;
        } else {
            width = c.getWidth();
            height = c.getHeight() + 100;
        }

        cs = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        Canvas comboImage = new Canvas(cs);

        comboImage.drawBitmap(c, 0f, 0f, null);
        comboImage.drawBitmap(s, 0f, c.getHeight(), null);

        File filepath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        //filepath.list();
        File dir = new File(filepath + "/Seasonal Buddy/Cards");
        if(!dir.exists()) {
            dir.mkdirs(); //make new directory if their not exist
        }
        file = new File(dir, "image.png"); //make image name like 'image'

        try {
            sendBroadcast(new Intent(
                    Intent.ACTION_MEDIA_MOUNTED,
                    Uri.parse("file://" + filepath)));//save image in this path
        } catch (Exception e) {
            e.getMessage();
        }
        Toast.makeText(CardActivity.this, "Image saved to Sd Card!", Toast.LENGTH_SHORT).show();

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            cs.compress(Bitmap.CompressFormat.PNG, 100, outputStream);//convert image to PNG
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cs;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_template)
        {
            onLoadTemplate();
        } else if (id == R.id.nav_update)
        {
            Intent update = new Intent(CardActivity.this, UpdateActivity.class);
            startActivity(update);
        } else if (id == R.id.nav_frame)
        {
            Intent frame = new Intent(CardActivity.this, FrameActivity.class);
            startActivity(frame);
        } else if (id == R.id.nav_share)
        {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onLoadTemplate()
    {
        Intent template = new Intent(CardActivity.this, Main2Activity.class);
        startActivityForResult(template,REQUEST_CODE_LOAD_TEMPLATE);

    }
    @Override
    public void onClick(View v)
    {
        int id = v.getId();
        switch (id){
            case R.id.fab:
                animateFAB();
                break;
            case R.id.fab1:
                animateFAB();
                Intent intent_drawing = new Intent(this, DrawingActivity.class);
                startActivityForResult(intent_drawing,REQUEST_CODE_BACK_DRAWING);
                break;
            case R.id.fab2:
                animateFAB();
                Intent intent_back = new Intent(CardActivity.this,Background.class);
                startActivityForResult(intent_back,REQUEST_CODE_BACK_IMAGE);
                break;
            case R.id.fab3:
                animateFAB();
                Intent intent_text = new Intent(CardActivity.this, InsertText.class);
                startActivityForResult(intent_text,REQUEST_CODE_TEXT);
                break;
            case R.id.fab4:
                animateFAB();
                onClickListner();
                break;
        }
    }

    private void animateFAB()
    {
        if(isFabOpen){

            fab.startAnimation(rotate_backward);
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab3.startAnimation(fab_close);
            fab4.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            fab4.setClickable(false);
            isFabOpen = false;
        } else {

            fab.startAnimation(rotate_forward);
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);
            fab4.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            fab4.setClickable(true);
            isFabOpen = true;
        }
    }


//    public String getPathFromURI(Uri contentUri) {
//        String res = null;
//        String[] proj = {MediaStore.Images.Media.DATA};
//        Cursor cursor = getContentResolver().query(contentUri, proj, null, null, null);
//        if (cursor.moveToFirst()) {
//            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//            res = cursor.getString(column_index);
//        }
//        cursor.close();
//        return res;
//    }

//    private void createimageview(int imgno) {
//        StickerImageView im = new StickerImageView(CardActivity.this);
//        im.setImageResource(imgno);
//        card_content.addView(im);
//    }
    private void createimageview(Bitmap imgno) {
        StickerImageView clip = new StickerImageView(CardActivity.this);
        clip.setImageBitmap(imgno);
        card_content.addView(clip);
    }
    public void onClickListner()
    {
        Intent intent = new Intent(CardActivity.this,StyleActivity.class);
        startActivityForResult(intent,REQUEST_CODE_IMAGE);
    }

    private void createTextview(String inputMessage,int inputSize, int inputColor, String inputStyle){

        StickerTextView tv_sticker = new StickerTextView(CardActivity.this);

        tv_sticker.setText(inputMessage);
        tv_sticker.setcolor(inputColor);
        tv_sticker.setMin(inputSize);
        tv_sticker.setsize(100);

        card_content.addView(tv_sticker);

        switch (inputStyle) {
            case "BirchStd":
                Typeface tface1 = Typeface.createFromAsset(getAssets(), "BirchStd.otf");
                tv_sticker.setTypeface(tface1);
                break;
            case "times":
                Typeface tface2 = Typeface.createFromAsset(getAssets(), "times.ttf");
                tv_sticker.setTypeface(tface2);
                break;
            case "davidbd":
                Typeface tface3 = Typeface.createFromAsset(getAssets(), "davidbd.ttf");
                tv_sticker.setTypeface(tface3);
                break;
            case "GOTHIC":
                Typeface tface4 = Typeface.createFromAsset(getAssets(), "GOTHIC.TTF");
                tv_sticker.setTypeface(tface4);
                break;
            case "Mopey":
                Typeface tface5 = Typeface.createFromAsset(getAssets(), "Mopey.ttf");
                tv_sticker.setTypeface(tface5);
                break;
            case "Amal":
                Typeface tface6 = Typeface.createFromAsset(getAssets(), "Amal.TTF");
                tv_sticker.setTypeface(tface6);
                break;
            default: {
                Typeface tf5 = Typeface.createFromAsset(getAssets(), "Mopey.ttf");
                tv_sticker.setTypeface(tf5);
            };
        }
    }
    // For moving
    private float move_orgX =-1, move_orgY = -1;

    //moving the textView working
    public boolean onTouch(View v, MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                move_orgX = event.getRawX();
                move_orgY = event.getRawY();

                break;
            case MotionEvent.ACTION_MOVE:
                float offsetX = event.getRawX() - move_orgX;
                float offsetY = event.getRawY() - move_orgY;
                v.setX(v.getX() + offsetX);
                v.setY(v.getY() + offsetY);
                move_orgX = event.getRawX();
                move_orgY = event.getRawY();

                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;

    }

    //Scaling the textView
    @Override
    public boolean onTouchEvent (MotionEvent event){
        // TODO Auto-generated method stub
        scaleGestureDetector.onTouchEvent(event);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImageView im = (ImageView) findViewById(R.id.cardImg);
        if (requestCode == CAMERA_REQUEST && resultCode == RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            im.setImageBitmap(photo);//set capture image to the styleImage ImageView
        }
        if (requestCode == RESULT_LOAD_IMAGE && requestCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            im.setImageURI(selectedImage);
        }
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_TEXT) {
            Bundle getbun = data.getExtras();
            String a = getbun.getString("msg"); //getting the inputted text from user
            int b = getbun.getInt("size");      //getting the size of text
            int c = getbun.getInt("color");     //getting the color
            String d = getbun.getString("type");    //getting the font style
            createTextview(a, b, c, d);
        }

//clip select and load
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_IMAGE) {
            Bitmap b = data.getParcelableExtra("key");
            createimageview(b);
        }
        //template select ans set to background
        if(resultCode==RESULT_OK && requestCode==REQUEST_CODE_LOAD_TEMPLATE){
            Bitmap b = data.getParcelableExtra("key");
            createimageview2(b);

        }
//
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_BACK_IMAGE) {
            StickerImageView img = new StickerImageView(CardActivity.this);
            if (data.hasExtra("finalBitmap")) {
                byte[] byteArray = data.getExtras().getByteArray("finalBitmap");
                Bitmap bitmapImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                img.setImageBitmap(bitmapImage);
                card_content.addView(img);
            }
        }

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_BACK_DRAWING) {
            StickerImageView imd = new StickerImageView(CardActivity.this);
            if (data.hasExtra("finalBitmapDrawing")) {
                byte[] byteArray = data.getExtras().getByteArray("finalBitmapDrawing");
                Bitmap bitmapImage = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
                imd.setImageBitmap(bitmapImage);
                card_content.addView(imd);
            }
        }

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

            template.setImageBitmap(bitmapImage);

        }
    }
    private void createimageview2(Bitmap imgno) {
        Drawable dr = new BitmapDrawable(imgno);
        card_content.setBackground(dr);
    }

    public class simpleOnScaleGestureListener extends
            ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            // TODO Auto-generated method stub
            float size = t1.getTextSize();
            Log.d("TextSizeStart", String.valueOf(size));

            float factor = detector.getScaleFactor();
            Log.d("Factor", String.valueOf(factor));


            float product = size * factor;
            Log.d("TextSize", String.valueOf(product));
            t1.setTextSize(TypedValue.COMPLEX_UNIT_PX, product);

            size = t1.getTextSize();
            Log.d("TextSizeEnd", String.valueOf(size));
            return true;
        }
    }
    private void makeStringRequest(final String rname,final String remail) {

        showpDialog();


        StringRequest strReq = new StringRequest(Request.Method.POST,
                urlString, new Response.Listener<String>() {

            // for sending the string with url we can add (+) the string to url and send....

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                pDialog.hide();
                Toast.makeText(CardActivity.this,response.toString(),Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        })
        {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email",remail);
                params.put("name", rname);

                return params;
            }

        }
                ;
        AppController.getInstance().addToRequestQueue(strReq);

    }

    private void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

}
