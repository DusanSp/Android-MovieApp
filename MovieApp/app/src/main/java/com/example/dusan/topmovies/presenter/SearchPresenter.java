package com.example.dusan.topmovies.presenter;

import android.util.Log;
import com.example.dusan.topmovies.model.DataManager;
import com.example.dusan.topmovies.model.MoviesResponse;
import com.example.dusan.topmovies.view.IListView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Predicate;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;


public class SearchPresenter implements IPresenterSearch {

  private IListView mListView;
  private DataManager dataManager;
  private Observable<MoviesResponse> responseObservable;
  private DisposableObserver<MoviesResponse> disposable;
  private int page = 1;

  public SearchPresenter(IListView listView) {
    dataManager = new DataManager();
    this.mListView = listView;
  }

  @Override
  public void loadData(final String query) {
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
        Log.d("SearchPresenter", "onError: " + e);
      }

      @Override
      public void onComplete() {
        mListView.hideLoadingIndicator();
        Log.d("SearchPresenter", "onComplete");
      }
    };

//    responseObservable = dataManager.searchMovie(query, page);
//    responseObservable
//        .subscribeOn(Schedulers.io())
//        .observeOn(AndroidSchedulers.mainThread())
//        .filter(new Predicate<MoviesResponse>() {
//          @Override
//          public boolean test(MoviesResponse moviesResponse) throws Exception {
//            return query.length() >= 2;
//          }
//        })
//        .debounce(3000, TimeUnit.MILLISECONDS)
//        .subscribe(disposable);
  }

  @Override
  public void disposeResource() {
    if (!disposable.isDisposed()) {
      disposable.dispose();
    }
  }
}
