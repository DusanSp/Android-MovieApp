package com.example.dusan.topmovies.presenter;


import com.example.dusan.topmovies.model.Movie;
import com.example.dusan.topmovies.model.TvShow;

import java.util.List;

public interface IPresenter {

    void notifyTopRatedMovies(List<Movie> movies);
    void notifyUpcomingMovies(List<Movie> movies);
    void notifyTvShowsOnTheAir(List<TvShow> tvShows);
    void onItemListClicked(int position);
    void getTopRatedMoviesData(boolean isUpdate);
    void updateTopRatedMoviesView();
    Movie getMovieDetails(int index);
}
