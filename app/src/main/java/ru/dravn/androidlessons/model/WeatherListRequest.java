
package ru.dravn.androidlessons.model;

import java.util.ArrayList;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WeatherListRequest {

    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("cod")
    @Expose
    private String cod;
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("list")
    @Expose
    private java.util.List<ru.dravn.androidlessons.model.List> list = new ArrayList<ru.dravn.androidlessons.model.List>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public WeatherListRequest() {
    }

    /**
     * 
     * @param message
     * @param count
     * @param cod
     * @param list
     */
    public WeatherListRequest(String message, String cod, Integer count, java.util.List<ru.dravn.androidlessons.model.List> list) {
        super();
        this.message = message;
        this.cod = cod;
        this.count = count;
        this.list = list;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public java.util.List<ru.dravn.androidlessons.model.List> getList() {
        return list;
    }

    public void setList(java.util.List<ru.dravn.androidlessons.model.List> list) {
        this.list = list;
    }

}
