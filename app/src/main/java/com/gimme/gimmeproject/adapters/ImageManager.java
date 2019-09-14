package com.gimme.gimmeproject.adapters;

import android.content.Context;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import okhttp3.OkHttpClient;

public class ImageManager {

    private static Picasso picassoInstance;

    public static Picasso getPicassoInstance(Context context){
        if(picassoInstance == null){
            synchronized (ImageManager.class){
                if(picassoInstance == null){
                    picassoInstance = new Picasso.Builder(context).downloader(ImageManager.getDownloader()).build();
                }
            }
        }
        return picassoInstance;
    }

    public static OkHttp3Downloader getDownloader(){
        OkHttpClient okHttp3Client = new okhttp3.OkHttpClient();
        OkHttp3Downloader okHttp3Downloader = new OkHttp3Downloader(okHttp3Client);
        return okHttp3Downloader;
    }
}
