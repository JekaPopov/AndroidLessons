
package ru.dravn.androidlessons.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Wind {

    @SerializedName("speed")
    @Expose
    private Double speed;
    @SerializedName("deg")
    @Expose
    private Double deg;
    @SerializedName("gust")
    @Expose
    private Integer gust;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Wind() {
    }

    /**
     * 
     * @param gust
     * @param speed
     * @param deg
     */
    public Wind(Double speed, Double deg, Integer gust) {
        super();
        this.speed = speed;
        this.deg = deg;
        this.gust = gust;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getDeg() {
        return deg;
    }

    public void setDeg(Double deg) {
        this.deg = deg;
    }

    public Integer getGust() {
        return gust;
    }

    public void setGust(Integer gust) {
        this.gust = gust;
    }

}
