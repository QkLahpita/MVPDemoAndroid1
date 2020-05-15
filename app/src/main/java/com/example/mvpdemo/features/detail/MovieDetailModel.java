package com.example.mvpdemo.features.detail;

import com.example.mvpdemo.api.APIService;
import com.example.mvpdemo.models.data_models.SetFavouriteMovieRequest;
import com.example.mvpdemo.models.data_models.SetFavouriteMovieResponse;
import com.example.mvpdemo.models.share_pref.AccountSharePref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailModel implements MovieDetailContract.Model {

    AccountSharePref accountSharePref;
    APIService service;

    public MovieDetailModel(AccountSharePref accountSharePref, APIService service) {
        this.accountSharePref = accountSharePref;
        this.service = service;
    }

    @Override
    public void updateFavouriteMovie(OnFinishUpdateFavouriteMovie onFinishUpdateFavouriteMovie, int movieId, boolean isFavourite) {
        SetFavouriteMovieRequest body = new SetFavouriteMovieRequest(movieId, !isFavourite);
        Call<SetFavouriteMovieResponse> call = service.setFavouriteMovie(body, accountSharePref.getSessionId());
        call.enqueue(new Callback<SetFavouriteMovieResponse>() {
            @Override
            public void onResponse(Call<SetFavouriteMovieResponse> call, Response<SetFavouriteMovieResponse> response) {
                onFinishUpdateFavouriteMovie.onResponseUpdateFavouriteMovie(
                                response.code() == 200 || response.code() == 201,
                                response,
                                isFavourite);
            }

            @Override
            public void onFailure(Call<SetFavouriteMovieResponse> call, Throwable t) {
                onFinishUpdateFavouriteMovie.onFailureUpdateFavouriteMovie(t.toString());
            }
        });
    }
}
