package com.example.roshan.seasonalbuddy6.Reminder;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.roshan.seasonalbuddy6.R;

import java.util.List;

/**
 * Created by ridmal on 2016-07-26.
 */
public class ReminderAdapter_email extends BaseAdapter {
    private Activity activity;
    private LayoutInflater inflater;
    private List<Reminder> reminders;

    public ReminderAdapter_email(Activity activity, List<Reminder> reminders){
        this.activity = activity;
        this.reminders = reminders;
    }

    @Override
    public int getCount() {
        return reminders.size();
    }

    @Override
    public Object getItem(int position) {
        return reminders.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(inflater==null)
            inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView==null)
            convertView = inflater.inflate(R.layout.item_todo_email,null);

        TextView tasktitle = (TextView)convertView.findViewById(R.id.task_title);
        TextView nametitle = (TextView)convertView.findViewById(R.id.name_title);
        TextView msgtitle = (TextView)convertView.findViewById(R.id.msg_title);
        TextView time = (TextView)convertView.findViewById(R.id.time_textview);

        Reminder r = reminders.get(position);

        tasktitle.setText(r.getTitle());
        nametitle.setText(r.getName());
        msgtitle.setText(r.getMsg());
        time.setText(r.getTime());

        return convertView;
    }
}
