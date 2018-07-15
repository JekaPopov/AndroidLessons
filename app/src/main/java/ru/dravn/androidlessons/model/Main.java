
package ru.dravn.androidlessons.model;

import android.renderscript.Sampler;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Main {

    @SerializedName("temp")
    @Expose
    private Double temp;
    @SerializedName("pressure")
    @Expose
    private double pressure;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("temp_min")
    @Expose
    private Double tempMin;
    @SerializedName("temp_max")
    @Expose
    private Double tempMax;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Main() {
    }

    /**
     * 
     * @param humidity
     * @param pressure
     * @param tempMax
     * @param temp
     * @param tempMin
     */
    public Main(Double temp, Integer pressure, Integer humidity, Double tempMin, Double tempMax) {
        super();
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public String getTemp() {
        return String.valueOf(temp.intValue());
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public String getPressure() {
        ////перевод в ммHg
        pressure = (int)(pressure * 0.75006375541921);
        return String.valueOf(pressure);
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return String.valueOf(humidity);
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public String getTempMin() {
        return String.valueOf(tempMin.intValue());
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        return String.valueOf(tempMax.intValue());
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

}
