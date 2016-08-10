package com.example.roshan.seasonalbuddy6.text.tool;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.roshan.seasonalbuddy6.R;

import java.util.ArrayList;
import java.util.List;

public class InsertText extends AppCompatActivity {


    //declaring global variable to get the font style
    static String itemStyle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_text);

        //To change the size of this intent
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        getWindow().setLayout((int) (width * .9), (int) (height * .65));

        //adding a border to edit text view
        // Create a border programmatically
        ShapeDrawable shape = new ShapeDrawable(new RectShape());
        shape.getPaint().setColor(Color.RED);
        shape.getPaint().setStyle(Paint.Style.STROKE);
        shape.getPaint().setStrokeWidth(3);

        //Increasing the font size of the editText using seek bar
        final SeekBar sk = (SeekBar) findViewById(R.id.seekBar);
        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int p = 5;
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                p = i;
                //             e2.setTextSize(p);
                //            Toast.makeText(adapterView.getContext(), "Selected: " + itemStyle, Toast.LENGTH_SHORT).show();
                //Toast.makeText(sk.getContext(), "Font Size is " + p +"%", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Toast.makeText(sk.getContext(), "Font Size is " + sk.getProgress() + "%", Toast.LENGTH_SHORT).show();
                if (p < 10) {
                    p = 10;
                    sk.setProgress(p);
                }
            }
        });



        // Spinner element to set color
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        // Spinner click listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                EditText e3 = (EditText)findViewById(R.id.editText);
                // Showing selected spinner item
//                Toast.makeText(adapterView.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
                switch (item){
                    case "Magenta":{
                        e3.setTextColor(Color.MAGENTA);
                        spinner.setBackgroundColor(Color.MAGENTA);
                    };break;
                    case "Blue":{
                        e3.setTextColor(Color.BLUE);
                        spinner.setBackgroundColor(Color.BLUE);
                    }break;
                    case "Yellow":{
                        e3.setTextColor(Color.YELLOW);
                        spinner.setBackgroundColor(Color.YELLOW);
                    }break;
                    case "Green":{
                        e3.setTextColor(Color.GREEN);
                        spinner.setBackgroundColor(Color.GREEN);
                    }break;
                    case "White":{
                        e3.setTextColor(Color.WHITE);
                        spinner.setBackgroundColor(Color.WHITE);
                    }break;
                    case "Red":{
                        e3.setTextColor(Color.RED);
                        spinner.setBackgroundColor(Color.RED);
                    }break;
                    default:e3.setTextColor(Color.BLACK);
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("Magenta");
        categories.add("Blue");
        categories.add("Yellow");
        categories.add("Green");
        categories.add("White");
        categories.add("Red");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories){
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0) {
                    // Set the item background color
                    tv.setBackgroundColor(Color.MAGENTA);
                }
                else if (position==1)
                    tv.setBackgroundColor(Color.BLUE);
                else if (position==2)
                    tv.setBackgroundColor(Color.YELLOW);
                else if (position==3)
                    tv.setBackgroundColor(Color.GREEN);
                else if (position==4)
                    tv.setBackgroundColor(Color.WHITE);
                else
                    tv.setBackgroundColor(Color.RED);
                return view;
            }
        };
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);


        // Spinner element to set font style
        final Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        // Spinner click listener
       spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               itemStyle = adapterView.getItemAtPosition(i).toString();
               EditText e4 = (EditText)findViewById(R.id.editText);
               // Showing selected spinner item
//               Toast.makeText(adapterView.getContext(), "Selected: " + itemStyle, Toast.LENGTH_SHORT).show();
               switch (itemStyle){
                   case "BirchStd": {
                       Typeface tf1 = Typeface.createFromAsset(getAssets(), "BirchStd.otf");
                       e4.setTypeface(tf1);
                   };break;
                   case "times": {
                       Typeface tf2 = Typeface.createFromAsset(getAssets(), "times.ttf");
                       e4.setTypeface(tf2);
                   };break;
                   case "davidbd": {
                       Typeface tf3 = Typeface.createFromAsset(getAssets(), "davidbd.ttf");
                       e4.setTypeface(tf3);
                   };break;
                   case "GOTHIC": {
                       Typeface tf4 = Typeface.createFromAsset(getAssets(), "GOTHIC.TTF");
                       e4.setTypeface(tf4);
                   };break;
                   case "Mopey": {
                       Typeface tf5 = Typeface.createFromAsset(getAssets(), "Mopey.ttf");
                       e4.setTypeface(tf5);
                   };break;
                   case "Amal": {
                       Typeface tf6 = Typeface.createFromAsset(getAssets(), "Amal.TTF");
                       e4.setTypeface(tf6);
                   };break;
                   default:{
                       Typeface tf5 = Typeface.createFromAsset(getAssets(), "Mopey.ttf");
                       e4.setTypeface(tf5);
                   };
               }
           }
           @Override
           public void onNothingSelected(AdapterView<?> adapterView) {
           }
       });
        // Spinner Drop down elements
        List<String> categories2 = new ArrayList<String>();
        categories2.add("BirchStd");
        categories2.add("times");
        categories2.add("davidbd");
        categories2.add("GOTHIC");
        categories2.add("Mopey");
        categories2.add("Amal");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories2){
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position%2 == 1) {
                    // Set the item background color
                    tv.setBackgroundColor(Color.parseColor("#1E88E5"));
                }
                else {
                    // Set the alternate item background color
                    tv.setBackgroundColor(Color.parseColor("#1976D2"));
                }
                return view;
            }
        };
        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        // attaching data adapter to spinner
        spinner2.setAdapter(dataAdapter2);


        //Going to the create card intent and
        ImageButton ok_btn = (ImageButton) findViewById(R.id.buttonOk);
        ok_btn.setOnClickListener(new View.OnClickListener()
                                  {
                                      @Override
                                      public void onClick(View view) {
                                        //Sending the inputted text to the previous intent
                                          //saving the values to variables
                                          final EditText e1 = (EditText) findViewById(R.id.editText);
                                          String userMessage = e1.getText().toString();
                                          SeekBar sk = (SeekBar) findViewById(R.id.seekBar);
                                          //Saving the progress of the seek bar to userSize variable
                                          int userSize = sk.getProgress();
                                          //Getting the text color
                                          int userColor = e1.getCurrentTextColor();
                                          //Getting the font style name
                                          String myStyle = itemStyle;

                                          //sending the data/variables to the createCard intent
                                          Intent in = new Intent();
                                          Bundle b = new Bundle();
                                          b.putString("msg",userMessage);
                                          b.putString("type",myStyle);
                                          b.putInt("size",userSize);
                                          b.putInt("color",userColor);

                                          in.putExtras(b);
                                          setResult(RESULT_OK,in);
                                          finish();
                                      }
                                  }

        );

        //Going to create card intent by cancel
        ImageButton cancel_btn = (ImageButton) findViewById(R.id.buttonCancle);
        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }//End of onCreate method




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
      //  getMenuInflater().inflate(R.menu.menu_insert_text, menu);
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
