package com.example.restclient.network;

public interface VolleyResultReceiver {
    /** called in main-UI thread */
    void onGettingResponse(String response);

    /** called in main-UI thread */
    void onGettingError(String response);
}
