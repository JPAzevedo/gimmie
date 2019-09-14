package com.gimme.gimmeproject.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.gimme.gimmeproject.R;
import com.gimme.gimmeproject.adapters.UserGiftAdapter;
import com.gimme.gimmeproject.entities.Gift;
import com.gimme.gimmeproject.webservices.PersonalGift;

import java.util.List;

public class UserFragment extends Fragment {


    private RecyclerView rvGift;

    private UserGiftAdapter userGiftAdapter;

    private List<Gift> gifts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View userView = inflater.inflate(R.layout.fragment_user,container,false);
        rvGift = userView.findViewById(R.id.rvGift);

        userGiftAdapter = new UserGiftAdapter(gifts);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        rvGift.setLayoutManager(mLayoutManager);
        rvGift.setItemAnimator(new DefaultItemAnimator());
        rvGift.setAdapter(userGiftAdapter);

        UserGifts userGifts = new UserGifts();
        userGifts.execute(new String[]{null});
        return userView;
    }

    class UserGifts extends AsyncTask<String,Void,List<?>>{
        @Override
        protected List<?> doInBackground(String... ids) {
            return getGifts(ids[0]);
        }

        @Override
        protected void onPostExecute(List<?> result) {
            super.onPostExecute(result);
            List<Gift> gifts = (List<Gift>) result;
            //ObjectMapper mapper = new ObjectMapper();
            //String res = mapper.writeValueAsString(gifts);
            userGiftAdapter.setGifts(gifts);
            userGiftAdapter.notifyDataSetChanged();
        }
    }

    private List<Gift> getGifts(String userId){
        PersonalGift instance = PersonalGift.getInstance();
        return instance.getGifts(userId);
    }
}
