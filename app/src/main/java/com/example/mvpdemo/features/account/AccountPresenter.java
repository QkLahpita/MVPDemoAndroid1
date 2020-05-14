package com.example.mvpdemo.features.account;

import android.content.Context;
import android.view.View;

import com.example.mvpdemo.utils.Utils;

public class AccountPresenter implements AccountContract.Presenter {

    AccountContract.View view;

    public AccountPresenter(AccountContract.View view) {
        this.view = view;
    }

    @Override
    public void getSessionId(Context context) {
        if (Utils.getSessionId(context) == null) {
            view.showLoginSection();
        } else {
            view.showAccountSection();
        }
    }
}
