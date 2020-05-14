package com.example.mvpdemo.features.detail;

import com.example.mvpdemo.api.APIService;
import com.example.mvpdemo.api.RetrofitConfiguration;
import com.example.mvpdemo.models.data_models.GetMovieAccountStatesResponse;
import com.example.mvpdemo.models.data_models.GetMovieDetailResponse;
import com.example.mvpdemo.models.share_pref.AccountSharePref;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieDetailPresenter implements MovieDetailContract.Presenter {

    MovieDetailContract.View view;
    AccountSharePref accountSharePref;
    APIService service;

    public MovieDetailPresenter(MovieDetailContract.View view, AccountSharePref accountSharePref) {
        this.view = view;
        this.accountSharePref = accountSharePref;
        this.service = RetrofitConfiguration.getInstance().create(APIService.class);
    }

    @Override
    public void getSessionId() {
        view.showFavouriteIcon(accountSharePref.getSessionId() != null);
    }

    @Override
    public void updateFavouriteMovie(int movieId, boolean isFavourite) {

    }

    @Override
    public void getMovieDetail(int movieId) {
        view.showLoadingIndicator();
        Call<GetMovieDetailResponse> call = service.getMovieDetail(movieId);
        call.enqueue(new Callback<GetMovieDetailResponse>() {
            @Override
            public void onResponse(Call<GetMovieDetailResponse> call, Response<GetMovieDetailResponse> response) {
                view.hideLoadingIndicator();
                if (response.code() == 200) {
                    view.showMovieDetail(response.body());
                    if (accountSharePref.getSessionId() != null) getMovieAccountStates(movieId);
                } else {
                    view.showErrorFromServer(response);
                    view.onBackPressed();
                }
            }

            @Override
            public void onFailure(Call<GetMovieDetailResponse> call, Throwable t) {
                view.hideLoadingIndicator();
                view.showErrorWhenFailure(t.toString());
            }
        });
    }

    private void getMovieAccountStates(int movieId) {
        Call<GetMovieAccountStatesResponse> call = service.getMovieAccountStates(
                movieId,
                accountSharePref.getSessionId()
        );
        call.enqueue(new Callback<GetMovieAccountStatesResponse>() {
            @Override
            public void onResponse(Call<GetMovieAccountStatesResponse> call, Response<GetMovieAccountStatesResponse> response) {
                view.hideLoadingIndicator();
                if (response.code() == 200) {
                    view.updateFavouriteIcon(response.body().isFavorite());
                } else {
                    view.showErrorFromServer(response);
                }
            }

            @Override
            public void onFailure(Call<GetMovieAccountStatesResponse> call, Throwable t) {
                view.hideLoadingIndicator();
                view.showErrorWhenFailure(t.toString());
            }
        });
    }
}
