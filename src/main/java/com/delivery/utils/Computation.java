package com.delivery.utils;


import com.delivery.constant.VelocityEnum;

/**
 * @author Administrator
 * @ClassName: Calculate
 * @ProjectName delivery
 * @Description: TODO
 * @date 2018/11/23 10:51
 */
public class Computation {

    private static double velocity = VelocityEnum.TWENTY.getVelocity();

    public static double timeCalculate(double lng1, double lat1, double lng2, double lat2){
        double distance = getDistance(lng1, lat1, lng2, lat2) / 1000;
        double time = (distance / velocity) * 60;
        return time;
    }

    // 返回时间分钟
    public static double timeCalculate(Event location1, Event location2){
        double distance = getDistance(location1.getLng(), location1.getLat(), location2.getLng(), location2.getLat())
                / 1000;
        double time = (distance / velocity) * 60;
        return time;
    }

    // 经纬度计算工具
    private static double EARTH_RADIUS = 6378.137;

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 通过经纬度获取距离(单位：米)
     *
     * @param lat1
     * @param lng1
     * @param lat2
     * @param lng2
     * @return 距离
     */
    private static double getDistance( double lng1, double lat1,double lng2, double lat2 ) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(radLat1) * Math.cos(radLat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000d) / 10000d;
        s = s * 1000;
        return s;
    }
}
