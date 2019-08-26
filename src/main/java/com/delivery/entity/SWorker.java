package com.delivery.entity;

import com.delivery.utils.Event;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * @ClassName: SWorker
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/8/26 20:24
 */

@Entity
@Table(name = "worker")
public class SWorker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "lat")
    private double lat;
    @Column(name = "lng")
    private double lng;
    @Column(name = "reward")
    private double reward;
    @Column(name = "capacity")
    private double capacity;
    @Column(name = "radius")
    private double radius;

    List<List<Event>> list = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }

    public double getCapacity() {
        return capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

}
