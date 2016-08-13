package com.kogimobile.nifs.socialfeed.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kogimobile.nifs.socialfeed.ui.activity.MainActivity;
import com.kogimobile.nifs.socialfeed.R;

public class SplashFragment extends Fragment {

    private final static long SPLASH_TIME_OUT = 1500; // mls

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_splash, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    if (getActivity() != null) {
                        startActivity(new Intent(getActivity(), MainActivity.class));
                    }
                }
            }, SPLASH_TIME_OUT);
        }
    }

}