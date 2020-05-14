package com.example.mvpdemo.features.account;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mvpdemo.BaseFragment;
import com.example.mvpdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends BaseFragment implements AccountContract.View {

    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_sign_in)
    TextView tvSignIn;
    @BindView(R.id.ll_sign_in)
    LinearLayout llSignIn;
    @BindView(R.id.tv_sign_out)
    TextView tvSignOut;
    @BindView(R.id.ll_account)
    LinearLayout llAccount;
    @BindView(R.id.ll_loading)
    LinearLayout llLoading;

    private AccountPresenter accountPresenter;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    protected void onViewCreated(View rootView) {
        accountPresenter = new AccountPresenter(this);
        accountPresenter.getSessionId(getContext());
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_account;
    }

    @OnClick({R.id.tv_sign_in, R.id.tv_sign_out})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_sign_in:
                break;
            case R.id.tv_sign_out:
                break;
        }
    }

    @Override
    public void showAccountSection() {
        llAccount.setVisibility(View.VISIBLE);
        llSignIn.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showLoginSection() {
        llAccount.setVisibility(View.INVISIBLE);
        llSignIn.setVisibility(View.VISIBLE);
    }
}
