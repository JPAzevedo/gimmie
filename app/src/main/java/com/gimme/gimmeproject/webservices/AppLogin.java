package com.gimme.gimmeproject.webservices;

import android.os.AsyncTask;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class AppLogin {

    private static AppLogin appLoginInstance;
    private OkHttpClient client;
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");


    public static AppLogin getInstance() {
        if(appLoginInstance == null) {
            synchronized (AppLogin.class) {
                if (appLoginInstance == null) {
                    appLoginInstance = new AppLogin();
                }
            }
        }
        return appLoginInstance;
    }

    private AppLogin(){
        client = new OkHttpClient();
    }

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

}
