package com.example.dusan.topmovies.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import com.example.dusan.topmovies.R;
import com.example.dusan.topmovies.presenter.SearchPresenter;

public class SearchActivity extends Activity {

  private SearchFragment mSearchFragment;
  public SearchPresenter mSearchPresenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_search);

    mSearchFragment = new SearchFragment();

    FragmentManager fragmentManager =
        getFragmentManager();
    FragmentTransaction fragmentTransaction =
        fragmentManager.beginTransaction();
    fragmentTransaction.add(R.id.fragment_holder_search, mSearchFragment);
    fragmentTransaction.commit();

    mSearchPresenter = new SearchPresenter(mSearchFragment);
    mSearchFragment.initPresenter(mSearchPresenter);
  }
}
