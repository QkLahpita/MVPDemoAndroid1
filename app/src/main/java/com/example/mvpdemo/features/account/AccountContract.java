package com.example.mvpdemo.features.account;

public interface AccountContract {
    interface View {
        void showAccountSection();
        void showLoginSection();
    }

    interface Presenter {
        void getSessionId();
    }
}
