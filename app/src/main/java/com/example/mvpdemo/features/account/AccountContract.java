package com.example.mvpdemo.features.account;

import retrofit2.Response;

public interface AccountContract {
    interface View {
        void showAccountSection();
        void showLoginSection();
        void showLoadingIndicator();
        void hideLoadingIndicator();
        void showErrorFromServer(Response error);
        void showErrorWhenFailure(String message);
    }

    interface Presenter {
        void getSessionId();
        void signIn(String username, String password);
    }
}
