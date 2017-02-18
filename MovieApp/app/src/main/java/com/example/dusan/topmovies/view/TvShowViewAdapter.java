package com.example.dusan.topmovies.view;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dusan.topmovies.R;
import com.example.dusan.topmovies.model.TvShow;
import com.squareup.picasso.Picasso;

import java.util.List;


public class TvShowViewAdapter extends RecyclerView.Adapter<TvShowViewHolder> {


    private List<TvShow> mTvShowList;
    private ListFragment mListFragment;

    public TvShowViewAdapter(List<TvShow> tvShowList) {
        this.mTvShowList = tvShowList;
    }

    public void setFragment(ListFragment fragment) {
        this.mListFragment = fragment;
    }

    @Override
    public TvShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new TvShowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TvShowViewHolder holder, final int position) {
        TvShow tvShow = mTvShowList.get(position);
        holder.mName.setText(tvShow.getName());
        holder.mRelaseDate.setText(tvShow.getFirstAirDate());
        holder.mRatingScore.setText(String.valueOf(tvShow.getAverageRating()));
        Picasso.with(mListFragment.getActivity())
                .load("https://image.tmdb.org/t/p/w92" + tvShow.getPosterPath()).into(holder.mPoster);

        // implementation later
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.d("--->", "onClick " + String.valueOf(position));
//                mListFragment.onItemClick(position);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return mTvShowList.size();
    }
}
