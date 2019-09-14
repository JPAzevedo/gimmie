package com.gimme.gimmeproject.fragments;

import android.os.AsyncTask;

import com.gimme.gimmeproject.entities.Gift;
import com.gimme.gimmeproject.webservices.PersonalGift;

import java.util.List;

public class GiftDownloader<T extends GiftDownloaderInterface> {

    private T t;

    public GiftDownloader(T t){
        this.t = t;
    }

    public void getGiftInformation(){
        GiftsWorker userGifts = new GiftsWorker();
        userGifts.execute(new String[]{null});
    }

    class GiftsWorker extends AsyncTask<String,Void,List<?>> {
        @Override
        protected List<?> doInBackground(String... ids) {
            return getGifts(ids[0]);
        }

        @Override
        protected void onPostExecute(List<?> result) {
            super.onPostExecute(result);
            List<Gift> gifts = (List<Gift>) result;
            t.onDownloadSuccess(gifts);
        }
    }

    private List<Gift> getGifts(String userId){
        PersonalGift instance = PersonalGift.getInstance();
        return instance.getGifts(userId);
    }

}
