package com.example.dusan.topmovies.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.dusan.topmovies.R;
import com.example.dusan.topmovies.presenter.TopMoviesPresenter;

public class TopRatedMovieActivity extends FragmentActivity {


    private MovieListFragment mMovieListFragment;
    public TopMoviesPresenter mTopMoviesPresenterenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movies_list);

        mMovieListFragment = new MovieListFragment();

        FragmentManager fragmentManager =
                getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_holder, mMovieListFragment);
        fragmentTransaction.commit();

        mTopMoviesPresenterenter = new TopMoviesPresenter(mMovieListFragment);
        mMovieListFragment.initPresenter(mTopMoviesPresenterenter);
    }
}
