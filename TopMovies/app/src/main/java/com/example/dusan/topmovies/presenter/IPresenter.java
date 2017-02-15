package com.example.dusan.topmovies.presenter;


import com.example.dusan.topmovies.model.Movie;

import java.util.List;

public interface IPresenter {

    void notifayTopRatedMovies(List<Movie> movies);
    void notifayUpcomingMovies(List<Movie> movies);
    void onItemListClicked(int positon);
    void getTopRatedMoviesData();
    Movie getMovieDetails(int index);
}
