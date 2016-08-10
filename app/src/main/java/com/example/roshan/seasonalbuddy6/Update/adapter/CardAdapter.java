package com.example.roshan.seasonalbuddy6.Update.adapter;

/**
 * Created by ridmal on 2016-06-21.
 */

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

public class CardAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<Card> cardItems;
    private String durl;
    ImageLoader imageLoader = AppController.getInstance().getImageLoader();

    public CardAdapter(Context ctx, List<Card> cardItems) {
        this.context = ctx;
        this.cardItems = cardItems;
    }

    public CardAdapter(Context ctx,List<Card> cardItems,String URL){
        this.context = ctx;
        this.cardItems = cardItems;
        this.durl = URL;
    }

    @Override
    public int getCount() {
        return cardItems.size();
    }

    @Override
    public Object getItem(int location) {
        return cardItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (inflater == null)
            inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = inflater.inflate(R.layout.list_row, null);

        if (imageLoader == null)
            imageLoader = AppController.getInstance().getImageLoader();
        NetworkImageView thumbNail = (NetworkImageView) convertView
                .findViewById(R.id.thumbnail);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        TextView rating = (TextView) convertView.findViewById(R.id.rating);
        TextView genre = (TextView) convertView.findViewById(R.id.genre);
        TextView year = (TextView) convertView.findViewById(R.id.releaseYear);

        // getting movie data for the row
        Card c = cardItems.get(position);

       String url = durl+c.getCardurl();
       // String url = durl+c.getCardurl();
        // thumbnail image
        thumbNail.setImageUrl(url, imageLoader);

        // title
        title.setText("Name: "+c.getCardname());

        // rating
        rating.setText("Type: " + c.getCardtype());

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

        return convertView;
    }

}