package com.example.roshan.seasonalbuddy6.Update.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.example.roshan.seasonalbuddy6.R;
import com.example.roshan.seasonalbuddy6.Update.app.AppController;
import com.example.roshan.seasonalbuddy6.Update.model.Card;

import java.util.List;

/**
 * Created by ridmal on 2016-06-24.
 */
public class clipadapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Card> clipItems;
    private String dlink;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

public clipadapter(Activity activity,List<Card> clips){
    this.activity=activity;
    this.clipItems =clips;
}
    public clipadapter(Activity activity,List<Card> clips,String url){
        this.activity=activity;
        this.clipItems =clips;
        this.dlink = url;
    }

    @Override
    public int getCount() {
        return clipItems.size();
    }

    @Override
    public Object getItem(int i) {
        return clipItems.get(i);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (inflater == null)
            inflater = (LayoutInflater) activity
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null)
            view = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) view
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView rating = (TextView) view.findViewById(R.id.rating);
        TextView genre = (TextView) view.findViewById(R.id.genre);
        TextView year = (TextView) view.findViewById(R.id.releaseYear);

        // getting movie data for the row
        Card c = clipItems.get(i);

        String url = dlink+c.getCardurl();
        // thumbnail image
        thumbNail.setImageUrl(url, imageLoader);

        // title
        title.setText("Name: "+c.getCardname());

        // rating
        rating.setText("Type" + c.getCardtype());

        // genre
//        String genreStr = "";
//        for (String str : m.getGenre()) {
//            genreStr += str + ", ";
//        }
//        genreStr = genreStr.length() > 0 ? genreStr.substring(0,
//                genreStr.length() - 2) : genreStr;
        genre.setText(c.getCardurl());

        // release year
        year.setText(c.getDate());

        return view;
    }

    }

