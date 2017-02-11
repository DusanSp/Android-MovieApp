package com.example.dusan.topmovies.model;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APICommunication {

    @GET("movie/top_rated")
    Call<MoviesResponse> getTopRatedMovies(@Query("api_key") String apiKey);
}
