package com.mobintum.worldcities.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobintum.worldcities.R;
import com.mobintum.worldcities.models.City;
import com.squareup.picasso.Picasso;

public class CityFragment extends Fragment {

    private static final String ARG_PARAM_CITY = "paramCity";

    private City city;
    private GoogleMap gMap;

    public static CityFragment newInstance(City city) {
        CityFragment fragment = new CityFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_CITY, city);
        fragment.setArguments(args);
        return fragment;
    }

    public CityFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.city = (City) getArguments().getSerializable(ARG_PARAM_CITY);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_city, container, false);
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        gMap = mapFragment.getMap();
        gMap.addMarker(new MarkerOptions().position(new LatLng(city.getLatitude(),city.getLongitude())).title(city.getName()));
        gMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(city.getLatitude(),city.getLongitude()),10));
        gMap.setMyLocationEnabled(true);
        gMap.setBuildingsEnabled(true);

        TextView txtNameCity = (TextView) view.findViewById(R.id.txtNameCity);
        txtNameCity.setText(city.getName());

        ImageView imgCity = (ImageView) view.findViewById(R.id.imgCity);

        Picasso.with(getActivity()).load(city.getPicUrl()).into(imgCity);


        return view;
    }

}
