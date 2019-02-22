package com.delivery.utils;

/**
 * @author Administrator
 * @ClassName: LngLatRange
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/22 17:13
 */
public class LngLatRange {
    private double lngStart;
    private double lngEnd;
    private double latStart;
    private double latEnd;

    public double getLngStart() {
        return lngStart;
    }

    public void setLngStart(double lngStart) {
        this.lngStart = lngStart;
    }

    public double getLngEnd() {
        return lngEnd;
    }

    public void setLngEnd(double lngEnd) {
        this.lngEnd = lngEnd;
    }

    public double getLatStart() {
        return latStart;
    }

    public void setLatStart(double latStart) {
        this.latStart = latStart;
    }

    public double getLatEnd() {
        return latEnd;
    }

    public void setLatEnd(double latEnd) {
        this.latEnd = latEnd;
    }

    @Override
    public String toString() {
        return "LngLatRange{" +
                "lngStart=" + lngStart +
                ", lngEnd=" + lngEnd +
                ", latStart=" + latStart +
                ", latEnd=" + latEnd +
                '}';
    }
}
