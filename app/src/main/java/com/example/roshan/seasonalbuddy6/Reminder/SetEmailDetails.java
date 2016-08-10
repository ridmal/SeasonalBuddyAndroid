package com.example.roshan.seasonalbuddy6.Reminder;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roshan.seasonalbuddy6.R;

public class SetEmailDetails extends AppCompatActivity {

    EditText etrname;
    EditText etemail;
    EditText etuname;
    EditText body;
    TextView apath;
    ImageView attimg;
    Button getimage;
    Button addemail;
    private static int RESULT_LOAD_IMG = 1;
    String filepath;
    Bitmap bitmapImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_email);

        etrname = (EditText)findViewById(R.id.etrname);
        etemail =(EditText)findViewById(R.id.etemail);
        etuname = (EditText)findViewById(R.id.etuname);
        body = (EditText)findViewById(R.id.etmsg);
        apath = (TextView)findViewById(R.id.txtpath);
        attimg = (ImageView)findViewById(R.id.imgattach);
        getimage = (Button)findViewById(R.id.btngetatt);
        addemail = (Button)findViewById(R.id.btnaddemail);

        getimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });
        addemail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addemail();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
            filepath = cursor.getString(columnIndex);

            Toast.makeText(getApplicationContext(),filepath,Toast.LENGTH_LONG).show();

            apath.setText(filepath);
            cursor.close();
            // Set the Image in ImageView after decoding the String
            bitmapImage = BitmapFactory.decodeFile(filepath);
            attimg.setImageBitmap(bitmapImage);

        } else {

            Toast.makeText(this, "You haven't picked Image",
                    Toast.LENGTH_LONG).show();
        }


    }

    public void addemail(){
        Intent in = new Intent();
        Bundle bun = new Bundle();
        bun.putString("uname",etuname.getText().toString());
        bun.putString("rname",etrname.getText().toString());
        bun.putString("email",etemail.getText().toString());
        bun.putString("body",body.getText().toString());
        bun.putString("path",apath.getText().toString());
        in.putExtras(bun);
        setResult(RESULT_OK,in);
        finish();
    }
}
