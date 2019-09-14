package com.gimme.gimmeproject.fragments;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gimme.gimmeproject.R;

import java.io.InputStream;

public class TestFragment extends Fragment {

    private ImageView ivNet;

    public TestFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_test,container,false);
        TextView textView = (TextView) fragmentView.findViewById(R.id.tTest);
        ImageView iv = (ImageView) fragmentView.findViewById(R.id.ivTest);
        ivNet = (ImageView) fragmentView.findViewById(R.id.ivTestUrl);

        iv.setImageResource(R.drawable.bananas);
        textView.setText(R.string.hello_blank_fragment);

        AsyncTask asyncTask = new DownloadImageFromInternet();
        String url = "https://images.newscientist.com/wp-content/uploads/2019/01/31134057/gettyimages-86304874.jpg";
        asyncTask.execute(new String[]{url});

        return fragmentView;
    }

    private class DownloadImageFromInternet extends AsyncTask<String, Void, Bitmap> {

    protected Bitmap doInBackground(String... urls) {
        String imageURL = urls[0];
        Bitmap bimage = null;
        try {
            InputStream in = new java.net.URL(imageURL).openStream();
            bimage = BitmapFactory.decodeStream(in);

        } catch (Exception e) {
            Log.e("Error Message", e.getMessage());
            e.printStackTrace();
        }
        return bimage;
    }

    protected void onPostExecute(Bitmap result) {
        ivNet.setImageBitmap(result);
    }
}

}
