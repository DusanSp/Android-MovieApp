package com.example.dusan.topmovies.view;


import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dusan.topmovies.model.Movie;
import com.example.dusan.topmovies.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieViewAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private List<Movie> mMovieList;
    private ListFragment mListFragment;

    public MovieViewAdapter(List<Movie> movies) {
        this.mMovieList = movies;

    }

    public void setFragment(ListFragment fragment) {
        this.mListFragment = fragment;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_row, parent, false);

        return new MovieViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        Movie movie = mMovieList.get(position);
        holder.mTitle.setText(movie.getTitle());
        holder.mRelaseDate.setText(movie.getRelaseDate());
        holder.mRatingScore.setText(String.valueOf(movie.getAverageRating()));
        Picasso.with(mListFragment.getActivity())
                .load("https://image.tmdb.org/t/p/w92" + movie.getPosterPath()).into(holder.mPoster);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("--->", "onClick " + String.valueOf(position));
                mListFragment.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }
}
