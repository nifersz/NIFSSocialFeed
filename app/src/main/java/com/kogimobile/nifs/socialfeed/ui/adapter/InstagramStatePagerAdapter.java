package com.kogimobile.nifs.socialfeed.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class InstagramStatePagerAdapter extends FragmentStatePagerAdapter {

    private Fragment[] mFragments;

    public InstagramStatePagerAdapter(FragmentManager fm,
                                      Fragment[] wizardFragments) {
        super(fm);
        mFragments = wizardFragments;
    } // end : constructor


    @Override
    public Fragment getItem(int position) {
        return mFragments[position];
    }

    @Override
    public int getCount() {
        if (mFragments == null) {
            return 0;
        } else {
            return mFragments.length;
        }
    }

}