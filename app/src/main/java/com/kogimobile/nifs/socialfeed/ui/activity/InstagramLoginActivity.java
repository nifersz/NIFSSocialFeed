package com.kogimobile.nifs.socialfeed.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.kogimobile.nifs.socialfeed.R;
import com.kogimobile.nifs.socialfeed.service.Access;

public class InstagramLoginActivity extends AppCompatActivity {

    private String mAuthUrl;

    private final static String TAG = "InstagramLoginActivity";

    private static final String CLIENT_ID = "8b594d532dd74b75981661269896600f";
    private static final String REDIRECT_URI = "http://localhost";
    private static final String AUTH_URL = "https://api.instagram.com/oauth/authorize/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instagram_login);

        mAuthUrl = AUTH_URL
            + "?client_id="
            + CLIENT_ID
            + "&redirect_uri="
            + REDIRECT_URI
            + "&response_type=token"
            + "&scope=public_content";

        WebView webView = (WebView) findViewById(R.id.webView_login);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new MyWebViewClient());
        webView.getSettings().setDomStorageEnabled(true);
        webView.loadUrl(mAuthUrl);
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view,
                                                String url) {
            if (url.startsWith(REDIRECT_URI)) {
                Log.d(TAG, "Instagram Token Retrieved: " + url);
                String urls[] = url.split("=");
                Access.INSTAGRAM = urls[1];

                Intent intent = new Intent();
                setResult(222, intent);
                finish();

                return false;
            } else {
                view.loadUrl(url);
                return true;
            }
        }

    }

}