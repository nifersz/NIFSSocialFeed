package com.kogimobile.nifs.socialfeed.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.kogimobile.nifs.socialfeed.R;
import com.kogimobile.nifs.socialfeed.ui.fragment.SplashFragment;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        // Hide the status bar
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                .add(R.id.splash_container, new SplashFragment())
                .commit();
        }
    }

}