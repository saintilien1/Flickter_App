package com.example.saintilien.flickster;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.saintilien.flickster.R;

public class activity_details_movie extends AppCompatActivity {
    TextView titleDetails;
    TextView overviewDetails;
    RatingBar rbmovie;
    TextView datemov;
    ImageView imgDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_movie);

        titleDetails=(TextView)findViewById(R.id.tvTitleDetails);
        overviewDetails=(TextView)findViewById(R.id.tvOverviewDetails);
        imgDetails=(ImageView)findViewById(R.id.ivDetailsImage);
        rbmovie=(RatingBar)findViewById(R.id.rbmovie);
        datemov=(TextView)findViewById(R.id.tvdate);

        Intent i =this.getIntent();
        String tit=i.getExtras().getString("TITLE");
        String over=i.getExtras().getString("OVERVIEW");
        String dat=i.getExtras().getString("DATE");
        float vote=i.getExtras().getFloat("VOTE");


        titleDetails.setText(tit);
        overviewDetails.setText(over);
        imgDetails.setImageResource(0);
        rbmovie.setRating(vote);
        datemov.setText(dat);
    }
}
