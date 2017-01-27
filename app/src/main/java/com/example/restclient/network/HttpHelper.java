package com.example.restclient.network;

import com.example.restclient.Utils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHelper {

    /*
    *   Needs background thread for work
    * */
    public String openUrl(String urlStr){
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(urlStr);
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String response = Utils.getStringFromInputStream(in);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (urlConnection == null) {
                urlConnection.disconnect();
            }
        }
    }
}
