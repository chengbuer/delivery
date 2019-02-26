package com.delivery.utils;

/**
 * @author Administrator
 * @ClassName: Task
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/22 14:45
 */
public class Task {
    private int id;
    private double lng;
    private double lat;
    private double radius;
    private double reward;
    private double beginTime;
    private double endTIme;
    private int type;

    public Task(){

    }

    public Task(int id, double lng, double lat, double beginTime, double endTIme,  double radius, double reward, int type) {
        this.id = id;
        this.lng = lng;
        this.lat = lat;
        this.radius = radius;
        this.reward = reward;
        this.beginTime = beginTime;
        this.endTIme = endTIme;
        this.type = type;
    }

    public double getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(double beginTime) {
        this.beginTime = beginTime;
    }

    public double getEndTIme() {
        return endTIme;
    }

    public void setEndTIme(double endTIme) {
        this.endTIme = endTIme;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", lng=" + lng +
                ", lat=" + lat +
                ", radius=" + radius +
                ", reward=" + reward +
                ", beginTime=" + beginTime +
                ", endTIme=" + endTIme +
                ", type=" + type +
                '}';
    }
}
