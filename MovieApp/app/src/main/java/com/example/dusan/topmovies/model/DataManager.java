package com.example.dusan.topmovies.model;


import io.reactivex.Observable;


public class DataManager {

  private final String API_KEY = "cea376b36a54214485643698fe4bfd16";
  private MovieAPI mMovieAPI;


  public DataManager() {
    mMovieAPI = new MovieAPI();
  }

  public Observable<MoviesResponse> fetchTopRatedMoviesData(int pageNumber) {
    return mMovieAPI.getService().getTopRatedMovies(API_KEY, pageNumber);
  }

//  public void fetchTopRatedMoviesData() {
//
//    mPresenter.showLoading();
//    mMovieAPI = new MovieAPI();
//
//    Call<MoviesResponse> call = mMovieAPI.getService().getTopRatedMovies(API_KEY, pageNumber);
//    call.enqueue(new Callback<MoviesResponse>() {
//      @Override
//      public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
//        if (response.code() == 200) {
//          List<Movie> movieListFromResponse = response.body().getResults();
//          for (Movie m : movieListFromResponse) {
//            Log.d("TEST", m.getTitle());
//          }
//
//          movieList.addAll(movieListFromResponse);
//
//          if (movieList != null && movieList.size() > 0) {
//            mPresenter.showMovieData(movieList);
//            mPresenter.hideLoading();
//
//            if (pageNumber < response.body().getTotalPages()) {
//              pageNumber++;
//            }
//          } else {
//            Log.e("DataManager", "Cant get movie from response.");
//          }
//        } else {
//          Log.e("DataManager", response.message());
//          movieList = null;
//        }
//      }
//
//      @Override
//      public void onFailure(Call<MoviesResponse> call, Throwable t) {
//        Log.e("DataManager", t.toString());
//        movieList = null;
//      }
//    });
//  }

  public Observable<MoviesResponse> fetchUpcomingMoviesData() {
    return mMovieAPI.getService().getUpComingMovies(API_KEY);
  }
//  public void fetchUpcomingMoviesData() {
//    mPresenter.showLoading();
//    mMovieAPI = new MovieAPI();
//
//    Call<MoviesResponse> call = mMovieAPI.getService().getUpComingMovies(API_KEY);
//    call.enqueue(new Callback<MoviesResponse>() {
//      @Override
//      public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
//        if (response.code() == 200) {
//          movieList = response.body().getResults();
//          for (Movie m : movieList) {
//            Log.d("TEST", m.getTitle());
//          }
//
//          if (movieList != null && movieList.size() > 0) {
//            mPresenter.showMovieData(movieList);
//            mPresenter.hideLoading();
//          } else {
//            Log.e("DataManager", "Cant get movie from response.");
//          }
//        } else {
//          Log.e("DataManager", response.message());
//          movieList = null;
//        }
//      }
//
//      @Override
//      public void onFailure(Call<MoviesResponse> call, Throwable t) {
//        Log.e("DataManager", t.toString());
//        movieList = null;
//      }
//    });
//  }

  public Observable<TvShowResponse> fetchTvShowsOnTheAirData(int page) {
    return mMovieAPI.getService().getShowsOnTheAir(API_KEY, page);
  }

  public Observable<MoviesResponse> searchMovie(String query, int page)
  {
    return mMovieAPI.getService().searchMovie(API_KEY, query, page);
  }
//  public void fetchTvShowsOnTheAirData() {
//    mPresenter.showLoading();
//    mMovieAPI = new MovieAPI();
//
//    Call<TvShowResponse> call = mMovieAPI.getService().getShowsOnTheAir(API_KEY, pageNumberTvShow);
//    call.enqueue(new Callback<TvShowResponse>() {
//      @Override
//      public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
//        if (response.code() == 200) {
//
//          List<TvShow> tvShowListResponse = response.body().getResults();
//
//          for (TvShow tvShow : tvShowList) {
//            Log.d("TEST", tvShow.getName());
//          }
//
//          tvShowList.addAll(tvShowListResponse);
//
//          if (tvShowList != null && tvShowList.size() > 0) {
//            mPresenter.showMovieData(tvShowList);
//            mPresenter.hideLoading();
//
//            if (pageNumberTvShow < response.body().getTotalPages()) {
//              pageNumberTvShow++;
//            }
//          } else {
//            Log.e("DataManager", "Cant get tv shows from response.");
//          }
//        } else {
//          Log.e("DataManager", response.message());
//          tvShowList = null;
//        }
//      }
//
//      @Override
//      public void onFailure(Call<TvShowResponse> call, Throwable t) {
//        Log.d("onFailure", t.toString());
//        tvShowList = null;
//      }
//    });
//  }
}
