package com.example.dusan.topmovies.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import com.example.dusan.topmovies.R;
import com.example.dusan.topmovies.presenter.UpcomingMoviesPresenter;


public class UpcomingMoviesActivity extends Activity {

  private MovieListFragment mMovieListFragment;
  public UpcomingMoviesPresenter mUpcomingMoviesPresenter;


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

    mUpcomingMoviesPresenter = new UpcomingMoviesPresenter(mMovieListFragment);
    mMovieListFragment.initPresenter(mUpcomingMoviesPresenter);
  }

  @Override
  public void onDestroy() {
    mUpcomingMoviesPresenter.disposeResource();
    super.onDestroy();
  }
}
