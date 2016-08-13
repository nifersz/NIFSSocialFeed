package com.kogimobile.nifs.socialfeed.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import retrofit2.Call;
import retrofit2.Response;

import com.kogimobile.nifs.socialfeed.R;
import com.kogimobile.nifs.socialfeed.model.TwitterTweet;
import com.kogimobile.nifs.socialfeed.service.Access;
import com.kogimobile.nifs.socialfeed.ui.adapter.TwitterAdapter;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.List;

public class TweetsFragment extends Fragment {

    @BindView(R.id.rcvw_tweets)
    RecyclerView mRecyclerView;

    private Unbinder mUnbinder;

    private TwitterAdapter mTweetsAdapter;

    private static final String TAG = "TweetsFragment";


    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tweets,
                                         container, false);
        mUnbinder = ButterKnife.bind(this, rootView);

        // Instagram photos list RecyclerView/Adapter
        mTweetsAdapter = new TwitterAdapter(getActivity());
        mRecyclerView.setAdapter(mTweetsAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        loadTweets();

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    public void loadTweets() {
        Twitter.getApiClient()
               .getStatusesService()
               .userTimeline(Access.TWITTER_USER_ID, null, null, null ,null, null, null, null, null)
               .enqueue(new retrofit2.Callback<List<Tweet>>() {
            @Override
            public void onResponse(Call<List<Tweet>> call,
                                   Response<List<Tweet>> response) {
                ArrayList<TwitterTweet> tweetsList = new ArrayList<>();

                for (Tweet tweet : response.body()) {
                    TwitterTweet newTweet = new TwitterTweet();

                    newTweet.setUsername(tweet.user.name);
                    newTweet.setUserID("@" + tweet.user.screenName);
                    newTweet.setContent(tweet.text);
                    newTweet.setRetweets(tweet.retweetCount);
                    newTweet.setFavorites(tweet.favoriteCount);

                    tweetsList.add(newTweet);
                }

                mTweetsAdapter.addAll(tweetsList);
            }

            @Override
            public void onFailure(Call<List<Tweet>> call,
                                  Throwable t) {
                Log.e(TAG, t.getLocalizedMessage());
            }
        });
    }

}