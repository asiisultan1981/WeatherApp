package com.example.weatherapp.OpenWeatherMap;

import com.example.weatherapp.Weather;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Example {
    @SerializedName("sys")
    private Country country;

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @SerializedName("main")
    private Main main;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @SerializedName("weather")
    private List<Weather> weatherList = null;

    public List<Weather> getWeatherList() {
        return weatherList;
    }

    public void setWeatherList(List<Weather> weatherList) {
        this.weatherList = weatherList;
    }
}
