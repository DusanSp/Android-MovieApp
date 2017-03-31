package com.example.dusan.topmovies.presenter;

import android.util.Log;
import com.example.dusan.topmovies.model.DataManager;
import com.example.dusan.topmovies.model.Movie;
import com.example.dusan.topmovies.model.MoviesResponse;
import com.example.dusan.topmovies.view.IListView;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.Collections;
import java.util.List;


public class TopMoviesPresenter implements IPresenter<IListView> {

  private DataManager mDataManager;
  private IListView mListView;
  private Observable<MoviesResponse> responseObservable;
  private DisposableObserver<MoviesResponse> disposable;
  private int page = 1;


  public TopMoviesPresenter(IListView listView) {
    this.mDataManager = new DataManager(this);
    this.mListView = listView;
  }

  @Override
  public void loadData() {

    mListView.showLoadingIndicator();

    disposable = new DisposableObserver<MoviesResponse>() {
      @Override
      public void onNext(MoviesResponse value) {
        mListView.showData(value.getResults());
        if (value.getTotalPages() != page) {
          page++;
        }
      }

      @Override
      public void onError(Throwable e) {
        mListView.hideLoadingIndicator();
        Log.d("TopMoviesPresenter", "onError: " + e);
      }

      @Override
      public void onComplete() {
        mListView.hideLoadingIndicator();
        Log.d("TopMoviesPresenter", "onComplete");
      }
    };

    responseObservable = mDataManager.fetchTopRatedMoviesData(page);
    responseObservable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(disposable);
  }

  public void disposeResource()
  {
    if (!disposable.isDisposed()) {
      disposable.dispose();
    }
  }

  @Override
  public void notifyDataChange(List movieList) {
    if (!movieList.isEmpty()) {
      mListView.showData(movieList);
    } else {
      mListView.showData(Collections.<String>emptyList());
      Log.d("TopMoviesPresenter", "Movie list is empty");
    }
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
