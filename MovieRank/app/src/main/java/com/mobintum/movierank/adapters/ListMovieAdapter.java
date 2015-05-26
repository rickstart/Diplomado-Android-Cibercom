package com.mobintum.movierank.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobintum.movierank.R;
import com.mobintum.movierank.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Rick on 25/05/15.
 */
public class ListMovieAdapter extends ArrayAdapter {

    private ArrayList<Movie> movies;
    private Context context;
    private int resource;
    private LayoutInflater inflater;

    public ListMovieAdapter(Context context, int resource, ArrayList<Movie> movies) {
        super(context, resource, movies);
        this.movies = movies;
        this.context = context;
        this.resource = resource;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(resource,parent,false);
            holder.imgThumbnail = (ImageView) convertView.findViewById(R.id.imgThumbnail);
            holder.txtTitle = (TextView) convertView.findViewById(R.id.txtTitle);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        Movie movie = movies.get(position);
        holder.txtTitle.setText(movie.getTitle());
        Picasso.with(context).load(movie.getPosterUrl()).into(holder.imgThumbnail);

        return convertView;
    }

    class ViewHolder{

        ImageView imgThumbnail;
        TextView txtTitle;
    }
}
