package com.delivery.constant;

/**
 * @author Administrator
 * @ClassName: RadiusEnum
 * @ProjectName delivery
 * @Description: TODO
 * @date 2018/11/26 22:04
 */
public enum RadiusEnum {
    TWENTY(1000);

    private final double raidus;

    private RadiusEnum(double raidus){
        this.raidus = raidus;
    }

    public double getRaidus(){
        return this.raidus;
    }
}
