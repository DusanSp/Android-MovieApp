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
    private List<Movie> movieList = new ArrayList<>();
    private List<TvShow> tvShowList = new ArrayList<>();
    private IPresenter mPresenter;
    private int pageNumber = 1;


    public DataManager(IPresenter presenter)
    {
        this.mPresenter = presenter;
    }

    public Movie getMovie(int index)
    {
        return movieList.get(index);
    }

    public void fetchTopRatedMoviesData(final boolean isUpdate) {
        mMovieAPI = new MovieAPI();

        Call<MoviesResponse> call = mMovieAPI.getService().getTopRatedMovies(API_KEY, pageNumber);
        call.enqueue(new Callback<MoviesResponse>() {
            @Override
            public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                if(response.code() == 200)
                {
                    List<Movie> movieListFromResponse = response.body().getResults();
                    for (Movie m : movieListFromResponse) {
                        Log.d("TEST", m.getTitle());
                    }

                    movieList.addAll(movieListFromResponse);

                    if(movieList != null && movieList.size() > 0)
                    {
                        if(!isUpdate)
                        {
                            mPresenter.notifyTopRatedMovies(movieList);
                        }
                        else
                        {
                            mPresenter.updateTopRatedMoviesView();
                        }

                        if(pageNumber < response.body().getTotalPages())
                        {
                            pageNumber++;
                        }
                    }
                    else{
                        Log.d("Error", "Cant get movie from response.");
                    }
                }
                else
                {
                    Log.d("Error", response.message());
                    movieList = null;
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d("onFailure", t.toString());
                movieList = null;
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
                    movieList = response.body().getResults();
                    for (Movie m : movieList) {
                        Log.d("TEST", m.getTitle());
                    }

                    if(movieList != null && movieList.size() > 0)
                    {
                        mPresenter.notifyUpcomingMovies(movieList);
                    }
                    else{
                        Log.d("Error", "Cant get movie from response.");
                    }
                }
                else
                {
                    Log.d("Error", response.message());
                    movieList = null;
                }
            }

            @Override
            public void onFailure(Call<MoviesResponse> call, Throwable t) {
                Log.d("onFailure", t.toString());
                movieList = null;
            }
        });
    }

    public void fetchTvShowsOnTheAirData() {
        mMovieAPI = new MovieAPI();

        Call<TvShowResponse> call = mMovieAPI.getService().getShowsOnTheAir(API_KEY);
        call.enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if(response.code() == 200)
                {
                    tvShowList = response.body().getResults();
                    for (TvShow tvShow : tvShowList) {
                        Log.d("TEST", tvShow.getName());
                    }

                    if(tvShowList != null && tvShowList.size() > 0)
                    {
                        mPresenter.notifyTvShowsOnTheAir(tvShowList);
                    }
                    else{
                        Log.d("Error", "Cant get movie from response.");
                    }
                }
                else
                {
                    Log.d("Error", response.message());
                    tvShowList = null;
                }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {
                Log.d("onFailure", t.toString());
                tvShowList = null;
            }
        });
    }
}
