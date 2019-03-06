package com.delivery.entity;

import com.delivery.constant.LabelEnum;
import com.delivery.utils.Event;
import com.delivery.utils.WorkerInfo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.*;
import java.util.ArrayList;
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
@Table(name = "schedule")
public class Schedule {
    @Id
    @GeneratedValue(generator = "worker_id")
    @GenericGenerator(name = "worker_id", strategy = "assigned")
    @Column(name = "worker_id")
    private int workerId;
    @Column(name = "lng")
    private double lng;
    @Column(name = "lat")
    private double lat;
    @Column(name = "events")
    private byte[] events;


    @Transient
    private List<Event> schedule;

    public Schedule() {
    }
// 从数据中读取的时候会自动安排，这里不用写
    // 关键在于要将序列化后的二进制转化为对象


    // 只初始化一次
    public Schedule(int workerId, double lng, double lat, double curTime) {
        this.workerId = workerId;
        this.lng = lng;
        this.lat = lat;
        schedule = new ArrayList<>();
        Event curLocation = new Event(LabelEnum.Worker.getName(), lng, lat, curTime, 0);
        schedule.add(curLocation);
        scheduleToBytes();
    }


    public void updateSchedule(WorkerInfo workerInfo){
        this.lng = workerInfo.getLng();
        this.lat = workerInfo.getLat();
        bytesToSchedule();
        int eventCompleted = workerInfo.getEventCompleted();
        for(int i = eventCompleted; i > 1; i--){
            schedule.remove(i);
        }

        scheduleToBytes();
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

    public void scheduleToBytes() {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oo = null;
        try {
            oo = new ObjectOutputStream(bos);
            oo.writeObject(this.schedule);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bos.close();
                oo.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.events = bos.toByteArray();
        }
    }


    // 需要额外调用这个方法
    public void bytesToSchedule() {
        ByteArrayInputStream bis = new ByteArrayInputStream(this.events);
        ObjectInputStream ois = null;
        List<Event> schedule = null;
        try {
            ois = new ObjectInputStream(bis);
            schedule = (List<Event>) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.schedule = schedule;
        }
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "workerId=" + workerId +
                ", lng=" + lng +
                ", lat=" + lat +
                ", schedule=" + schedule +
                '}';
    }
}
