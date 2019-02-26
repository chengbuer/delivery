package com.delivery.entity;

import javax.persistence.*;

/**
 * @author Administrator
 * @ClassName: PointOfInterest
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/22 14:45
 */

@Entity
@Table(name="point_of_interest")
public class PointOfInterest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="lng")
    private double lng;
    @Column(name="lat")
    private double lat;
    @Column(name="types")
    private String types;
    @Column(name="reward")
    private double reward;

    public PointOfInterest(){

    }

    public PointOfInterest(double lng, double lat, double reward, String types) {
        this.lng = lng;
        this.lat = lat;
        this.types = types;
        this.reward = reward;
    }

    @Override
    public String toString() {
        return "PointOfInterest{" +
                "id=" + id +
                ", lng=" + lng +
                ", lat=" + lat +
                ", types='" + types + '\'' +
                ", reward=" + reward +
                '}';
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

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public double getReward() {
        return reward;
    }

    public void setReward(double reward) {
        this.reward = reward;
    }
}
