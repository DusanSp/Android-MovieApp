package com.example.dusan.topmovies.presenter;


import com.example.dusan.topmovies.model.Movie;

import java.util.List;

public interface IPresenter {

    void notifay(List<Movie> movies);
    void onItemListClicked(int positon);
    void getMoviesData();
    Movie getMovieDetails(int index);
}
