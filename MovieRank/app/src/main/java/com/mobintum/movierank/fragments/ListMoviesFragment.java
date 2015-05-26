package com.mobintum.movierank.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.mobintum.movierank.R;
import com.mobintum.movierank.models.Movie;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;


public class ListMoviesFragment extends Fragment {

    public static final String API_KEY = "35hg37n2zaybbwf7wncj9vgw";
    private String query ="friends";
    private ArrayList<Movie> movies = new ArrayList<Movie>();

    private OnFragmentInteractionListener mListener;

    Uri.Builder builder = new Uri.Builder();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        builder.scheme("http")
                .authority("api.rottentomatoes.com")
                .appendPath("api")
                .appendPath("public")
                .appendPath("v1.0")
                .appendPath("movies.json")
                .appendQueryParameter("apikey",API_KEY)
                .appendQueryParameter("q",query);

        String url = builder.build().toString();

        Log.e("URL", url);
        new RottenRequest().execute(url);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_movies, container, false);

        ListView listMovies = (ListView) view.findViewById(R.id.listMovies);


        return view;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {

        public void onMovieSelected(Movie movie);
    }

    public class RottenRequest extends AsyncTask<String,Void,String>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpClient httpClient = new DefaultHttpClient();
            HttpResponse httpResponse;
            String response;

            try{
                httpResponse = httpClient.execute(new HttpGet(params[0]));
                StatusLine statusLine = httpResponse.getStatusLine();
                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    httpResponse.getEntity().writeTo(out);
                    out.close();
                    response = out.toString();
                    return response;
                }else{
                    return null;
                }


            }catch (Exception e){
                e.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            if(result!=null)
                Log.e("RESPONSE",result);
                movies = Movie.parseJSON(result);

        }

    }

}
