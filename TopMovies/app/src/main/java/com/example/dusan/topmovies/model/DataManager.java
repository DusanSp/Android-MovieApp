package com.example.dusan.topmovies.model;


import android.util.Log;

import com.example.dusan.topmovies.presenter.IPresenter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataManager {
    private final String API_KEY = "cea376b36a54214485643698fe4bfd16";
    private MovieAPI mMovieAPI;
    private List<Movie> movies = new ArrayList<>();
    private IPresenter mPresenter;


    public DataManager(IPresenter presenter)
    {
        this.mPresenter = presenter;
    }

    public Movie getMovie(int index)
    {
        return movies.get(index);
    }

    public void fetchTopRatedMoviesData() {
        mMovieAPI = new MovieAPI();

        Call<MoviesResponse> call = mMovieAPI.getService().getTopRatedMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if(response.code() == 200)
                {
                    movies = response.body().getResults();
                    for (Movie m : movies) {
                        Log.d("TEST", m.getTitle());
                    }

                    if(movies != null && movies.size() > 0)
                    {
                        mPresenter.notifayTopRatedMovies(movies);
                    }
                    else{
                        Log.d("Error", "Cant get movie from response.");
                    }
                }
                else
                {
                    Log.d("Error", response.message());
                    movies = null;
                }

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d("onFailure", t.toString());
                movies = null;
            }
        });
    }

    public void fetchUpcomingMoviesData() {
        mMovieAPI = new MovieAPI();

        Call<MoviesResponse> call = mMovieAPI.getService().getUpComingMovies(API_KEY);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if(response.code() == 200)
                {
                    movies = response.body().getResults();
                    for (Movie m : movies) {
                        Log.d("TEST", m.getTitle());
                    }

                    if(movies != null && movies.size() > 0)
                    {
                        mPresenter.notifayUpcomingMovies(movies);
                    }
                    else{
                        Log.d("Error", "Cant get movie from response.");
                    }
                }
                else
                {
                    Log.d("Error", response.message());
                    movies = null;
                }

            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d("onFailure", t.toString());
                movies = null;
            }
        });
    }
}
