package com.kogimobile.nifs.socialfeed.model.instagram;

import com.google.gson.annotations.SerializedName;

public class Counts {

    private Integer media;

    @SerializedName("followed_by")
    private Integer followedBy;

    private Integer follows;

    public Integer getMedia() {
        return media;
    }

    public void setMedia(Integer media) {
        this.media = media;
    }

    public Integer getFollowedBy() {
        return followedBy;
    }

    public void setFollowedBy(Integer followedBy) {
        this.followedBy = followedBy;
    }

    public Integer getFollows() {
        return follows;
    }

    public void setFollows(Integer follows) {
        this.follows = follows;
    }
}