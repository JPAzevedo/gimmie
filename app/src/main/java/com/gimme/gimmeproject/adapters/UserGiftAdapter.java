package com.gimme.gimmeproject.adapters;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gimme.gimmeproject.R;
import com.gimme.gimmeproject.asynctasks.DownloadImage;
import com.gimme.gimmeproject.entities.Gift;
import com.gimme.gimmeproject.fragments.TestFragment;

import java.util.List;

public class UserGiftAdapter extends RecyclerView.Adapter<UserGiftAdapter.GiftViewHolder> {

    private List<Gift> gifts;

    public class GiftViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvDescription;
        ImageView ivGift;

        public GiftViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tvName);
            tvDescription = view.findViewById(R.id.tvDescription);
            ivGift = view.findViewById(R.id.ivGift);
        }
    }

    public UserGiftAdapter(List<Gift> gifts) {
        this.gifts = gifts;
    }

    public void setGifts(List<Gift> gifts){
        this.gifts = gifts;
    }

    @Override
    public GiftViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_gift, parent, false);
        return new GiftViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GiftViewHolder holder, int position) {
        holder.tvName.setText(gifts.get(position).getName());
        holder.tvDescription.setText(gifts.get(position).getDescription());

        AsyncTask downloadImage = new DownloadImage(holder.ivGift);
        downloadImage.execute(new String[]{gifts.get(position).getImageUrl()});

        Log.d("UserGiftAdapter","Name: "+gifts.get(position).getName());
        Log.d("UserGiftAdapter","Description: "+gifts.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        if (gifts == null) {
            return 0;
        }
        return gifts.size();
    }
}
