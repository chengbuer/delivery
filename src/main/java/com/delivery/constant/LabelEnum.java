package com.delivery.constant;

/**
 * @author Administrator
 * @ClassName: LabelEnum
 * @ProjectName delivery
 * @Description: TODO
 * @date 2019/2/23 15:03
 */
public enum LabelEnum {
    Worker("worker"), PointInterest("point of interest"), Task("task");

    private final String name;

    private LabelEnum(String name)
    {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

