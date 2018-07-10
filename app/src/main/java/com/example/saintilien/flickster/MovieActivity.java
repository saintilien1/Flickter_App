package com.example.saintilien.flickster;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.saintilien.flickster.adapters.MovieArrayAdapter;
import com.example.saintilien.flickster.models.movie;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class MovieActivity extends AppCompatActivity {
    ArrayList<movie> movies;
    MovieArrayAdapter movieAdapter;
    ListView lvMovies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);

        lvMovies =(ListView) findViewById(R.id.lvMovies);
        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this, movies);
        lvMovies.setAdapter(movieAdapter);

        String url="https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed";
        AsyncHttpClient client=new AsyncHttpClient();
        client.get(url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                JSONArray movieJsonResults = null;


                try{
                    movieJsonResults = response.getJSONArray("results");
                    movies.addAll(movie.fromJSONArray(movieJsonResults));
                    movieAdapter.notifyDataSetChanged();
                    Log.d("DEBUG",movies.toString());
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

        lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                final String title=movies.get(position).getOriginalTitle();
                final String overview =movies.get(position).getOverview();
                final String dater=movies.get(position).getRelease_date();
                final double vote=movies.get(position).getVote()/2;
                final String imagdet=movies.get(position).getPosterPath();
                Intent intent=new Intent(view.getContext(),activity_details_movie.class);
                intent.putExtra("TITLE",title);
                intent.putExtra("OVERVIEW",overview);
                intent.putExtra("DATE",dater);
                intent.putExtra("VOTE",vote);
                intent.putExtra("IMAGE",imagdet);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        movieAdapter.notifyDataSetChanged();
    }
}
