package com.example.dusan.topmovies.view;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.dusan.topmovies.R;

public class MovieViewHolder extends RecyclerView.ViewHolder{

    public TextView mTitle;
    public TextView mRelaseDate;
    public TextView mRatingScore;


    public MovieViewHolder(View itemView) {
        super(itemView);

        this.mTitle = (TextView) itemView.findViewById(R.id.title);
        this.mRelaseDate = (TextView) itemView.findViewById(R.id.relase_date);
        this.mRatingScore = (TextView) itemView.findViewById(R.id.rating_score);
    }
}
