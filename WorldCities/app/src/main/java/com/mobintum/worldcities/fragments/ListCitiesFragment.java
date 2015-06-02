package com.mobintum.worldcities.fragments;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.mobintum.worldcities.R;
import com.mobintum.worldcities.adapters.ListCitiesAdapter;
import com.mobintum.worldcities.models.City;


public class ListCitiesFragment extends Fragment {

    private OnFragmentInteractionListener mListener;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_cities, container, false);
        ListView listCities = (ListView) view.findViewById(R.id.listCities);
        ListCitiesAdapter adapter = new ListCitiesAdapter(getActivity(),android.R.layout.simple_list_item_1,City.getData());
        listCities.setAdapter(adapter);
        listCities.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mListener.onCitySelected(City.getData().get(position));
            }
        });

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

        public void onCitySelected(City city);
    }

}
