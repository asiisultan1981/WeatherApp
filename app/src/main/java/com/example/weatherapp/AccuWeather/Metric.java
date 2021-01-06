
package com.example.weatherapp.AccuWeather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metric {

    @SerializedName("Value")
    @Expose
    private Double value;
    @SerializedName("Unit")
    @Expose
    private String unit;
    @SerializedName("UnitType")
    @Expose
    private Integer unitType;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Metric() {
    }

    /**
     * 
     * @param unitType
     * @param unit
     * @param value
     */
    public Metric(Double value, String unit, Integer unitType) {
        super();
        this.value = value;
        this.unit = unit;
        this.unitType = unitType;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Integer getUnitType() {
        return unitType;
    }

    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

}
