package com.kogimobile.nifs.socialfeed.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kogimobile.nifs.socialfeed.R;
import com.kogimobile.nifs.socialfeed.service.Access;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterLoginButton;

public class TwitterLoginFragment extends Fragment {

    private TwitterLoginButton mBtnTwitterLogin;

    private TwitterSession session;

    private final static String TAG = "TwitterLoginFragment";


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_twitter_login,
                                         container,
                                         false);

        mBtnTwitterLogin = (TwitterLoginButton) rootView.findViewById(R.id.btn_twitter_login);
        mBtnTwitterLogin.setCallback(new Callback<TwitterSession>() {
            @Override
            public void success(Result<TwitterSession> result) {
                session = result.data;
                Access.TWITTER = session.getAuthToken().token;
                Access.TWITTER_USER_ID = session.getUserId();
                showTweets();
            }
            @Override
            public void failure(TwitterException exception) {
                Log.e(TAG, "Login with Twitter failure.", exception);
            }
        });

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode,
                                 Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mBtnTwitterLogin.onActivityResult(requestCode, resultCode, data);
    }

    private void showTweets() {
        getFragmentManager()
            .beginTransaction()
            .replace(R.id.content_panel, new TweetsFragment())
            .commit();
    }

}