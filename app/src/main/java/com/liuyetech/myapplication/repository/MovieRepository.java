package com.liuyetech.myapplication.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.liuyetech.myapplication.entity.Cast;
import com.liuyetech.myapplication.entity.Crew;
import com.liuyetech.myapplication.entity.Movie;
import com.liuyetech.myapplication.entity.Result;
import com.liuyetech.myapplication.network.MovieApi;
import com.liuyetech.myapplication.network.RetrofitUtils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    public static final MovieRepository MOVIE_REPOSITORY = new MovieRepository();

    private MovieApi movieApi;

    private MovieRepository() {
        movieApi = RetrofitUtils.getInstance().create(MovieApi.class);
    }

    public LiveData<List<Movie>> getMovieList() {
        MutableLiveData<List<Movie>> data = new MutableLiveData<>();
        movieApi.getMovieList().enqueue(new Callback<Result<List<Movie>>>() {
            @Override
            public void onResponse(@NonNull Call<Result<List<Movie>>> call, @NonNull Response<Result<List<Movie>>> response) {
                if (response.isSuccessful()) {
                    Result<List<Movie>> MovieResult = response.body();
                    if (MovieResult != null && MovieResult.getCode() == 200) {
                        data.setValue(MovieResult.getData());
                    } else {
                        data.setValue(null);
                    }
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<List<Movie>>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<List<Cast>> getMovieCastByMid(int mid) {
        MutableLiveData<List<Cast>> data = new MutableLiveData<>();
        movieApi.getMovieCastByMid(mid).enqueue(new Callback<Result<List<Cast>>>() {
            @Override
            public void onResponse(@NonNull Call<Result<List<Cast>>> call, @NonNull Response<Result<List<Cast>>> response) {
                if (response.isSuccessful()) {
                    Result<List<Cast>> MovieResult = response.body();
                    if (MovieResult != null && MovieResult.getCode() == 200) {
                        data.setValue(MovieResult.getData());
                    } else {
                        data.setValue(null);
                    }
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<List<Cast>>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;

    }

    public LiveData<List<Crew>> getMovieCrewByMid(int mid) {
        MutableLiveData<List<Crew>> data = new MutableLiveData<>();
        movieApi.getMovieCrewByMid(mid).enqueue(new Callback<Result<List<Crew>>>() {
            @Override
            public void onResponse(@NonNull Call<Result<List<Crew>>> call, @NonNull Response<Result<List<Crew>>> response) {
                if (response.isSuccessful()) {
                    Result<List<Crew>> MovieResult = response.body();
                    if (MovieResult != null && MovieResult.getCode() == 200) {
                        data.setValue(MovieResult.getData());
                    } else {
                        data.setValue(null);
                    }
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<List<Crew>>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<Movie> getMovieByMId(int mid) {
        MutableLiveData<Movie> data = new MutableLiveData<>();
        movieApi.getMovieByMId(mid).enqueue(new Callback<Result<Movie>>() {
            @Override
            public void onResponse(@NonNull Call<Result<Movie>> call, @NonNull Response<Result<Movie>> response) {
                if (response.isSuccessful()) {
                    Result<Movie> MovieResult = response.body();
                    if (MovieResult != null && MovieResult.getCode() == 200) {
                        data.setValue(MovieResult.getData());
                    } else {
                        data.setValue(null);
                    }
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<Movie>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }

    public LiveData<List<Movie>> getMovieListByType(int tid) {
        MutableLiveData<List<Movie>> data = new MutableLiveData<>();
        movieApi.getMovieListByType(tid).enqueue(new Callback<Result<List<Movie>>>() {
            @Override
            public void onResponse(@NonNull Call<Result<List<Movie>>> call, @NonNull Response<Result<List<Movie>>> response) {
                if (response.isSuccessful()) {
                    Result<List<Movie>> MovieResult = response.body();
                    if (MovieResult != null && MovieResult.getCode() == 200) {
                        data.setValue(MovieResult.getData());
                    } else {
                        data.setValue(null);
                    }
                } else {
                    data.setValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<Result<List<Movie>>> call, @NonNull Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
