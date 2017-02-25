package com.example.dusan.topmovies.presenter;


import com.example.dusan.topmovies.model.DataManager;
import com.example.dusan.topmovies.model.Movie;
import com.example.dusan.topmovies.model.TvShow;
import com.example.dusan.topmovies.view.TvShowsOnTheAirActivity;
import com.example.dusan.topmovies.view.TopRatedMovieActivity;
import com.example.dusan.topmovies.view.UpcomingMoviesActivity;

import java.util.List;

public class Presenter implements IPresenter {


    private TopRatedMovieActivity topRatedMovieActivity;
    private UpcomingMoviesActivity upcomingMoviesActivity;
    private TvShowsOnTheAirActivity tvShowsOnTheAirActivity;
    private DataManager mDataManager;

    public Presenter() {

        mDataManager = new DataManager(this);
    }

    public void initTopRatedMovieActivity(TopRatedMovieActivity activity) {
        topRatedMovieActivity = activity;
        upcomingMoviesActivity = null;
        tvShowsOnTheAirActivity = null;
    }

    public void initUpcomingMovieActivity(UpcomingMoviesActivity activity) {
        upcomingMoviesActivity = activity;
        topRatedMovieActivity = null;
        tvShowsOnTheAirActivity = null;
    }

    public void initTvShowsOnTheAirActivity(TvShowsOnTheAirActivity activity) {
        tvShowsOnTheAirActivity = activity;
        topRatedMovieActivity = null;
        upcomingMoviesActivity = null;
    }

    public void getTopRatedMoviesData(boolean isUpdate) {
        mDataManager.fetchTopRatedMoviesData(isUpdate);
    }

    @Override
    public void updateTopRatedMoviesView() {
        topRatedMovieActivity.updateTopRatedMoviesView();
    }

    public void getUpcomingMoviesData()
    {
        mDataManager.fetchUpcomingMoviesData();
    }

    public void getTvShowsOnTheAirData()
    {
        mDataManager.fetchTvShowsOnTheAirData();
    }

    @Override
    public Movie getMovieDetails(int position) {
        return mDataManager.getMovie(position);
    }

    @Override
    public void notifyTopRatedMovies(List<Movie> movies) {
        topRatedMovieActivity.showTopRatedMoviesData(movies);
    }

    @Override
    public void notifyUpcomingMovies(List<Movie> movies) {
        upcomingMoviesActivity.showUpcomingMoviesData(movies);
    }

    @Override
    public void notifyTvShowsOnTheAir(List<TvShow> tvShows) {
        tvShowsOnTheAirActivity.showTvShowOnTheAir(tvShows);
    }

    @Override
    public void onItemListClicked(int position) {
        if(topRatedMovieActivity != null)
        {
            topRatedMovieActivity.showDetailFragment(position);
        }

        if(upcomingMoviesActivity != null)
        {
            upcomingMoviesActivity.showDetailFragment(position);
        }
    }
}
