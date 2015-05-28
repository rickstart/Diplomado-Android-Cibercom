package com.mobintum.movierank.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.mobintum.movierank.R;
import com.mobintum.movierank.models.Cast;
import com.mobintum.movierank.models.Movie;
import com.mobintum.movierank.models.Raiting;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class DetailMovieFragment extends Fragment {

    private static final String ARG_PARAM_MOVIE = "paramMovie";
    private Movie movie;

    public static DetailMovieFragment newInstance(Movie movie) {
        DetailMovieFragment fragment = new DetailMovieFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_MOVIE,movie);
        fragment.setArguments(args);
        return fragment;
    }

    public DetailMovieFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.movie = (Movie) getArguments().getSerializable(ARG_PARAM_MOVIE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_movie, container, false);
        ImageView imgPoster = (ImageView) view.findViewById(R.id.imgPoster);
        TextView txtTitleDetail = (TextView) view.findViewById(R.id.txtTitleDetail);
        TextView txtRuntime = (TextView) view.findViewById(R.id.txtRuntime);
        TextView txtCasting = (TextView) view.findViewById(R.id.txtCasting);
        TextView txtSynopsis = (TextView) view.findViewById(R.id.txtSynopsis);
        RatingBar ratingCritic = (RatingBar) view.findViewById(R.id.ratingCritic);
        RatingBar ratingAudience = (RatingBar) view.findViewById(R.id.ratingAudience);

        Picasso.with(getActivity()).load(movie.getPosterUrl()).into(imgPoster);
        txtTitleDetail.setText(movie.getTitle());
        txtRuntime.setText(movie.getRuntime()+" min");
        ArrayList<Cast> casting = movie.getCasting();
        for (int i=0; i<casting.size();i++){
            if(i==0)
                txtCasting.setText(casting.get(i).getName());
            else
                txtCasting.append(" ,"+casting.get(i).getName());
        }
        txtSynopsis.setText(movie.getSynopsis());

        Raiting rating = movie.getRaiting();
        double score =  rating.getAudienceScore()*0.01*5;
        ratingAudience.setRating((float)score);
        score =  rating.getCriticScore()*0.01*5;
        ratingCritic.setRating((float)score);


        return view;
    }


}
