package com.example.dusan.topmovies.view;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;

import com.example.dusan.topmovies.R;
import com.example.dusan.topmovies.model.Movie;
import com.example.dusan.topmovies.presenter.IPresenter;
import com.example.dusan.topmovies.view.adapters.MovieViewAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MovieListFragment extends Fragment implements IListView {

  @BindView(R.id.recycler_view)
  RecyclerView mRecyclerView;

  private MovieViewAdapter mMovieViewAdapter;
  private IPresenter presenter;
  private LinearLayoutManager mLayoutManager;
  private boolean userScrolled = true;
  private List<Movie> movieList = new ArrayList<>();
  private int lastVisiblesItems;
  private int totalItemCount;
  private int visibleThreshold = 5;
  private ProgressDialog mProgressDialog;


  public void initPresenter(IPresenter presenter) {
    this.presenter = presenter;
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.list_fragment, container, false);
    ButterKnife.bind(this, view);

    mLayoutManager = new LinearLayoutManager(getActivity());
    mRecyclerView.setLayoutManager(mLayoutManager);

    mMovieViewAdapter = new MovieViewAdapter(movieList);
    mMovieViewAdapter.setFragment(this);
    mRecyclerView.setAdapter(mMovieViewAdapter);

    implementScrollListener();

    return view;
  }

  private void implementScrollListener() {
    mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override
      public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
          userScrolled = true;
        }
      }

      @Override
      public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        totalItemCount = mLayoutManager.getItemCount();
        lastVisiblesItems = mLayoutManager.findLastVisibleItemPosition();

        if (userScrolled && (visibleThreshold + lastVisiblesItems) >= totalItemCount) {
          userScrolled = false;
          presenter.loadData();
        }
      }
    });
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState) {
    super.onActivityCreated(savedInstanceState);

    presenter.loadData();
  }

  @Override
  public void onStart() {
    super.onStart();
  }

  @Override
  public void onItemClick(Movie movie) {
    DetailFragment detailFragment = new DetailFragment();
    detailFragment.initPresenter(presenter);
    detailFragment.setMovie(movie);
    FragmentManager fragmentManager =
        getFragmentManager();
    FragmentTransaction fragmentTransaction =
        fragmentManager.beginTransaction();
    fragmentTransaction.replace(R.id.fragment_holder, detailFragment);
    fragmentTransaction.addToBackStack(null);
    fragmentTransaction.commit();
  }

  @Override
  public void showData(List list) {
    if (!list.isEmpty()) {
      mMovieViewAdapter.dataSetChange(list);
    } else {
      mMovieViewAdapter.dataSetChange(Collections.<String>emptyList());
      Log.d("MovieListFragment", "Movie list is empty");
    }
  }

  @Override
  public void showLoadingIndicator() {
    if(mProgressDialog == null)
    {
      mProgressDialog = new ProgressDialog(getActivity());
      mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
      mProgressDialog.setMessage("It's loading...");
      mProgressDialog.setTitle("Loading");
    }

    mProgressDialog.show();
  }

  @Override
  public void hideLoadingIndicator() {
    mProgressDialog.hide();
  }

  @Override
  public void onDestroyView() {
    mProgressDialog.dismiss();
    presenter.disposeResource();
    super.onDestroyView();
  }
}
