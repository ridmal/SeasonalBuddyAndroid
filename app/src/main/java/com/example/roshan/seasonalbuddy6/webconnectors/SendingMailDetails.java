package com.example.roshan.seasonalbuddy6.webconnectors;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.StringRequest;
import com.example.roshan.seasonalbuddy6.Update.app.AppController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ridmal on 2016-08-05.
 */
public class SendingMailDetails {
    private String urlString = "http://192.168.43.80:8086/savereciver";
    private static final String TAG = "CardActivity";
    private ProgressDialog pDialog;
    Context ctx;
    String rname;
    String rmail;

    public SendingMailDetails(Context ctx,String rname,String rmail){
        this.rname = rname;
        this.rmail = rmail;
        this.ctx = ctx;

    }

    public void sendemaildetails() {

        StringRequest strReq = new StringRequest(Request.Method.POST,
                urlString, new Response.Listener<String>() {

            // for sending the string with url we can add (+) the string to url and send....

            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
//                pDialog.hide();
                Toast.makeText(ctx,response.toString(),Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());

            }
        })
        {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                params.put("email",rmail);
                params.put("name", rname);

                return params;
            }

        }
                ;
        AppController.getInstance().addToRequestQueue(strReq);

    }

}
