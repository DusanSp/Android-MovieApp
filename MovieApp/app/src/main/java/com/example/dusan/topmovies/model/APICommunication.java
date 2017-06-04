package com.example.dusan.topmovies.model;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APICommunication {

  @GET("movie/top_rated")
  Observable<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey,
      @Query("page") int pageNumber);

  @GET("movie/upcoming")
  Observable<MoviesResponse> getUpComingMovies(@Query("api_key") String apiKey);

  @GET("tv/on_the_air")
  Observable<TvShowResponse> getShowsOnTheAir(@Query("api_key") String apiKey,
      @Query("page") int pageNumber);

  @GET("search/movie")
  Observable<MoviesResponse> searchMovie(
      @Query("api_key") String apiKey,
      @Query("query") String query,
      @Query("page") int pageNumber);
}
