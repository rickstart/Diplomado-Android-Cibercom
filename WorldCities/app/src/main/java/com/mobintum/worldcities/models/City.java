package com.mobintum.worldcities.models;

import java.util.ArrayList;

/**
 * Created by Rick on 01/06/15.
 */
public class City {

    private String name;
    private double latitude;
    private double longitude;
    private String picUrl;

    public City(String name, double latitude, double longitude, String picUrl) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.picUrl = picUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public static ArrayList<City> getData(){
        ArrayList<City> cities = new ArrayList<City>();
        cities.add(new City("New York",40.717626,-73.997534,"http://cache.graphicslib.viator.com/graphicslib/thumbs674x446/2625/SITours/new-york-city-in-one-day-small-group-sightseeing-tour-in-new-york-city-147596.jpg"));
        cities.add(new City("Tokio",35.689487, 139.691706,"http://passioncolombia.com.co/wp-content/uploads/2014/06/Tokio_start_fpm158727850_16fec334f1.jpg"));
        cities.add(new City("Mexico City",19.432608, -99.133208,"https://www.mcquilling.com/media/locations/mexico_city.jpg"));
        cities.add(new City("San Francisco",37.774929, -122.419416,"https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcS2XDhxWrqYlHgeGYVP-xycedjruBzivTt5NUrL2RESditLhV4jBQ"));
        cities.add(new City("Shangai",31.230416, 121.473701,"http://www.itespresso.es/wp-content/uploads/2010/06/shandes.jpg"));
        return cities;
    }
}
