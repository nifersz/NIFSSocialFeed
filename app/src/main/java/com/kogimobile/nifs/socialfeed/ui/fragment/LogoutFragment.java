package com.kogimobile.nifs.socialfeed.ui.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.kogimobile.nifs.socialfeed.R;
import com.kogimobile.nifs.socialfeed.service.Access;
import com.twitter.sdk.android.Twitter;

public class LogoutFragment extends Fragment {

    @BindView(R.id.btn_instagram_logout)
    Button mBtnInstagramLogout;

    @BindView(R.id.btn_twitter_logout)
    Button mBtnTwitterLogout;

    private Unbinder mUnbinder;


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_logouts,
                                         container, false);
        mUnbinder = ButterKnife.bind(this, rootView);

        mBtnInstagramLogout.setEnabled(Access.INSTAGRAM != null);
        mBtnInstagramLogout.setOnClickListener((View v) -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
                CookieManager.getInstance().removeAllCookies(null);
                CookieManager.getInstance().flush();
            } else {
                CookieSyncManager cookieSyncMngr = CookieSyncManager.createInstance(getActivity());
                cookieSyncMngr.startSync();
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.removeAllCookie();
                cookieManager.removeSessionCookie();
                cookieSyncMngr.stopSync();
                cookieSyncMngr.sync();
            }

            Access.INSTAGRAM = null;
            mBtnInstagramLogout.setEnabled(false);
        });
        mBtnTwitterLogout.setEnabled(Access.TWITTER != null);
        mBtnTwitterLogout.setOnClickListener((View v) -> {
            CookieSyncManager.createInstance(getActivity());
            CookieManager cookieManager = CookieManager.getInstance();
            cookieManager.removeSessionCookie();
            Twitter.getSessionManager().clearActiveSession();
            Twitter.logOut();

            Access.TWITTER = null;
            Access.TWITTER_USER_ID = 0;
            mBtnTwitterLogout.setEnabled(false);
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

}