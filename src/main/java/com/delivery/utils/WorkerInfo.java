package com.delivery.utils;

/**
 * @author Administrator
 * @ClassName: WorkerInfo
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/3/3 20:41
 */
public class WorkerInfo {
    private int workerId;
    private double lng;
    private double lat;
    private int eventCompleted;

    @Override
    public String toString() {
        return "WorkerInfo{" +
                "workerId=" + workerId +
                ", lng=" + lng +
                ", lat=" + lat +
                ", eventCompleted=" + eventCompleted +
                '}';
    }

    public int getEventCompleted() {
        return eventCompleted;
    }

    public void setEventCompleted(int eventCompleted) {
        this.eventCompleted = eventCompleted;
    }



    public int getWorkerId() {
        return workerId;
    }

    public void setWorkerId(int workerId) {
        this.workerId = workerId;
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

}
