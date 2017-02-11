package com.example.dusan.topmovies.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.dusan.topmovies.R;
import com.example.dusan.topmovies.presenter.Presenter;

import java.util.List;

public class TopRatedMovieActivity extends FragmentActivity {


    private MovieListFragment mMovieListFragment;
    public Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new Presenter(this);

        mMovieListFragment = new MovieListFragment();

        FragmentManager fragmentManager =
                getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_holder, mMovieListFragment);
        fragmentTransaction.commit();

        mMovieListFragment.initPresenter(presenter);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
    }

    public void showData(List movieList)
    {
        mMovieListFragment.updateView(movieList);
    }

    public void showDetailFragment(int position)
    {

        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        movieDetailFragment.initPresenter(presenter);
        movieDetailFragment.setMovieID(position);
        FragmentManager fragmentManager =
                getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_holder, movieDetailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
