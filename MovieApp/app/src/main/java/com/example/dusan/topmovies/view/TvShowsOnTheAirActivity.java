package com.example.dusan.topmovies.view;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.dusan.topmovies.R;
import com.example.dusan.topmovies.presenter.Presenter;

import java.util.List;


public class TvShowsOnTheAirActivity extends FragmentActivity {

    private ListFragment mListFragment;
    public Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serieslist);


        presenter = new Presenter();
        presenter.initTvShowsOnTheAirActivity(this);

        mListFragment = new ListFragment();

        FragmentManager fragmentManager =
                getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragment_holder_series, mListFragment);
        fragmentTransaction.commit();

        mListFragment.initPresenter(presenter);
    }

    @Override
    protected void onStart()
    {
        super.onStart();

        // TODO: check if tv show data have been fetched from server
        presenter.getTvShowsOnTheAirData();
    }

    public void showTvShowOnTheAir(List tvShowList)
    {
        mListFragment.updateTvShowView(tvShowList);
    }
}
