package com.example.roshan.seasonalbuddy6.Reminder;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.roshan.seasonalbuddy6.R;

public class MessageDetails extends AppCompatActivity {

    String sender_name;
    String phonenumber;
    String msgdetail;

    private String contactID;     // contacts unique ID
    String contactNumber = null;
    String name;
    private static final int PICK_CONTACT = 1234;

    EditText ev;
    EditText ev2;

   // Button msgdetails;

    EditText tname;
    EditText tphone;
    EditText tmessage;
    Button okbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);

     tname = (EditText)findViewById(R.id.txtname);
      tphone = (EditText)findViewById(R.id.txtphone);
       tmessage = (EditText)findViewById(R.id.txtmsg);
         okbtn = (Button)findViewById(R.id.okbtn);

        Button cntc_btn = (Button)findViewById(R.id.button);

        cntc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(intent, PICK_CONTACT);
            }
        });



        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sender_name = tname.getText().toString();
                phonenumber = tphone.getText().toString();
                msgdetail = tmessage.getText().toString();
                //msgdetails.cancel();

                Intent in = new Intent();
                Bundle bun = new Bundle();
                bun.putString("sname",sender_name);
                bun.putString("pnumber",phonenumber);
                bun.putString("msgdetails",msgdetail);
                in.putExtras(bun);
                setResult(RESULT_OK,in);
                finish();
            }
        });


    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        ev = (EditText)findViewById(R.id.txtname);
        ev2 = (EditText)findViewById(R.id.txtphone);
//        tv = (TextView)findViewById(R.id.textView);
//        tv2 = (TextView)findViewById(R.id.textView2);

        switch (reqCode) {
            case (PICK_CONTACT) :
                if (resultCode == Activity.RESULT_OK) {
                    Uri contactData = data.getData();

                    Cursor c =  managedQuery(contactData, null, null, null, null);
                    if (c.moveToFirst()) {
                        name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
                    }
                    Cursor cursorID = getContentResolver().query(contactData,
                            new String[]{ContactsContract.Contacts._ID},
                            null, null, null);
                    if (cursorID.moveToFirst()) {
                        contactID = cursorID.getString(cursorID.getColumnIndex(ContactsContract.Contacts._ID));
                    }

                    // Using the contact ID now we will get contact phone number
                    Cursor cursorPhone = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER},
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ? AND " +
                                    ContactsContract.CommonDataKinds.Phone.TYPE + " = " +
                                    ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE,
                            new String[]{contactID},
                            null);
                    if (cursorPhone.moveToFirst()) {
                        contactNumber = cursorPhone.getString(cursorPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    }
                }
                ev.setText(name);
                ev2.setText(contactNumber);

                break;
        }
    }








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_message_details, menu);
        return true;
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
