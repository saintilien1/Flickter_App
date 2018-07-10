package com.example.saintilien.flickster.models;

import android.graphics.Movie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class movie {
    String posterPath;
    String originalTitle;
    String overview;

    String backdropPath;
    double vote_average;
    String release_date;

    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }
    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getOverview() {
        return overview;
    }
    public double getVote() {
        return vote_average;
    }
    public String getRelease_date() {
        return "Release Date :"+release_date;
    }


    public movie(JSONObject jsonObject) throws JSONException{
        this.posterPath = jsonObject.getString("poster_path");
        this.originalTitle = jsonObject.getString("original_title");
        this.overview = jsonObject.getString("overview");
        this.backdropPath = jsonObject.getString("backdrop_path");
        this.vote_average=jsonObject.getDouble("vote_average");
        this.release_date=jsonObject.getString("release_date");
    }

    public static ArrayList<movie> fromJSONArray(JSONArray array){
        ArrayList<movie> results = new ArrayList<>();
        for(int x = 0; x < array.length(); x++){
            try {
                results.add(new movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
