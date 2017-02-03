package com.example.restclient.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

/*
* Responses are always delivered on the main thread.
* Request sending when called queue.add()
*
* Async work!!!
* */
public class VolleyHelper {
    private final RequestQueue queue;
    private final Object tag = "tag";

    public VolleyHelper(Context context) {
        queue = Volley.newRequestQueue(context);
    }

    public void requestUsingVolley(String urlString, final VolleyResultReceiver receiver){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                urlString,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) { receiver.onGettingResponse(response);}
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        receiver.onGettingError(error.toString());
                    }
                });

        stringRequest.setTag(tag); //for canceling all with this tag
        queue.add(stringRequest);
    }

    public void cancelAll() {
        queue.cancelAll(tag);
    }
}
