package com.example.dusan.topmovies.presenter;


import com.example.dusan.topmovies.model.DataManager;
import com.example.dusan.topmovies.model.Movie;
import com.example.dusan.topmovies.view.TopRatedMovieActivity;

import java.util.List;

public class Presenter implements IPresenter {


    private TopRatedMovieActivity topRatedMovieActivity;
    private DataManager mDataManager;

    public Presenter(TopRatedMovieActivity activity) {
        topRatedMovieActivity = activity;
        mDataManager = new DataManager(this);
    }

    public void getMoviesData() {
        mDataManager.fetchMovieData();
    }

    @Override
    public Movie getMovieDetails(int position) {
        return mDataManager.getMovie(position);
    }

    @Override
    public void notifay(List<Movie> movies) {
        topRatedMovieActivity.showData(movies);
    }

    @Override
    public void onItemListClicked(int position) {
        topRatedMovieActivity.showDetailFragment(position);
    }
}
