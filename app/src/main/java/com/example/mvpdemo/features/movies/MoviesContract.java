package com.example.mvpdemo.features.movies;

import com.example.mvpdemo.models.GetMoviesResponse;

import java.util.List;

//1. create contract
public interface MoviesContract {
    interface View {
        void setDataToRecyclerView(List<GetMoviesResponse.ResultsBean> movies);
        void showErrorToast(String error);
    }

    interface Presenter {
        void getMovies(int page);
    }
}