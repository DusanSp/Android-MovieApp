package com.example.dusan.topmovies.view.holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dusan.topmovies.R;


public class TvShowViewHolder extends RecyclerView.ViewHolder {

    public TextView mName;
    public TextView mRelaseDate;
    public TextView mRatingScore;
    public ImageView mPoster;


    public TvShowViewHolder(View itemView) {
        super(itemView);

        this.mName = (TextView) itemView.findViewById(R.id.title);
        this.mRelaseDate = (TextView) itemView.findViewById(R.id.relase_date);
        this.mRatingScore = (TextView) itemView.findViewById(R.id.rating_score);
        this.mPoster = (ImageView) itemView.findViewById(R.id.movie_poster);
    }
}
