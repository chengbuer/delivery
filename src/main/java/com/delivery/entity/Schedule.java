package com.delivery.entity;

import com.delivery.utils.Event;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author Administrator
 * @ClassName: Schedule
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/22 20:55
 */

@Entity
@Table(name="schedule")
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="worker_id")
    private int workerId;
    @Column(name="lng")
    private double lng;
    @Column(name="lat")
    private double lat;
    @Column(name="events")
    private byte[] events;

    private List<Event> schedule;

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

    public byte[] getEvents() {
        return events;
    }

    public void setEvents(byte[] events) {
        this.events = events;
    }

    public List<Event> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<Event> schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "workerId=" + workerId +
                ", lng=" + lng +
                ", lat=" + lat +
                ", events=" + Arrays.toString(events) +
                ", schedule=" + schedule +
                '}';
    }
}
