package com.example.mvpdemo.features.account;

import android.content.Context;

public interface AccountContract {
    interface View {
        void showAccountSection();
        void showLoginSection();
    }

    interface Presenter {
        void getSessionId(Context context);
    }
}
