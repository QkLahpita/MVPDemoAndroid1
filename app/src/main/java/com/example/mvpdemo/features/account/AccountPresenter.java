package com.example.mvpdemo.features.account;

import com.example.mvpdemo.models.share_pref.AccountSharePref;

public class AccountPresenter implements AccountContract.Presenter {

    AccountContract.View view;
    AccountSharePref accountSharePref;

    public AccountPresenter(AccountContract.View view, AccountSharePref accountSharePref) {
        this.view = view;
        this.accountSharePref = accountSharePref;
    }

    @Override
    public void getSessionId() {
        if (accountSharePref.getSessionId() == null) {
            view.showLoginSection();
        } else {
            view.showAccountSection();
        }
    }
}
