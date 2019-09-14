package com.gimme.gimmeproject.webservices;

import android.util.Log;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HTTPRequest {

    public HTTPRequest(){
        client = new OkHttpClient();
    }

    private OkHttpClient client;
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    protected String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Log.d("BodyRequest","Body: "+json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    protected String get(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }
}
