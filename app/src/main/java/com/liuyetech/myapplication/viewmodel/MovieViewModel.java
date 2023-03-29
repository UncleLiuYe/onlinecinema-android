package com.liuyetech.myapplication.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.liuyetech.myapplication.entity.Cast;
import com.liuyetech.myapplication.entity.Crew;
import com.liuyetech.myapplication.entity.Movie;
import com.liuyetech.myapplication.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {
    public LiveData<List<Movie>> getMovieList() {
        return MovieRepository.MOVIE_REPOSITORY.getMovieList();
    }

    public LiveData<List<Cast>> getMovieCastByMid(int mid) {
        return MovieRepository.MOVIE_REPOSITORY.getMovieCastByMid(mid);
    }

    public LiveData<List<Crew>> getMovieCrewByMid(int mid) {
        return MovieRepository.MOVIE_REPOSITORY.getMovieCrewByMid(mid);
    }

    public LiveData<Movie> getMovieByMId(int mid) {
        return MovieRepository.MOVIE_REPOSITORY.getMovieByMId(mid);
    }

    public LiveData<List<Movie>> getMovieListByType(int tid) {
        return MovieRepository.MOVIE_REPOSITORY.getMovieListByType(tid);
    }
}
