package com.mobintum.worldcities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.mobintum.worldcities.models.City;

import java.util.ArrayList;

/**
 * Created by Rick on 01/06/15.
 */
public class ListCitiesAdapter extends ArrayAdapter {

    private Context context;
    private ArrayList<City> cities;
    private int resource;
    private LayoutInflater inflater;

    public ListCitiesAdapter(Context context, int resource, ArrayList<City> cities) {
        super(context, resource, cities);
        this.context = context;
        this.cities = cities;
        this.resource = resource;
        this.inflater = LayoutInflater.from(context);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;

        if(convertView==null){
            holder = new ViewHolder();
            inflater.inflate(resource,parent,false);
            holder.text1 = (TextView) convertView.findViewById(android.R.id.text1);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        City city = cities.get(position);
        holder.text1.setText(city.getName());

        return convertView;
    }

    class ViewHolder{

        TextView text1;
    }
}
