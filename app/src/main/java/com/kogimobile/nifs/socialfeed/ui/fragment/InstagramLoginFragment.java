package com.kogimobile.nifs.socialfeed.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.kogimobile.nifs.socialfeed.R;
import com.kogimobile.nifs.socialfeed.ui.activity.InstagramLoginActivity;

public class InstagramLoginFragment extends Fragment {

    @BindView(R.id.btn_instagram_login)
    Button mBtnInstagramLogin;

    private Unbinder mUnbinder;

    private final static String TAG = "InstagramLoginFragment";

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_instagram_login,
            container,
            false);
        mUnbinder = ButterKnife.bind(this, rootView);

        mBtnInstagramLogin.setOnClickListener((View view) -> {
            loginToInstagram();
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 222) {
            getFragmentManager()
                .beginTransaction()
                .replace(R.id.content_panel, new InstagramViewFragment())
                .commit();
        }
    }

    private void loginToInstagram() {
        Intent myIntent = new Intent(getActivity(), InstagramLoginActivity.class);
        startActivityForResult(myIntent, 222);
    }

}