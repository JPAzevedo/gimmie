package com.gimme.gimmeproject.fragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.gimme.gimmeproject.R;
import com.gimme.gimmeproject.adapters.FeedAdapter;
import com.gimme.gimmeproject.entities.Gift;

import java.io.File;
import java.util.Date;
import java.util.List;

public class FeedFragment extends Fragment implements GiftDownloaderInterface, View.OnClickListener {

    private FeedAdapter feedAdapter;
    private FloatingActionButton fabPhoto;
    private RecyclerView rvFeed;
    private List<Gift> gifts;

    //Global Variables
    private static final int CAMERA_IMAGE_REQUEST = 101;
    private String imageName;

    private static final int REQUEST_CAMERA_ACCESS = 1010;

    public FeedFragment(){

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.fragment_feed,container,false);
        rvFeed = (RecyclerView) fragmentView.findViewById(R.id.rvFeed);
        fabPhoto = (FloatingActionButton) fragmentView.findViewById(R.id.fabPhoto);

        feedAdapter = new FeedAdapter(gifts,getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvFeed.setLayoutManager(mLayoutManager);
        rvFeed.setItemAnimator(new DefaultItemAnimator());
        rvFeed.setAdapter(feedAdapter);

        fabPhoto.setOnClickListener(this);

        GiftDownloader giftDownloader = new GiftDownloader(this);
        giftDownloader.getGiftInformation();

        return fragmentView;
    }

    @Override
    public void onDownloadSuccess(List results) {
        feedAdapter.updateAdapter(results);
        feedAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDownloadError() {

    }

    @Override
    public void onClick(View view) {

        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(getActivity(),
                    new String[]{Manifest.permission.CAMERA},
                    REQUEST_CAMERA_ACCESS);

        }
        else{
            captureImage();
        }

    }

    /*
        From this point on, the class manages the camera access
     */

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CAMERA_ACCESS: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    Snackbar.make(getView(), "Here's a Snackbar", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();

                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

    public void captureImage() {

        // Creating folders for Image
        String imageFolderPath = Environment.getExternalStorageDirectory().toString()
                + "/Gimme";
        File imagesFolder = new File(imageFolderPath);
        imagesFolder.mkdirs();

        // Generating file name
        imageName = new Date().toString() + ".png";

        // Creating image here
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(imageFolderPath, imageName)));
        startActivityForResult(takePictureIntent,
                CAMERA_IMAGE_REQUEST);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            // TODO Auto-generated method stub
            super.onActivityResult(requestCode, resultCode, data);

            if (resultCode == Activity.RESULT_OK && requestCode == CAMERA_IMAGE_REQUEST) {

                Toast.makeText(getActivity(), "Success",
                        Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(getActivity(), "Take Picture Failed or canceled",
                        Toast.LENGTH_SHORT).show();
            }
    }
}
