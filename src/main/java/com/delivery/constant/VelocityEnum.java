package com.delivery.constant;

/**
 * @author Administrator
 * @ClassName: VelocityEnum
 * @ProjectName delivery
 * @Description: TODO
 * @date 2018/11/25 14:32
 */
public enum VelocityEnum {
    TWENTY(20);

    private final double velocity;

    private VelocityEnum(double velocity){
        this.velocity = velocity;
    }

    public double getVelocity(){
        return this.velocity;
    }


}
