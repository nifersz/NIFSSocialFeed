package com.kogimobile.nifs.socialfeed.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.kogimobile.nifs.socialfeed.R;
import com.kogimobile.nifs.socialfeed.ui.adapter.InstagramStatePagerAdapter;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class InstagramViewFragment extends Fragment {

    @BindView(R.id.vpager_instagram)
    ViewPager mViewPager;

    @BindView(R.id.button_instagram_mypictures)
    Button mBtnMyPictures;

    @BindView(R.id.button_instagram_myprofile)
    Button mBtnMyProfile;

    @BindColor(R.color.disabled)
    int disabledColor;

    @BindColor(R.color.colorPrimary)
    int primaryColor;

    private InstagramStatePagerAdapter mInstagramPagerAdapter;

    private int mCurrentPage;

    private Unbinder mUnbinder;

    private static final String TAG = "InstagramViewFragment";


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_instagram_view,
                                         container,
                                         false);
        mUnbinder = ButterKnife.bind(this, rootView);

        init();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }


    private void init() {
        mInstagramPagerAdapter = new InstagramStatePagerAdapter(
            getChildFragmentManager(),
            new Fragment[] {
                new InstagramPicturesFragment(),
                new InstagramProfileFragment(),
            }
        );
        mViewPager.setAdapter(mInstagramPagerAdapter);
        mViewPager.setCurrentItem(mCurrentPage);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            public void onPageScrollStateChanged(int state) {}
            public void onPageScrolled(int pos, float posOffset, int posOffsetPixels) {}

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        mBtnMyProfile.setTextColor(disabledColor);
                        mBtnMyPictures.setTextColor(primaryColor);
                        break;
                    case 1:
                        mBtnMyProfile.setTextColor(primaryColor);
                        mBtnMyPictures.setTextColor(disabledColor);
                        break;
                    default: return;
                }
            }
        });

        mBtnMyPictures.setOnClickListener((View v) -> {
            moveToPage(0);
        });
        mBtnMyProfile.setOnClickListener((View v) -> {
            moveToPage(1);
        });
    }

    private void moveToPage(int pageIndex) {
        mCurrentPage = pageIndex;
        mViewPager.setCurrentItem(mCurrentPage, true);
    }

}