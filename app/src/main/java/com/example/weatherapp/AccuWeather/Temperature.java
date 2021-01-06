
package com.example.weatherapp.AccuWeather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Temperature {

    @SerializedName("Metric")
    @Expose
    private Metric metric;

    public Metric getMetric() {
        return metric;
    }

    public void setMetric(Metric metric) {
        this.metric = metric;
    }
}
