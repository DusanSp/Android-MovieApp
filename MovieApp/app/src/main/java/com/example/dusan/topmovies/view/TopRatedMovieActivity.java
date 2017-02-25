package com.example.dusan.topmovies.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.dusan.topmovies.R;
import com.example.dusan.topmovies.presenter.Presenter;

import java.util.List;

public class TopRatedMovieActivity extends FragmentActivity {


    private ListFragment mListFragment;
    public Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topratedmovies);

        presenter = new Presenter();
        presenter.initTopRatedMovieActivity(this);

        mListFragment = new ListFragment();

        FragmentManager fragmentManager =
                getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_holder, mListFragment);
        fragmentTransaction.commit();

        mListFragment.initPresenter(presenter);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        // TODO: check if movie data have been fetched from server
        presenter.getTopRatedMoviesData(false);
    }

    public void showTopRatedMoviesData(List movieList)
    {
        mListFragment.showMovieList(movieList);
    }

    public void updateTopRatedMoviesView()
    {
        mListFragment.updateMovieListView();
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
        fragmentTransaction.replace(R.id.fragment_holder, detailFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
