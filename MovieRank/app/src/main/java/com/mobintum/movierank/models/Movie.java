package com.mobintum.movierank.models;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Rick on 18/05/15.
 */
public class Movie {

    private String title;
    private int year;
    private String runtime;
    private Raiting raiting;
    private String synopsis;
    private String posterUrl;
    private Cast[] casting;

    public Movie(String title, int year, String runtime, Raiting raiting, String synopsis, String posterUrl, Cast[] casting) {
        this.title = title;
        this.year = year;
        this.runtime = runtime;
        this.raiting = raiting;
        this.synopsis = synopsis;
        this.posterUrl = posterUrl;
        this.casting = casting;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public Raiting getRaiting() {
        return raiting;
    }

    public void setRaiting(Raiting raiting) {
        this.raiting = raiting;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getPosterUrl() {
        return posterUrl;
    }

    public void setPosterUrl(String posterUrl) {
        this.posterUrl = posterUrl;
    }

    public Cast[] getCasting() {
        return casting;
    }

    public void setCasting(Cast[] casting) {
        this.casting = casting;
    }

    public static ArrayList<Movie> parseJSON(String response){

        ArrayList<Movie> movies = new ArrayList<Movie>();

        try {
            JSONObject responseJSON = new JSONObject(response);
            JSONArray moviesJSON = responseJSON.getJSONArray("movies");
            for(int i=0; i<moviesJSON.length();i++){
                JSONObject movie = moviesJSON.getJSONObject(i);

            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return movies;

    }

}
