package com.example.dusan.topmovies.presenter;

import com.example.dusan.topmovies.model.MoviesResponse;
import com.example.dusan.topmovies.view.IListView;
import com.example.dusan.topmovies.view.TopMovieInteractor;
import io.reactivex.observers.DisposableObserver;

public class TopMoviesPresenter implements IPresenter {

  private TopMovieInteractor mInteractor;
  private IListView mListView;
  private int page = 1;

  public TopMoviesPresenter(IListView listView) {
    this.mListView = listView;
  }

  @Override
  public void loadData() {

    mListView.showLoadingIndicator();
    mInteractor = new TopMovieInteractor();
    mInteractor.execute(new TopMoviesObserver(), page);
  }

  @Override
  public void disposeResource() {
    if(mInteractor != null) {
      mInteractor.dispose();
    }
  }

  private final class TopMoviesObserver extends DisposableObserver<MoviesResponse> {

    @Override
    public void onNext(MoviesResponse value) {
      TopMoviesPresenter.this.mListView.showData(value.getResults());
    }

    @Override
    public void onError(Throwable e) {
      TopMoviesPresenter.this.mListView.hideLoadingIndicator();
    }

    @Override
    public void onComplete() {
      TopMoviesPresenter.this.mListView.hideLoadingIndicator();
    }
  }
}
