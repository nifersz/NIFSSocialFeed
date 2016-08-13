package com.kogimobile.nifs.socialfeed.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.fabric.sdk.android.Fabric;

import com.kogimobile.nifs.socialfeed.R;
import com.kogimobile.nifs.socialfeed.service.Access;
import com.kogimobile.nifs.socialfeed.ui.fragment.InstagramLoginFragment;
import com.kogimobile.nifs.socialfeed.ui.fragment.InstagramViewFragment;
import com.kogimobile.nifs.socialfeed.ui.fragment.IntroFragment;
import com.kogimobile.nifs.socialfeed.ui.fragment.LogoutFragment;
import com.kogimobile.nifs.socialfeed.ui.fragment.TweetsFragment;
import com.kogimobile.nifs.socialfeed.ui.fragment.TwitterLoginFragment;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawer;

    @BindView(R.id.nav_view)
    NavigationView mNavigationView;


    /** Number of the current active fragment. */
    private int mCurFragment;

    private static final String TWITTER_KEY = "qyVci3mNwvZYG0tw4UU52AOeY";
    private static final String TWITTER_SECRET = "aPRMgb2Ujyv7R6kGZXGmNU7qqKLlI0pRrOOXnG90cfY3zdqQZG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        if (savedInstanceState == null) {
            getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content_panel, new IntroFragment())
                .commit();
            mCurFragment = 0;
        }

        init();
    }

    @Override
    public void onBackPressed() {
        if (mDrawer.isDrawerOpen(GravityCompat.START)) {
            mDrawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Fragment fragment = getSupportFragmentManager().findFragmentByTag("TwitterLoginFragment");
        if (fragment != null) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
        Fragment fragment2 = getSupportFragmentManager().findFragmentByTag("InstagramLoginFragment");
        if (fragment2 != null) {
            fragment2.onActivityResult(requestCode, resultCode, data);
        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_camera:
                showFragment(1);
                break;
            case R.id.nav_send:
                showFragment(2);
                break;
            case R.id.nav_manage:
                showFragment(3);
                break;
        }

        mDrawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void init() {
        setSupportActionBar(mToolbar);

        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, mDrawer, mToolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close);
        mDrawer.setDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    private void showFragment(int nextFragmentNum) {
        String fragmentTag = "";
        Fragment nextFragment = null;

        if (mCurFragment == nextFragmentNum) {
            return;
        }

        switch (nextFragmentNum) {
            case 0:
                fragmentTag = "IntroFragment";
                nextFragment = new IntroFragment();
                mCurFragment = 0;
                break;
            case 1:
                fragmentTag = "InstagramLoginFragment";

                if (Access.INSTAGRAM == null) {
                    nextFragment = new InstagramLoginFragment();
                } else {
                    nextFragment = new InstagramViewFragment();
                }

                mCurFragment = 1;
                break;
            case 2:
                fragmentTag = "TwitterLoginFragment";

                if (Access.TWITTER == null) {
                    nextFragment = new TwitterLoginFragment();
                } else {
                    nextFragment = new TweetsFragment();
                }

                mCurFragment = 2;
                break;
            case 3:
                fragmentTag = "LogoutFragment";
                nextFragment = new LogoutFragment();
                mCurFragment = 3;
                break;
        }

        getSupportFragmentManager()
            .beginTransaction()
            .replace(R.id.content_panel, nextFragment, fragmentTag)
            .commit();
    }

}