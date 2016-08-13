package com.kogimobile.nifs.socialfeed.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.kogimobile.nifs.socialfeed.R;
import com.kogimobile.nifs.socialfeed.model.TwitterTweet;

import java.util.ArrayList;

public class TwitterAdapter extends RecyclerView.Adapter<TwitterAdapter.TweetViewHolder> {

    private Context mCtxt;

    /** Layout inflater from activity context. */
    private LayoutInflater mInflater;

    /** Stores all items necessary to build the photo grid. */
    private ArrayList<TwitterTweet> mUserTweets;

    /** Tag for logs shown in this class. */
    private static final String TAG = "TwitterAdapter";


    public TwitterAdapter(Context ctxt) {
        if (ctxt == null) {
            Log.e(TAG, "There are not activity context for this adapter.");
            return;
        }

        mCtxt = ctxt;
        mInflater = LayoutInflater.from(ctxt);
        mUserTweets = new ArrayList<>();
    }

    @Override
    public int getItemCount() {
        if (mUserTweets == null) {
            Log.w(TAG, "Tweets reference is null.");
            return 0;
        }
        return mUserTweets.size();
    }

    @Override
    public TweetViewHolder onCreateViewHolder(ViewGroup parent, int position) {
        View view = mInflater.inflate(R.layout.card_tweet, parent, false);
        TweetViewHolder tweetVH = new TweetViewHolder(view);
        return tweetVH;
    }

    @Override
    public void onBindViewHolder(TweetViewHolder tweetVH,
                                 int position) {
        TwitterTweet tweet = mUserTweets.get(position);

        tweetVH.userID.setText(tweet.getUserID());
        tweetVH.username.setText(tweet.getUsername());
        tweetVH.content.setText(tweet.getContent());
        tweetVH.retweets.setText(String.valueOf(tweet.getRetweets()));
        tweetVH.favorites.setText(String.valueOf(tweet.getFavorites()));
    }

    public void addTweet(TwitterTweet tweet) {
        mUserTweets.add(tweet);
        notifyItemInserted(getItemCount() - 1);
    }

    public void addAll(ArrayList<TwitterTweet> tweets) {
        if (tweets == null) {
            Log.e(TAG, "New tweets can not be null.");
            return;
        }

        mUserTweets.addAll(tweets);
        notifyDataSetChanged();
    }


    public class TweetViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_tweet_userid)
        TextView userID;

        @BindView(R.id.text_tweet_username)
        TextView username;

        @BindView(R.id.text_tweet_content)
        TextView content;

        @BindView(R.id.text_retweets_number)
        TextView retweets;

        @BindView(R.id.text_tweet_favs_number)
        TextView favorites;

        TweetViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }

}