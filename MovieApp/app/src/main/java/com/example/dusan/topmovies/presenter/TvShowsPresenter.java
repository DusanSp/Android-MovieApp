package com.example.dusan.topmovies.presenter;

import android.util.Log;
import com.example.dusan.topmovies.model.DataManager;
import com.example.dusan.topmovies.model.TvShowResponse;
import com.example.dusan.topmovies.view.IListView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;


public class TvShowsPresenter implements IPresenter<IListView> {

  private Observable<TvShowResponse> responseObservable;
  private DisposableObserver<TvShowResponse> disposable;

  private DataManager mDataManager;
  private IListView mListView;
  private int page = 1;

  public TvShowsPresenter(IListView listView) {
    mDataManager = new DataManager();
    this.mListView = listView;
  }

  @Override
  public void loadData() {
    mListView.showLoadingIndicator();

    disposable = new DisposableObserver<TvShowResponse>() {
      @Override
      public void onNext(TvShowResponse value) {
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

    responseObservable = mDataManager.fetchTvShowsOnTheAirData(page);
    responseObservable
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(disposable);
  }

  @Override
  public void disposeResource() {
    if (!disposable.isDisposed()) {
      disposable.dispose();
    }
  }
}
