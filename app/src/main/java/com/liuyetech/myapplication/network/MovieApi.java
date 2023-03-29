package com.liuyetech.myapplication.network;

import com.liuyetech.myapplication.entity.Cast;
import com.liuyetech.myapplication.entity.Crew;
import com.liuyetech.myapplication.entity.Movie;
import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.entity.Video;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface MovieApi {
    @GET("movie/list")
    Call<Result<List<Movie>>> getMovieList();

    @GET("movie/cast/{mid}")
    Call<Result<List<Cast>>> getMovieCastByMid(@Path("mid") int mid);

    @GET("movie/crew/{mid}")
    Call<Result<List<Crew>>> getMovieCrewByMid(@Path("mid") int mid);

    @GET("movie/{mid}")
    Call<Result<Movie>> getMovieByMId(@Path("mid") int mid);

    @GET("movie/type/{tid}")
    Call<Result<List<Movie>>> getMovieListByType(@Path("tid") int tid);
}
