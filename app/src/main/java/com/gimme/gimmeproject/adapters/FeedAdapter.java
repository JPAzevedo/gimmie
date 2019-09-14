package com.gimme.gimmeproject.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.gimme.gimmeproject.R;
import com.gimme.gimmeproject.entities.Gift;
import com.gimme.gimmeproject.util.CircleTransform;

import java.util.List;

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.FeedViewHolder> {

    private List<Gift> gifts;
    private Context context;

    public FeedAdapter(List<Gift> gifts, Context context){
        this.gifts = gifts;
        this.context = context;
    }

    public void updateAdapter(List<Gift> gifts){
        this.gifts = gifts;
        notifyDataSetChanged();
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_feed,parent,false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {

        ImageManager.getPicassoInstance(context).load(gifts.get(position).getUserPhotoUrl()).transform(new CircleTransform()).into(holder.ivUser);
        ImageManager.getPicassoInstance(context).load(gifts.get(position).getImageUrl()).into(holder.ivGift);
        holder.tvDescription.setText(gifts.get(position).getDescription());
        holder.tvUserName.setText(gifts.get(position).getUserName());
    }

    @Override
    public int getItemCount() {

        if(gifts == null) {
            return 0;
        }

        return gifts.size();
    }

    public class FeedViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUserName, tvDescription;
        private ImageView ivUser, ivGift;

        public FeedViewHolder(View itemView) {
            super(itemView);
            tvUserName = (TextView) itemView.findViewById(R.id.tvFeedUserName);
            tvDescription = (TextView) itemView.findViewById(R.id.tvFeedDescription);
            ivUser = (ImageView) itemView.findViewById(R.id.ivFeedUserPhoto);
            ivGift = (ImageView) itemView.findViewById(R.id.ivFeedGift);

        }
    }
}
