
package ru.dravn.androidlessons.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import static java.lang.String.*;

public class Main {

    @SerializedName("temp")
    @Expose
    private Double temp;
    @SerializedName("pressure")
    @Expose
    private Double pressure;
    @SerializedName("humidity")
    @Expose
    private Integer humidity;
    @SerializedName("temp_min")
    @Expose
    private Double tempMin;
    @SerializedName("temp_max")
    @Expose
    private Double tempMax;
    @SerializedName("sea_level")
    @Expose
    private Double seaLevel;
    @SerializedName("grnd_level")
    @Expose
    private Double grndLevel;

    public String getTemp() {
        return valueOf(temp.intValue());
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public String getPressure() {
        ////перевод в ммHg
        pressure = pressure * 0.75006375541921;
        return valueOf(pressure.intValue());
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public String getHumidity() {
        return valueOf(humidity);
    }

    public void setHumidity(Integer humidity) {
        this.humidity = humidity;
    }

    public String getTempMin() {
        return valueOf(tempMin.intValue());
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public String getTempMax() {
        return valueOf(tempMax.intValue());
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public Double getSeaLevel() {
        return seaLevel;
    }

    public void setSeaLevel(Double seaLevel) {
        this.seaLevel = seaLevel;
    }

    public Double getGrndLevel() {
        return grndLevel;
    }

    public void setGrndLevel(Double grndLevel) {
        this.grndLevel = grndLevel;
    }

}
