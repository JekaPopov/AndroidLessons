
package ru.dravn.androidlessons.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sys {

    @SerializedName("country")
    @Expose
    private String country;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Sys() {
    }

    /**
     * 
     * @param country
     */
    public Sys(String country) {
        super();
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
