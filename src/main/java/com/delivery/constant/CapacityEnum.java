package com.delivery.constant;

/**
 * @author Administrator
 * @ClassName: CapacityEnum
 * @ProjectName delivery
 * @Description: TODO
 * @date 2018/11/25 14:38
 */
public enum CapacityEnum {
    FIVE(5);

    private final int capacity;
    private CapacityEnum(int capacity){
        this.capacity = capacity;
    }

    public int getCapacity(){
        return this.capacity;
    }
}
