package com.example.restclient;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class VolleyHelper {

    /*
    * Responses are always delivered on the main thread.
    * Request sending when called queue.add()
    *
    * Async work!!!
    * */
    void requestUsingVolley(Context context, final ILogger logger){
        RequestQueue queue = Volley.newRequestQueue(context);
        String url ="http://www.example.com";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) { logger.log("Response is: "+ response);}
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        logger.log("That didn't work!");
                    }
                });

        //for cancel all with tag
        Object o = "a";
        stringRequest.setTag(o);

        queue.add(stringRequest);
    }
}
