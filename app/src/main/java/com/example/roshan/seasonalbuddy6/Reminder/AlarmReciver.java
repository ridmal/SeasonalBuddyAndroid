package com.example.roshan.seasonalbuddy6.Reminder;
/**
 * Created by Shaki on 3/30/2016.
 */
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.example.roshan.seasonalbuddy6.R;
import com.example.roshan.seasonalbuddy6.webconnectors.SendingMailDetails;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
public class AlarmReciver extends BroadcastReceiver {
    static int index1=1;
    static String text;
DB_reminder dbr;
    DB_reminder_email dbemail;
    String sname;
    String sphone;
    String smsg;
    String time;

    String uname;
    String rname;
    String body;
    String path;
    String email;
    String task;
    private static String TAG = ReminderActivity.class.getSimpleName();
        @Override
        public void onReceive(Context context, Intent intent) {
//            String x = null;
//            Bundle b = intent.getExtras();
//            x = b.getString("time");
            //Reducing the seconds
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            //getiing the time from the calender
          Calendar cal = Calendar.getInstance();
            time = cal.getTime().toString();
            time = sdf.format(cal.getTime());

           // Toast.makeText(context, time, Toast.LENGTH_LONG).show();
            dbr = new DB_reminder(context);
            Cursor rs = dbr.messagedet(time);

            if (rs.getCount()==0) {
                Toast.makeText(context, "No Details to send", Toast.LENGTH_LONG).show();
            }
                while (rs.moveToNext()) {
                    sname = rs.getString(1);
                    sphone = rs.getString(2);
                    smsg = rs.getString(3);
                    // Toast.makeText(context, "Your Time is up!!!!!       " + time, Toast.LENGTH_LONG).show();
                    Vibrator vib = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
                    vib.vibrate(2000);

                    try {

                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(sphone, null, sname + "!!!! " + smsg, null, null);
                        Toast.makeText(context, "SMS sent.", Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(context, e.toString(), Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                }
              dbemail = new DB_reminder_email(context);
                    Cursor res = dbemail.emaildet(time);

                    if(res.getCount()==0){
                        Toast.makeText(context, "No email details to send", Toast.LENGTH_LONG).show();
                    }
                    while(res.moveToNext()){
                        rname =res.getString(1);
                        uname=res.getString(2);
                        email=res.getString(3);
                        body=res.getString(4);
                        path=res.getString(5);
                        task = res.getString(7);

                        Vibrator vib = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
                        vib.vibrate(2000);

                        if(getMobileDataState(context)){

                            SendingMailDetails smd = new SendingMailDetails(context,rname,email);
                            smd.sendemaildetails(); // save receiver details on maindatabase
sendemailrid semail = new sendemailrid();
                            semail.execute(rname,uname,email,body,path);

                        }
                        else{
                            DB_reminder_email_pending dbep = new DB_reminder_email_pending(context);
                            dbep.insertdata(uname,rname,email,body,path,task);
                        }

                    }
                    //This is to send MMS Testing code
//                    Intent sendIntent = new Intent(Intent.ACTION_SEND);
//                    sendIntent.setClassName("com.android.mms", "com.android.mms.ui.ComposeMessageActivity");
//                    sendIntent.putExtra("sms_body", "some text");
//                    sendIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("file:///sdcard/DCIM/images.jpg"));
//                    sendIntent.setType("image/png");
//                    context.startActivity(sendIntent);

                    //This is to get notifications
                    Intent in = new Intent(context,ReminderActivity.class);
                    //Getting multiple notifications
                    ArrayList<PendingIntent> intentArray1 = new ArrayList<PendingIntent>();
//                    PendingIntent pintent = PendingIntent.getActivity(context,0, in,0);
                    PendingIntent pintent = PendingIntent.getActivity(context,index1, in,Intent.FLAG_ACTIVITY_NEW_TASK);
                    Notification noti = new Notification.Builder(context)
                            .setTicker("Message Sent via Seasonal Buddy").setContentTitle("Seasonal Buddy")
                            .setContentText("You have a Reminder from Seasonal Buddy")
                            .setSmallIcon(R.drawable.background)
                            .setContentIntent(pintent)
                            .getNotification();
                    noti.flags = Notification.DEFAULT_SOUND;
                    noti.flags = Notification.FLAG_AUTO_CANCEL;
                    NotificationManager nm = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
                    nm.notify(index1, noti);
                    intentArray1.add(pintent);
                    index1++;

        }
    public boolean getMobileDataState(Context ctx) {
        try {
            TelephonyManager telephonyService = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);

            Method getMobileDataEnabledMethod = telephonyService.getClass().getDeclaredMethod("getDataEnabled");

            if (null != getMobileDataEnabledMethod) {
                boolean mobileDataEnabled = (Boolean) getMobileDataEnabledMethod.invoke(telephonyService);

                return mobileDataEnabled;
            }
        } catch (Exception ex) {
            Log.e(TAG, "Error getting mobile data state", ex);
        }

        return false;
    }

    public class sendemailrid extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String rname = strings[0];
            String uname = strings[1];
            String email = strings[2];
            String body=strings[3];
            String path=strings[4];
            Mail m = new Mail("ridmadushanka@gmail.com", "ridmal1992");
            String[] toArr = {email};
            m.setTo(toArr);
            m.setFrom("ridmadushanka@gmail.com");
            m.setSubject(rname+", You have Seasonal Buddy Greeting from "+uname);
            m.setBody(body);

            try {
                m.addAttachment(path);
                if (m.send()) {
                    System.out.println("mail send");
                } else {
                    System.out.println("mail not send");
                }
            } catch (Exception e) {
                Log.e("MailApp", "Could not send email", e);
            }
            return null;
        }


    }
    }
