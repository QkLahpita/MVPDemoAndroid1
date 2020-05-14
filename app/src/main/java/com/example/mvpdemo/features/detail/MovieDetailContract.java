package com.example.mvpdemo.features.detail;

import com.example.mvpdemo.models.data_models.GetMovieDetailResponse;

import retrofit2.Response;

public interface MovieDetailContract {
    interface View {
        void showFavouriteIcon(boolean isShown);
        void showLoadingIndicator();
        void hideLoadingIndicator();
        void showErrorFromServer(Response error);
        void showErrorWhenFailure(String message);
        void showMovieDetail(GetMovieDetailResponse movie);
        void updateFavouriteIcon(boolean isFavourite);
        void onBackPressed();
    }

    interface Presenter {
        void getSessionId();
        void updateFavouriteMovie(int movieId, boolean isFavourite);
        void getMovieDetail(int movieId);
    }
}
