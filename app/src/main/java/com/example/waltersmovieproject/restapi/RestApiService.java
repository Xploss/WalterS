package com.example.waltersmovieproject.restapi;

import com.example.waltersmovieproject.moviemodel.ListMovieModel;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApiService {
    @GET("3/movie/popular?api_key=0dde3e9896a8c299d142e214fcb636f8&language=en-US")
    Call<ListMovieModel> getList();
}
