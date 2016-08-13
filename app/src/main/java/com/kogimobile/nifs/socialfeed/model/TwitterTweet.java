package com.kogimobile.nifs.socialfeed.model;

public class TwitterTweet {

    private String userID;
    private String username;
    private String content;
    private int retweets;
    private int favorites;


    public TwitterTweet() {}

    public TwitterTweet(String userID,
                        String username,
                        String content,
                        int retweets,
                        int favorites) {
        this.userID = userID;
        this.username = username;
        this.content = content;
        this.retweets = retweets;
        this.favorites = favorites;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getRetweets() {
        return retweets;
    }

    public void setRetweets(int retweets) {
        this.retweets = retweets;
    }

    public int getFavorites() {
        return favorites;
    }

    public void setFavorites(int favorites) {
        this.favorites = favorites;
    }

}