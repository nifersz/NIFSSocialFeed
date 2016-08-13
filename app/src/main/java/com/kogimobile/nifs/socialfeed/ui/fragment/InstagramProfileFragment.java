package com.kogimobile.nifs.socialfeed.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kogimobile.nifs.socialfeed.R;
import com.kogimobile.nifs.socialfeed.model.InstagramPhoto;
import com.kogimobile.nifs.socialfeed.model.instagram.Counts;
import com.kogimobile.nifs.socialfeed.model.instagram.Data;
import com.kogimobile.nifs.socialfeed.model.instagram.InstagramResponse;
import com.kogimobile.nifs.socialfeed.model.instagram.InstagramUser;
import com.kogimobile.nifs.socialfeed.service.Access;
import com.kogimobile.nifs.socialfeed.service.RetrofitManager;
import com.kogimobile.nifs.socialfeed.ui.ItemOffsetDecoration;
import com.kogimobile.nifs.socialfeed.ui.adapter.InstagramPhotoAdapter;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstagramProfileFragment extends Fragment {

    @BindView(R.id.image_instagram_profilepic)
    ImageView mImgvProfilePic;

    @BindView(R.id.text_instagram_username)
    TextView mTxtvUsername;

    @BindView(R.id.text_instagram_name)
    TextView mTxtvName;

    @BindView(R.id.text_instagram_posts)
    TextView mTxtvPostNum;

    @BindView(R.id.text_instagram_followers)
    TextView mTxtvFollowersNum;

    @BindView(R.id.text_instagram_following)
    TextView mTxtvFollowingNum;

    @BindView(R.id.rcvw_profile_pictures_grid)
    RecyclerView mRecyclerView;

    private Unbinder mUnbinder;

    private InstagramPhotoAdapter mPicturesAdapter;

    private static final int GRID_COLUMNS = 3;

    private static final String TAG = "InstagramProfileFrag";


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_instagram_profile,
                                         container,
                                         false);
        mUnbinder = ButterKnife.bind(this, rootView);

        // Instagram photos list RecyclerView/Adapter
        mPicturesAdapter = new InstagramPhotoAdapter(getActivity());
        mRecyclerView.setAdapter(mPicturesAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), GRID_COLUMNS));
        mRecyclerView.addItemDecoration(new ItemOffsetDecoration(getActivity(), R.dimen.grid_item_offset));

        loadLikedImages();
        loadUserInfo();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    public void loadUserInfo() {
        RetrofitManager.getInstance()
        .getInstagramApi().getUserInfo(Access.INSTAGRAM)
        .enqueue(new Callback<InstagramUser>() {
            @Override
            public void onResponse(Call<InstagramUser> call,
                                   Response<InstagramUser> response) {
                if (response.body() != null) {
                    InstagramUser instagramResponse = response.body();

                    Data userData = instagramResponse.getData();
                    Counts userCounts = instagramResponse.getData().getCounts();

                    mTxtvUsername.setText(userData.getUsername());
                    mTxtvName.setText(userData.getFullName());
                    Picasso.with(getActivity()).load(userData.getProfilePicture()).into(mImgvProfilePic);
                    mTxtvPostNum.setText(String.valueOf(userCounts.getMedia()));
                    mTxtvFollowersNum.setText(String.valueOf(userCounts.getFollowedBy()));
                    mTxtvFollowingNum.setText(String.valueOf(userCounts.getFollows()));
                }
            }

            @Override
            public void onFailure(Call<InstagramUser> call, Throwable t) {
                Log.e(TAG, "Request error: " + t.getLocalizedMessage());
            }
        });
    }

    public void loadLikedImages() {
        RetrofitManager.getInstance()
        .getInstagramApi().getLikedPictures(Access.INSTAGRAM)
        .enqueue(new Callback<InstagramResponse>() {
            @Override
            public void onResponse(Call<InstagramResponse> call,
                                   Response<InstagramResponse> response) {

                if (response.body() != null) {
                    InstagramResponse instagramResponse = response.body();
                    ArrayList<InstagramPhoto> photosList = new ArrayList<>();

                    for (Data data: instagramResponse.getData()) {
                        String imgUrl = data.getImages().getThumbnail().getUrl();
                        photosList.add(new InstagramPhoto(imgUrl));
                    }

                    mPicturesAdapter.addAll(photosList);
                }

            }

            @Override
            public void onFailure(Call<InstagramResponse> call,
                                  Throwable t) {
                Log.e(TAG, "Request error: " + t.getLocalizedMessage());
            }
        });
    }

}