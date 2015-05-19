package com.mobintum.movierank.models;

/**
 * Created by Rick on 18/05/15.
 */
public class Raiting {

    private int criticScore;
    private int audienceScore;

    public Raiting(int criticScore, int audienceScore) {
        this.criticScore = criticScore;
        this.audienceScore = audienceScore;
    }

    public int getCriticScore() {
        return criticScore;
    }

    public void setCriticScore(int criticScore) {
        this.criticScore = criticScore;
    }

    public int getAudienceScore() {
        return audienceScore;
    }

    public void setAudienceScore(int audienceScore) {
        this.audienceScore = audienceScore;
    }
}
