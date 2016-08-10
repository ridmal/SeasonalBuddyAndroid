package com.example.roshan.seasonalbuddy6.Update;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.example.roshan.seasonalbuddy6.R;
import com.example.roshan.seasonalbuddy6.Update.adapter.CardAdapter;
import com.example.roshan.seasonalbuddy6.Update.adapter.clipadapter;
import com.example.roshan.seasonalbuddy6.Update.app.AppController;
import com.example.roshan.seasonalbuddy6.Update.model.Card;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class UpdateActivity extends AppCompatActivity {
    private static final String TAG = UpdateActivity.class.getSimpleName();
    static String urlcard = null;  // this is url for cards,clips and templates that get as a jsong list for each item
    static String downloadlink = null;
    static String savelink = null;
    static String event = null;
    ListView listView;
    // private List<Movie> movieList = new ArrayList<Movie>();
    // private List<Card> cardList = new ArrayList<Card>();
    // private ListView listView;
    ImageView im2;
    Bitmap bnp = null;
    Button cardbtn1;
    Button clipbtn1, template;
    private ProgressDialog pDialog;
    //private CustomListAdapter adapter;
    private CardAdapter cadapter;
    private clipadapter clipadapter;
    private NetworkImageView nm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        listView = (ListView) findViewById(R.id.list);

        cardbtn1 = (Button) findViewById(R.id.updateCard);
        clipbtn1 = (Button) findViewById(R.id.updateClips);
        template = (Button) findViewById(R.id.updateTemplate);

        cardbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                urlcard = "http://192.168.43.80:8086/getallcards/";
                downloadlink = "http://192.168.43.80:8086/carddownload/";
                savelink = "/Seasonal Buddy/SesonalCards/";
GetUpdates gu = new GetUpdates(downloadlink,urlcard,UpdateActivity.this,event);
                listView.setAdapter(gu.getcarduploads()); // creating adapter with cards and set to the list view
                downloadimage(listView,gu.getlist()); // download the cards
               // getcarduploads(downloadlink);
            }
        });
        clipbtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                urlcard = "http://192.168.43.80:8086/getallclips/";
                downloadlink = "http://192.168.43.80:8086/clipdownload/";
                savelink = "/Seasonal Buddy/Clips/";
                GetUpdates gu = new GetUpdates(downloadlink,urlcard,UpdateActivity.this,event);
                listView.setAdapter(gu.getcarduploads()); // creating adapter with clips and set to the list
                downloadimage(listView,gu.getlist()); // download the clips
                //getclipuploads(downloadlink);
            }
        });
        template.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                urlcard = "http://192.168.43.80:8086/getallTemps/";
                downloadlink = "http://192.168.43.80:8086/tempdownload/";
                savelink = "/Seasonal Buddy/Templates/";
                GetUpdates gu = new GetUpdates(downloadlink,urlcard,UpdateActivity.this,event);
                listView.setAdapter(gu.getcarduploads()); // creating adapter with clips and set to the list
                downloadimage(listView,gu.getlist()); // download the clips
            }
        });


        // Spinner element to set color
        final Spinner spinner = (Spinner) findViewById(R.id.spinner3);
        // Spinner click listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                // Showing selected spinner item
//                Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                event = item;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Sinhala_Hindu_NewYear");
        categories.add("Christmas");
        categories.add("Vesak_Festival");
        categories.add("New_Year");
        categories.add("Birthday");
        categories.add("Valentine");
        categories.add("Thaipongal");
        categories.add("Ramazan");
        categories.add("Default");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories) {
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position % 2 == 1) {
                    // Set the item background color
                    tv.setBackgroundColor(Color.parseColor("#1E88E5"));
                } else {
                    // Set the alternate item background color
                    tv.setBackgroundColor(Color.parseColor("#1976D2"));
                }
                return view;
            }
        };
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }
//    public void getcarduploads(String url) {
//        final List<Card> cardList = new ArrayList<Card>();
//        ListView listView;
//        listView = (ListView) findViewById(R.id.list);
//        //adapter = new CustomListAdapter(this, movieList);
//        // cadapter = new CardAdapter(this, cardList);
//        cadapter = new CardAdapter(UpdateActivity.this, cardList, url);
//        listView.setAdapter(cadapter);
//        downloadimage(listView, cardList);
//        pDialog = new ProgressDialog(this);
//        // Showing progress dialog before making http request
//        pDialog.setMessage("Loading...");
//        pDialog.show();
//
//        // changing action bar color
//
//
//        // Creating volley request obj
//        JsonArrayRequest movieReq = new JsonArrayRequest(urlcard + event,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        Log.d(TAG, response.toString());
//                        hidePDialog();
//
//                        // Parsing json
//                        for (int i = 0; i < response.length(); i++) {
//                            try {
//
//                                JSONObject obj = null;
//                                try {
//                                    obj = response.getJSONObject(i);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
////                                Movie movie = new Movie();
////                                movie.setTitle(obj.getString("title"));
////                                movie.setThumbnailUrl(obj.getString("image"));
////                                movie.setRating(((Number) obj.get("rating"))
////                                        .doubleValue());
////                                movie.setYear(obj.getInt("releaseYear"));
//                                Card c = new Card();
//                                c.setCardname(obj.getString("name"));
//                                c.setCardurl(obj.getString("path"));
//                                c.setCardtype(obj.getString("type"));
//                                c.setDate(obj.getString("date"));
//
//                                // Genre is json array
////                                JSONArray genreArry = obj.getJSONArray("genre");
////                                ArrayList<String> genre = new ArrayList<String>();
////                                for (int j = 0; j < genreArry.length(); j++) {
////                                    genre.add((String) genreArry.get(j));
////                                }
//                                //  movie.setGenre(genre);
//
//
//                                // adding movie to movies array
//                                //  movieList.add(movie);
//                                cardList.add(c);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//
//                        // notifying list adapter about data changes
//                        // so that it renders the list view with updated data
//                        // adapter.notifyDataSetChanged();
//                        cadapter.notifyDataSetChanged();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//                hidePDialog();
//
//            }
//        });
//
//        // Adding request to request queue
//        AppController.getInstance().addToRequestQueue(movieReq);
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        hidePDialog();
//    }
//
//    private void hidePDialog() {
//        if (pDialog != null) {
//            pDialog.dismiss();
//            pDialog = null;
//        }
//    }
    //    public void getclipuploads(String url) {
//        final List<Card> clipList = new ArrayList<Card>();
//        ListView listView;
//        listView = (ListView) findViewById(R.id.list);
//        //adapter = new CustomListAdapter(this, movieList);
//        // cadapter = new CardAdapter(this, cardList);
//        clipadapter = new clipadapter(UpdateActivity.this, clipList, url);
//        listView.setAdapter(clipadapter);
//        downloadimage(listView, clipList);
//        pDialog = new ProgressDialog(this);
//        // Showing progress dialog before making http request
//        pDialog.setMessage("Loading...");
//        pDialog.show();
//
//        // changing action bar color
//
//
//        // Creating volley request obj
//        JsonArrayRequest movieReq = new JsonArrayRequest(urlcard + event,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        Log.d(TAG, response.toString());
//                        hidePDialog();
//
//                        // Parsing json
//                        for (int i = 0; i < response.length(); i++) {
//                            try {
//
//                                JSONObject obj = null;
//                                try {
//                                    obj = response.getJSONObject(i);
//                                } catch (JSONException e) {
//                                    e.printStackTrace();
//                                }
////                                Movie movie = new Movie();
////                                movie.setTitle(obj.getString("title"));
////                                movie.setThumbnailUrl(obj.getString("image"));
////                                movie.setRating(((Number) obj.get("rating"))
////                                        .doubleValue());
////                                movie.setYear(obj.getInt("releaseYear"));
//                                Card c = new Card();
//                                c.setCardname(obj.getString("name"));
//                                c.setCardurl(obj.getString("path"));
//                                c.setCardtype(obj.getString("type"));
//                                c.setDate(obj.getString("date"));
//
//                                // Genre is json array
////                                JSONArray genreArry = obj.getJSONArray("genre");
////                                ArrayList<String> genre = new ArrayList<String>();
////                                for (int j = 0; j < genreArry.length(); j++) {
////                                    genre.add((String) genreArry.get(j));
////                                }
//                                //  movie.setGenre(genre);
//
//
//                                // adding movie to movies array
//                                //  movieList.add(movie);
//                                clipList.add(c);
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//
//                        }
//
//                        // notifying list adapter about data changes
//                        // so that it renders the list view with updated data
//                        // adapter.notifyDataSetChanged();
//                        clipadapter.notifyDataSetChanged();
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d(TAG, "Error: " + error.getMessage());
//                hidePDialog();
//
//            }
//        });
//
//        // Adding request to request queue
//        AppController.getInstance().addToRequestQueue(movieReq);
//    }

    public void downloadimage(ListView listView, final List<Card> cardList) {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Card c = cardList.get(i);
                final String a = c.getCardurl();
                String cardname = c.getCardname() + ".jpg";
                Toast.makeText(getApplicationContext(), a, Toast.LENGTH_LONG).show();
//
//      String url = "http://192.168.43.80:8086/carddownload/"+a;
//        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
//        nm.setImageUrl(url, imageLoader);
                // MainActivity.this.addContentView(nm, new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
                // nm.buildDrawingCache();
                // Bitmap bmp = nm.getDrawingCache();


//        Bitmap bmp = im2.getDrawingCache();
                showdialogbox(cardname, a);
            }
        });
    }

    public void showdialogbox(final String cardname, String url) {
        final Dialog download = new Dialog(UpdateActivity.this);
        download.setTitle("Download this File");
        download.setContentView(R.layout.imageviewdownload);
        download.setCancelable(false);
        String Url = downloadlink + url;
        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        final ImageView im = (ImageView) download.findViewById(R.id.imageView);
        imageLoader.get(Url, new ImageLoader.ImageListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //  Log.e(TAG, "Image Load Error: " + error.getMessage());
            }

            @Override
            public void onResponse(ImageLoader.ImageContainer response, boolean arg1) {
                if (response.getBitmap() != null) {
                    // load image into imageview

                    im.setImageBitmap(response.getBitmap());

                }
            }
        });
        download.show();

        Button dbtn = (Button) download.findViewById(R.id.btndownload);
        dbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bitmap bitmap = ((BitmapDrawable) im.getDrawable()).getBitmap();
                File filepath = Environment.getExternalStorageDirectory();
                //filepath.list();
                File dir = new File(filepath + savelink + event + "/");
                dir.mkdirs(); //make new directory if their not exist

                File file = new File(dir, cardname); //make image name like 'image'
                try {
                    sendBroadcast(new Intent(
                            Intent.ACTION_MEDIA_MOUNTED,
                            Uri.parse("file://" + filepath)));//save image in this path
                } catch (Exception e) {
                    e.getMessage();
                }
                Toast.makeText(UpdateActivity.this, "Image saved to Sd Card!", Toast.LENGTH_SHORT).show();

                try {
                    FileOutputStream outputStream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);//convert image to PNG
                    outputStream.flush();
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        Button cbtn = (Button) download.findViewById(R.id.btncancle);
        cbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                download.cancel();
            }
        });

    }
}
