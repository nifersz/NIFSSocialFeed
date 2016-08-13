package com.kogimobile.nifs.socialfeed.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import com.kogimobile.nifs.socialfeed.R;
import com.kogimobile.nifs.socialfeed.model.InstagramPhoto;
import com.kogimobile.nifs.socialfeed.model.instagram.Data;
import com.kogimobile.nifs.socialfeed.model.instagram.InstagramResponse;
import com.kogimobile.nifs.socialfeed.service.Access;
import com.kogimobile.nifs.socialfeed.service.RetrofitManager;
import com.kogimobile.nifs.socialfeed.ui.ItemOffsetDecoration;
import com.kogimobile.nifs.socialfeed.ui.adapter.InstagramPhotoAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InstagramPicturesFragment extends Fragment {

    @BindView(R.id.rcvw_pictures_grid)
    RecyclerView mRecyclerView;

    private Unbinder mUnbinder;

    private InstagramPhotoAdapter mPicturesAdapter;

    private static final int GRID_COLUMNS = 3;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_instagram_pictures,
                                         container, false);
        mUnbinder = ButterKnife.bind(this, rootView);

        // Instagram photos list RecyclerView/Adapter
        mPicturesAdapter = new InstagramPhotoAdapter(getActivity());
        mRecyclerView.setAdapter(mPicturesAdapter);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), GRID_COLUMNS));
        mRecyclerView.addItemDecoration(new ItemOffsetDecoration(getActivity(), R.dimen.grid_item_offset));

        loadRecentImages();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    public void loadRecentImages() {
        RetrofitManager.getInstance()
        .getInstagramApi().getRecentPictures(Access.INSTAGRAM)
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
            public void onFailure(Call<InstagramResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Error Image Download",Toast.LENGTH_LONG).show();
            }
        });
    }

}