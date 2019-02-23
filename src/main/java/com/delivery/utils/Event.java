package com.delivery.utils;

import java.io.Serializable;

/**
 * @author Administrator
 * @ClassName: Event
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/22 20:27
 */
public class Event implements Serializable {
    private String label;
    private double lng;
    private double lat;
    private double earliestTime;
    private double latestTime;
    private int load;

    public Event(String label, double lng, double lat, double earliestTime, double latestTime) {
        this.label = label;
        this.lng = lng;
        this.lat = lat;
        this.earliestTime = earliestTime;
        this.latestTime = latestTime;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getEarliestTime() {
        return earliestTime;
    }

    public void setEarliestTime(double earliestTime) {
        this.earliestTime = earliestTime;
    }

    public double getLatestTime() {
        return latestTime;
    }

    public void setLatestTime(double latestTime) {
        this.latestTime = latestTime;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public Event copy(){
        return new Event(label, lng, lat, earliestTime, latestTime);
    }

    @Override
    public String toString() {
        return "Event{" +
                "label='" + label + '\'' +
                ", lng=" + lng +
                ", lat=" + lat +
                ", earliestTime=" + earliestTime +
                ", latestTime=" + latestTime +
                ", load=" + load +
                '}';
    }
}
