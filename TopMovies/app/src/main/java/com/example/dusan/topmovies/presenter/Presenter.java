package com.example.dusan.topmovies.presenter;


import com.example.dusan.topmovies.model.DataManager;
import com.example.dusan.topmovies.model.Movie;
import com.example.dusan.topmovies.view.TopRatedMovieActivity;
import com.example.dusan.topmovies.view.UpcomingMoviesActivity;

import java.util.List;

public class Presenter implements IPresenter {


    private TopRatedMovieActivity topRatedMovieActivity;
    private UpcomingMoviesActivity upcomingMoviesActivity;
    private DataManager mDataManager;

    public Presenter() {

        mDataManager = new DataManager(this);
    }

    public void initTopRatedMovieActivity(TopRatedMovieActivity activity) {
        topRatedMovieActivity = activity;
    }

    public void initUpcomingMovieActivity(UpcomingMoviesActivity activity) {
        upcomingMoviesActivity = activity;
    }

    public void getTopRatedMoviesData() {
        mDataManager.fetchTopRatedMoviesData();
    }

    public void getUpcomingMoviesData()
    {
        mDataManager.fetchUpcomingMoviesData();
    }

    @Override
    public Movie getMovieDetails(int position) {
        return mDataManager.getMovie(position);
    }

    @Override
    public void notifayTopRatedMovies(List<Movie> movies) {
        topRatedMovieActivity.showTopRatedMoviesData(movies);
    }

    @Override
    public void notifayUpcomingMovies(List<Movie> movies) {
        upcomingMoviesActivity.showUpcomingMoviesData(movies);
    }

    @Override
    public void onItemListClicked(int position) {
        topRatedMovieActivity.showDetailFragment(position);
    }
}
