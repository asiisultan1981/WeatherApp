
package com.example.weatherapp.AccuWeather;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("ID")
    @Expose
    private String iD;
    @SerializedName("LocalizedName")
    @Expose
    private String localizedName;
    @SerializedName("EnglishName")
    @Expose
    private String englishName;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Country() {
    }

    /**
     * 
     * @param englishName
     * @param localizedName
     * @param iD
     */
    public Country(String iD, String localizedName, String englishName) {
        super();
        this.iD = iD;
        this.localizedName = localizedName;
        this.englishName = englishName;
    }

    public String getID() {
        return iD;
    }

    public void setID(String iD) {
        this.iD = iD;
    }

    public String getLocalizedName() {
        return localizedName;
    }

    public void setLocalizedName(String localizedName) {
        this.localizedName = localizedName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

}
