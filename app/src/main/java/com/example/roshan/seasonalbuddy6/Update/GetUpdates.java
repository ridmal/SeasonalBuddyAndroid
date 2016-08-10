package com.example.roshan.seasonalbuddy6.Update;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.roshan.seasonalbuddy6.R;
import com.example.roshan.seasonalbuddy6.Update.adapter.CardAdapter;
import com.example.roshan.seasonalbuddy6.Update.app.AppController;
import com.example.roshan.seasonalbuddy6.Update.model.Card;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ridmal on 2016-08-06.
 */
public class GetUpdates {
    private static final String TAG = "UPDATECARD";
    String durl;
    String listurl;
    Context ctx;
    String event;
    ProgressDialog pDialog;
    CardAdapter cadapter;
    List<Card> cardList = new ArrayList<Card>();
public GetUpdates(String durl, String urllist, Context ctx,String event){
    this.ctx = ctx;
    this.durl = durl;
    this.listurl = urllist;
    this.event = event;
}
    public CardAdapter getcarduploads() {
    //    final List<Card> cardList = new ArrayList<Card>();
        ListView listView;
        //adapter = new CustomListAdapter(this, movieList);
        // cadapter = new CardAdapter(this, cardList);
        cadapter = new CardAdapter(ctx, cardList,durl);
        pDialog = new ProgressDialog(ctx);
        // Showing progress dialog before making http request
        pDialog.setMessage("Loading...");
        pDialog.show();

        // changing action bar color


        // Creating volley request obj
        JsonArrayRequest movieReq = new JsonArrayRequest(listurl + event,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.d(TAG, response.toString());
                        hidePDialog();

                        // Parsing json
                        for (int i = 0; i < response.length(); i++) {
                            try {

                                JSONObject obj = null;
                                try {
                                    obj = response.getJSONObject(i);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
//                                Movie movie = new Movie();
//                                movie.setTitle(obj.getString("title"));
//                                movie.setThumbnailUrl(obj.getString("image"));
//                                movie.setRating(((Number) obj.get("rating"))
//                                        .doubleValue());
//                                movie.setYear(obj.getInt("releaseYear"));
                                Card c = new Card();
                                c.setCardname(obj.getString("name"));
                                c.setCardurl(obj.getString("path"));
                                c.setCardtype(obj.getString("type"));
                                c.setDate(obj.getString("date"));

                                // Genre is json array
//                                JSONArray genreArry = obj.getJSONArray("genre");
//                                ArrayList<String> genre = new ArrayList<String>();
//                                for (int j = 0; j < genreArry.length(); j++) {
//                                    genre.add((String) genreArry.get(j));
//                                }
                                //  movie.setGenre(genre);


                                // adding movie to movies array
                                //  movieList.add(movie);
                                cardList.add(c);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }

                        // notifying list adapter about data changes
                        // so that it renders the list view with updated data
                        // adapter.notifyDataSetChanged();
                        cadapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                hidePDialog();

            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(movieReq);
        return cadapter;
    }

    private void hidePDialog() {
        if (pDialog != null) {
            pDialog.dismiss();
            pDialog = null;
        }
    }
    public List<Card> getlist(){
        return cardList;
    }

}


