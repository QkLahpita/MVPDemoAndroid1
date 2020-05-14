package com.example.mvpdemo.features.movies;

import android.os.Handler;

import com.example.mvpdemo.api.APIService;
import com.example.mvpdemo.api.RetrofitConfiguration;
import com.example.mvpdemo.models.GetMoviesResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

//2. create presenter
public class MoviesPresenter implements MoviesContract.Presenter {

    private MoviesContract.View view;

    public MoviesPresenter(MoviesContract.View view) {
        this.view = view;
    }

    @Override
    public void getMovies(int page) {
        view.showLoadingIndicator();
        APIService service = RetrofitConfiguration.getInstance().create(APIService.class);
        Call<GetMoviesResponse> call = service.getMovies(page);
        call.enqueue(new Callback<GetMoviesResponse>() {
            @Override
            public void onResponse(Call<GetMoviesResponse> call, Response<GetMoviesResponse> response) {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        view.setDataToRecyclerView(response.body().getResults());
                        view.hideLoadingIndicator();
                    }
                }, 2000);
            }

            @Override
            public void onFailure(Call<GetMoviesResponse> call, Throwable t) {
                view.showErrorToast(t.toString());
                view.hideLoadingIndicator();
            }
        });
    }
}
