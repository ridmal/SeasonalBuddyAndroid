package com.example.roshan.seasonalbuddy6.Reminder.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Vibrator;
import android.util.Log;
import android.widget.Toast;


import com.example.roshan.seasonalbuddy6.R;
import com.example.roshan.seasonalbuddy6.Reminder.DB_reminder_email_pending;
import com.example.roshan.seasonalbuddy6.Reminder.Mail;
import com.example.roshan.seasonalbuddy6.Reminder.Reminder;
import com.example.roshan.seasonalbuddy6.Reminder.ReminderActivity;
import com.example.roshan.seasonalbuddy6.webconnectors.SendingMailDetails;

import java.util.ArrayList;

/**
 * Created by ridmal on 2016-07-15.
 */
public class NetworkChangeReceiver extends BroadcastReceiver {
    static int index1=1;
    String rname;
    String uname;
    String email;
    String path;
    String body;
    @Override
    public void onReceive(Context context, Intent intent) {
        String status = NetworkUtil.getConnectivityStatusString(context);
        if(status=="1"||status=="2"){
            DB_reminder_email_pending dbep = new DB_reminder_email_pending(context);
            Cursor res = dbep.emailpendingdet();

            if(res.getCount()==0){
                Toast.makeText(context,"nothing to send",Toast.LENGTH_LONG).show();
            }
            while(res.moveToNext()){
                rname =res.getString(1);
                uname=res.getString(2);
                email=res.getString(3);
                body=res.getString(4);
                path=res.getString(5);

                Vibrator vib = (Vibrator) context.getSystemService(context.VIBRATOR_SERVICE);
                vib.vibrate(2000);

                sendemailrid semail = new sendemailrid();   // sending mail as a background task
                semail.execute(rname,uname,email,body,path);

//                SendingMailDetails  smd = new SendingMailDetails(context,rname,email);  // save receiver details on maindatabase
//                smd.sendemaildetails();


                Intent in = new Intent(context,ReminderActivity.class);
                //Getting multiple notifications
                ArrayList<PendingIntent> intentArray1 = new ArrayList<PendingIntent>();
//                    PendingIntent pintent = PendingIntent.getActivity(context,0, in,0);
                PendingIntent pintent = PendingIntent.getActivity(context,index1, in,Intent.FLAG_ACTIVITY_NEW_TASK);
                Notification noti = new Notification.Builder(context)
                        .setTicker("email Sent via Seasonal Buddy").setContentTitle("Seasonal Buddy")
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

            dbep.droptable();
        }

        //Toast.makeText(context, status, Toast.LENGTH_LONG).show();
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
                    // Toast.makeText(MainActivity.this, "Email was sent successfully.", Toast.LENGTH_LONG).show();
                } else {
                    System.out.println("mail not send");
                    // Toast.makeText(MainActivity.this, "Email was not sent.", Toast.LENGTH_LONG).show();
                }
            } catch (Exception e) {
                //Toast.makeText(MailApp.this, "There was a problem sending the email.", Toast.LENGTH_LONG).show();
                Log.e("MailApp", "Could not send email", e);
            }
            return null;
        }


    }
}
