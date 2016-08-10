package com.example.roshan.seasonalbuddy6.Reminder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by ridmal on 2016-04-25.
 */
public class DB_reminder extends SQLiteOpenHelper {

    public static final String db_name = "reminder_db";
    public static final String table_name = "reminder_table";

    public DB_reminder(Context context) {
//        super(context,  "/mnt/sdcard/ReminderApp/reminder_db", null, 1);
        //to do app
        //super(context, TaskContract.DB_NAME, null, TaskContract.DB_VERSION);
        super(context,  "/mnt/sdcard/ReminderApp/shaki_reminder_db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + table_name + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,P_NUMBER TEXT,MESSAGE TEXT,TIME TEXT,TASK TEXT)");
        //to do app
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ table_name);
        //To do app
        onCreate(db);
    }
    public boolean insertdata(String name,String pnumber,String message,String time,String task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contents = new ContentValues();
        contents.put("NAME",name);
        contents.put("P_NUMBER", pnumber);
        contents.put("MESSAGE", message);
        contents.put("TIME", time);
        contents.put("TASK",task);
        long result = db.insert(table_name, null, contents);
        if(result==-1)
            return  false;
        else
            return true;
    }

    public Cursor messagedet(String time){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+table_name+" Where TIME = '"+time+"'" ,null);
        return res;
    }

    public Cursor tabledet(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+table_name,null);
        return res;
    }
    public int deletedata(String time){
        SQLiteDatabase db = this.getWritableDatabase();
      return  db.delete(table_name,"TIME = ?",new String[]{time});

    }


}
