package com.example.roshan.seasonalbuddy6.Reminder;

import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.roshan.seasonalbuddy6.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class SetAlarmUser extends AppCompatActivity {
    TimePicker TimePicker1;
    DatePicker DatePicker1;
    ImageButton Setalarm;
    Button setDate;
   ImageButton setTime;
    ImageButton msgdetails;

    ImageButton emaildetails;
    String reminder_time;

    String sender_name;
    String phonenumber;
    String msgdetail;

    String uname;
    String rname;
    String email;
    String path;
    String body;

    String tasktitle;
    Calendar now;
    DB_reminder dbr;
    boolean isEmail;

    public static int RESULT_MESSAGE_DETAILS =1;
    public static int RESULT_EMAIL_DETAILS=2;

    TimePickerDialog timePickerDialog;

    final static int RQS_1 = 1;
    static int index=1;

    public static Intent in;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm_user);
        now = Calendar.getInstance();
        dbr = new DB_reminder(SetAlarmUser.this);


        tasktitle = getIntent().getStringExtra("task");
       // Toast.makeText(this,tasktitle,Toast.LENGTH_LONG).show();

        setTime = (ImageButton)findViewById(R.id.settimebtn);
        msgdetails = (ImageButton)findViewById(R.id.msgdetailsbtn);


        setTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog getdate = new Dialog(SetAlarmUser.this);
                getdate.setTitle("Choose a Date");
                getdate.setContentView(R.layout.datepicker_layout);
                getdate.setCancelable(false);
                getdate.show();
                //   now = Calendar.getInstance();
                DatePicker1 =(DatePicker)getdate.findViewById(R.id.datePicker1);
                DatePicker1.init(
                        now.get(Calendar.YEAR),
                        now.get(Calendar.MONTH),
                        now.get(Calendar.DAY_OF_MONTH),
                        null);
                Button setdateok = (Button)getdate.findViewById(R.id.datepickerokbtn);
                setdateok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        getdate.cancel();
                    }
                });

                final Dialog gettime = new Dialog(SetAlarmUser.this);
                gettime.setTitle("Set a Time");
                gettime.setContentView(R.layout.timepicker_layout);
                gettime.setCancelable(false);
                gettime.show();

                //  now = Calendar.getInstance();
                TimePicker1 = (TimePicker)gettime.findViewById(R.id.timePicker1);
                TimePicker1.setCurrentHour(now.get(Calendar.HOUR_OF_DAY));
                TimePicker1.setCurrentMinute(now.get(Calendar.MINUTE));

                Button settimeokbtn = (Button)gettime.findViewById(R.id.timepickerokbtn);
                settimeokbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        gettime.cancel();
                    }
                });
            }
        });

        Setalarm = (ImageButton)findViewById(R.id.Setalarm);
        Setalarm.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                Calendar current = Calendar.getInstance();

                if(DatePicker1==null||TimePicker1==null){
                    Toast.makeText(getApplicationContext(),
                            "Please set the Date and Time ",
                            Toast.LENGTH_LONG).show();
                }
                if((sender_name==null||phonenumber==null)&&(rname==null||email==null)){
                    Toast.makeText(getApplicationContext(),
                            "Please set message details or email details ",
                            Toast.LENGTH_LONG).show();
                }
                else{
                    Calendar cal = Calendar.getInstance();
                    cal.set(DatePicker1.getYear(),
                            DatePicker1.getMonth(),
                            DatePicker1.getDayOfMonth(),
                            TimePicker1.getCurrentHour(),
                            TimePicker1.getCurrentMinute(),00);

                    // reminder_time= cal.getTime().toString();

                    if (cal.compareTo(current) <= 0) {
                        Toast.makeText(getApplicationContext(),
                                "Invalid Date/Time",
                                Toast.LENGTH_LONG).show();
                    }
                    else if(email==null||rname==null){
                        // reminder_time= cal.getTime().toString();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        DateFormat dateInstance   =DateFormat.getTimeInstance(DateFormat.SHORT);
                        reminder_time = sdf.format(cal.getTime());

                        setAlarm(cal,false);

                        sender_name=null;
                        phonenumber=null;
                        msgdetail=null;
                    }
                    else {
                        // reminder_time= cal.getTime().toString();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        DateFormat dateInstance   =DateFormat.getTimeInstance(DateFormat.SHORT);
                        reminder_time = sdf.format(cal.getTime());

                        setAlarm(cal,true);

                        uname=null;
                        rname=null;
                        path=null;
                        body=null;
                        email=null;
                    }
                }
//                Intent mainAvtivityIntent = new Intent(".MainActivity");
//                startActivity(mainAvtivityIntent);
            }

        });
emaildetails = (ImageButton)findViewById(R.id.imgbtnremail);
        emaildetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(SetAlarmUser.this,SetEmailDetails.class);
                startActivityForResult(in,RESULT_EMAIL_DETAILS);
            }
        });
        msgdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent in = new Intent(SetAlarmUser.this,MessageDetails.class);
                startActivityForResult(in, RESULT_MESSAGE_DETAILS);


            }
        });
    }

    //This is to get the name and the phone number from the contact list
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==RESULT_MESSAGE_DETAILS){
            Bundle getbun = data.getExtras();
            String sname = getbun.getString("sname");
            String pnumber = getbun.getString("pnumber");
            String msgdetails = getbun.getString("msgdetails");
            setmessagedetail(sname,pnumber,msgdetails);
        }
        if(resultCode==RESULT_OK && requestCode==RESULT_EMAIL_DETAILS){
            Bundle getbun = data.getExtras();
             this.uname = getbun.getString("uname");
            this.rname = getbun.getString("rname");
            this.email = getbun.getString("email");
            this.path = getbun.getString("path");
            this.body = getbun.getString("body");

        }

    }

    public void setmessagedetail(String sname,String pnumber,String msgdetails){
        this.sender_name = sname;
        this.phonenumber = pnumber;
        this.msgdetail = msgdetails;

    }

    private void setAlarm(Calendar targetCal,Boolean isEmail) {
in = new Intent();

        if(targetCal==null){
            Toast.makeText(getApplicationContext(), "Please check again the Time and Date", Toast.LENGTH_LONG).show();
        }
        else {

            if(isEmail){
           DB_reminder_email dbr = new DB_reminder_email(SetAlarmUser.this);
            boolean result= dbr.insertdata(uname,rname,email,body,reminder_time,path,tasktitle);}
          else{
                dbr = new DB_reminder(SetAlarmUser.this);
                boolean result= dbr.insertdata(sender_name,phonenumber,msgdetail,reminder_time,tasktitle);
            }

            Toast.makeText(SetAlarmUser.this, "Alarm is set at" + targetCal.getTime(),
                    Toast.LENGTH_LONG).show();
            Intent intent = new Intent(getBaseContext(), AlarmReciver.class);
            Bundle b = new Bundle();
            b.putString("time", reminder_time);
            intent.putExtras(b);


            AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

            ArrayList<PendingIntent> intentArray = new ArrayList<PendingIntent>();

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    getBaseContext(), index, intent, 0);


            //  Toast.makeText(getApplicationContext(), Integer.toString(index), Toast.LENGTH_LONG).show();
            alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                    pendingIntent);
            intentArray.add(pendingIntent);
            index++;
        }


        setResult(RESULT_OK,in);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_set_alarm_user, menu);
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
