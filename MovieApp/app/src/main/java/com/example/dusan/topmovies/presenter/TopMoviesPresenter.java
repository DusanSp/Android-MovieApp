package com.example.dusan.topmovies.presenter;

import com.example.dusan.topmovies.model.DataManager;
import com.example.dusan.topmovies.model.Movie;
import com.example.dusan.topmovies.view.IListView;
import java.util.List;


public class TopMoviesPresenter implements IPresenter<IListView> {

  private DataManager mDataManager;
  private IListView mListView;


  public TopMoviesPresenter(IListView listView) {
    this.mDataManager = new DataManager(this);
    this.mListView = listView;
  }

  @Override
  public void loadData() {
    mDataManager.fetchTopRatedMoviesData();
  }

  @Override
  public void notifyDataChange(List movieList) {
    mListView.showData(movieList);
  }

  @Override
  public Movie getDetailData(int position) {
    return mDataManager.getMovie(position);
  }

  @Override
  public void showLoading() {
    mListView.showLoadingIndicator();
  }

  @Override
  public void hideLoading() {
    mListView.hideLoadingIndicator();
  }
}
