package com.example.roshan.seasonalbuddy6.Reminder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roshan.seasonalbuddy6.R;

import java.util.ArrayList;
import java.util.List;

public class ReminderActivity extends AppCompatActivity {
    private static final String TAG = "ReminderActivity";
    //Adding a private instance of DB_reminder
    private DB_reminder mHelper;
    private DB_reminder_email dbreminderemail;
    //displaying data in the main view using an Adapter.
    //Getting a reference to the ListView created in activity_main.xml file by adding an instance of the ListView:
    private ListView mTaskListView;
    private ListView mTaskListView_Email;
    //This ArrayAdapter will help to populate the ListView with the data
    private ArrayAdapter<String> mAdapter;

    public ReminderAdapter reminderAdapter;
    public ReminderAdapter_email reminderAdapter_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        updateUI();
        updateUIEMAIL();
    }


    public void deleteTask(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.time_textview);
        String task = String.valueOf(taskTextView.getText());
        mHelper = new DB_reminder(this);
        mHelper.deletedata(task);
        updateUI();
    }
    public void deleteTask_email(View view) {
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.time_textview);
        String task = String.valueOf(taskTextView.getText());
        dbreminderemail = new DB_reminder_email(this);
        dbreminderemail.deletedata(task);
        updateUIEMAIL();
    }
    private void updateUI() {
        List<Reminder> reminders = new ArrayList<Reminder>();
        mTaskListView = (ListView) findViewById(R.id.list_todo);
        DB_reminder md = new DB_reminder(this);
        reminderAdapter = new ReminderAdapter(this,reminders);
        mTaskListView.setAdapter(reminderAdapter);
        Cursor c = md.tabledet();
        if(c.getCount()==0){

            Toast.makeText(this,"No dateils to show",Toast.LENGTH_LONG).show();
        }
        else {
            while (c.moveToNext()) {
                int id = c.getColumnIndex("NAME");
                String name = c.getString(id);
                int id2 = c.getColumnIndex("MESSAGE");
                String messge = c.getString(id2);
                int id3 = c.getColumnIndex("TIME");
                String time = c.getString(id3);
                int id4 = c.getColumnIndex("TASK");
                String task = c.getString(id4);

                Reminder rem = new Reminder();
                rem.setName(name);
                rem.setMsg(messge);
                rem.setTime(time);
                rem.setTitle(task);

                reminders.add(rem);



            }
        }
    }

    private void updateUIEMAIL() {
        List<Reminder> reminders = new ArrayList<Reminder>();
        mTaskListView_Email = (ListView) findViewById(R.id.list_todoemail);
        DB_reminder_email md = new DB_reminder_email(this);
        reminderAdapter_email = new ReminderAdapter_email(this,reminders);
        mTaskListView_Email.setAdapter(reminderAdapter_email);
        Cursor c = md.tabledet();
        if(c.getCount()==0){

            Toast.makeText(this,"No dateils to show",Toast.LENGTH_LONG).show();
        }
        else {
            while (c.moveToNext()) {
                int id = c.getColumnIndex("RNAME");
                String name = c.getString(id);
                int id2 = c.getColumnIndex("BODY");
                String body = c.getString(id2);
                int id3 = c.getColumnIndex("TIME");
                String time = c.getString(id3);
                int id4 = c.getColumnIndex("TASK");
                String task = c.getString(id4);

                Reminder rem = new Reminder();
                rem.setName(name);
                rem.setMsg(body);
                rem.setTime(time);
                rem.setTitle(task);

                reminders.add(rem);



            }
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode==RESULT_OK){
            updateUI();
            updateUIEMAIL();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                Log.d(TAG, "Add a new task");
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add a new task")
                        .setMessage("What do you want to do next?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
//                                String task = String.valueOf(taskEditText.getText());
//                                Log.d(TAG, "Task to add: " + task);
                                String task = String.valueOf(taskEditText.getText());

                                Intent setAlarmIntent = new Intent(ReminderActivity.this,SetAlarmUser.class);
                                setAlarmIntent.putExtra("task",task);
                                startActivityForResult(setAlarmIntent, 2);

                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
