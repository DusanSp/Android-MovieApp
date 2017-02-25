package com.example.dusan.topmovies.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.dusan.topmovies.R;
import com.example.dusan.topmovies.presenter.Presenter;

import java.util.List;

public class UpcomingMoviesActivity extends Activity {

    private ListFragment mListFragment;
    public Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcomingmovies);

        presenter = new Presenter();
        presenter.initUpcomingMovieActivity(this);

        mListFragment = new ListFragment();

        FragmentManager fragmentManager =
                getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_holder_upcoming, mListFragment);
        fragmentTransaction.commit();

        mListFragment.initPresenter(presenter);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        // TODO: check if movie data have been fetched from server
        presenter.getUpcomingMoviesData();
    }

    public void showUpcomingMoviesData(List movieList)
    {
        mListFragment.showMovieList(movieList);
    }

    public void showDetailFragment(int position)
    {
        DetailFragment detailFragment = new DetailFragment();
        detailFragment.initPresenter(presenter);
        detailFragment.setMovieID(position);
        FragmentManager fragmentManager =
                getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_holder_upcoming, detailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
