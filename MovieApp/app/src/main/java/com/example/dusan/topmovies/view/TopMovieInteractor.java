package com.example.dusan.topmovies.view;

import com.example.dusan.topmovies.model.DataManager;
import com.example.dusan.topmovies.model.MoviesResponse;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Dusan on 04.Jun.17.
 */

public class TopMovieInteractor extends BaseInteractor<MoviesResponse> {


  public TopMovieInteractor() {
    super();
  }

  @Override
  public Observable<MoviesResponse> buildUseCaseObservable(int pageNumber) {
    return DataManager.fetchTopRatedMoviesData(pageNumber)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread());
  }
}
