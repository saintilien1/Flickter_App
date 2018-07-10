package com.example.saintilien.flickster.adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.saintilien.flickster.R;
import com.example.saintilien.flickster.models.movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieArrayAdapter extends ArrayAdapter<movie> {
    public  static class ViewHolder
    {
        TextView tvOriginalTitle;

        ImageView ivImage;

    }

    public MovieArrayAdapter(Context context, List<movie> movies){
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        movie movie =getItem(position);
        final ViewHolder viewholder;

        //Check if existing
        if(convertView ==null){
            viewholder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView= inflater.inflate(R.layout.item_movie, parent , false);

           //find imageview
            viewholder.ivImage = (ImageView) convertView.findViewById(R.id.idMoviesImage);
            viewholder.ivImage.setImageResource(0);

            viewholder.tvOriginalTitle = (TextView) convertView.findViewById(R.id.tvTitle);

            convertView.setTag(viewholder);
            //return view
          }else{
            viewholder = (ViewHolder) convertView.getTag();
        }
        viewholder.ivImage.setImageResource(0);
        //populate data
        viewholder.tvOriginalTitle.setText(movie.getOriginalTitle());


        int orientation = getContext().getResources().getConfiguration().orientation;
        if(orientation == Configuration.ORIENTATION_PORTRAIT){
            Picasso.with(getContext()).load(movie.getPosterPath()).into(viewholder.ivImage);
        }else if(orientation==Configuration.ORIENTATION_LANDSCAPE){
            Picasso.with(getContext()).load(movie.getPosterPath()).into(viewholder.ivImage);
        }
        return convertView;
    }
}
