package com.kogimobile.nifs.socialfeed.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

import java.util.ArrayList;

import com.kogimobile.nifs.socialfeed.R;
import com.kogimobile.nifs.socialfeed.model.InstagramPhoto;
import com.squareup.picasso.Picasso;

public class InstagramPhotoAdapter extends RecyclerView.Adapter<InstagramPhotoAdapter.InstagramPhotoViewHolder> {

    private Context mCtxt;

    /** Layout inflater from activity context. */
    private LayoutInflater mInflater;

    /** Stores all items necessary to build the photo grid. */
    private ArrayList<InstagramPhoto> mUserPhotos;

    /** Tag for logs shown in this class. */
    private static final String TAG = "InstagramPhotoAdapter";


    public InstagramPhotoAdapter(Context ctxt) {
        if (ctxt == null) {
            Log.e(TAG, "There are not activity context for this adapter.");
            return;
        }

        mCtxt = ctxt;
        mInflater = LayoutInflater.from(ctxt);
        mUserPhotos = new ArrayList<>();
    }


    @Override
    public int getItemCount() {
        if (mUserPhotos == null) {
            Log.w(TAG, "Instagram photos reference is null.");
            return 0;
        }
        return mUserPhotos.size();
    }

    @Override
    public InstagramPhotoViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = mInflater.inflate(R.layout.card_instagram_photo, parent, false);
        InstagramPhotoViewHolder instagramPhotoVH = new InstagramPhotoViewHolder(view);
        return instagramPhotoVH;
    }

    @Override
    public void onBindViewHolder(InstagramPhotoViewHolder instaPhotoVH,
                                 int position) {
        InstagramPhoto instagramPhoto = mUserPhotos.get(position);
        String instagramPhotoUrl = instagramPhoto.getUrl();

        if (instagramPhotoUrl != null) {
            instaPhotoVH.setPhoto(instagramPhotoUrl);
        } else {
            instaPhotoVH.setDefaultImage();
        }
    }

    public void addPhoto(InstagramPhoto instagramPhoto) {
        mUserPhotos.add(instagramPhoto);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addAll(ArrayList<InstagramPhoto> instagramPhotos) {
        if (instagramPhotos == null) {
            Log.e(TAG, "Instagram photos can not be null.");
            return;
        }

        mUserPhotos.addAll(instagramPhotos);
        notifyDataSetChanged();
    }


    public class InstagramPhotoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image_instagram_photo)
        ImageView photo;

        InstagramPhotoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void setDefaultImage() {
            Picasso.with(mCtxt)
                   .load(R.drawable.instagram_default)
                   .into(photo);
        }

        public void setPhoto(String urlImage){
            Picasso.with(mCtxt)
                   .load(urlImage)
                   .placeholder(R.drawable.instagram_default)
                   .into(photo);
        }

    }

}