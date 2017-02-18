package com.example.dusan.topmovies.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dusan.topmovies.R;

import butterknife.ButterKnife;
import butterknife.OnClick;


public class MenuActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_topratedmovies)
    public void showTopRatedMovies(View view) {
        Intent topRatedMoviesIntent = new Intent(MenuActivity.this,
                TopRatedMovieActivity.class);
        startActivity(topRatedMoviesIntent);
    }

    @OnClick(R.id.btn_upcamingmovies)
    public void showUpcomingMovies(View view)
    {
        Intent upcomingMoviesIntent = new Intent(MenuActivity.this,
                UpcomingMoviesActivity.class);
        startActivity(upcomingMoviesIntent);
    }

    @OnClick(R.id.btn_tvshowsontheair)
    public void showTvShowsOnTheAir(View view)
    {
        Intent upcomingMoviesIntent = new Intent(MenuActivity.this,
                TvShowsOnTheAirActivity.class);
        startActivity(upcomingMoviesIntent);
    }
}
