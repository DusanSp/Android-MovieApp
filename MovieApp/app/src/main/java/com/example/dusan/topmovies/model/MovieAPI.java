package com.example.dusan.topmovies.model;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieAPI {

    private APICommunication apiCommunication;

    private final String BASE_URL = "http://api.themoviedb.org/3/";

    public MovieAPI()
    {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiCommunication = retrofit.create(APICommunication.class);
    }

    public APICommunication getService()
    {
        return apiCommunication;
    }
}
