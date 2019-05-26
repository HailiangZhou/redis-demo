package com.atguigu.redis.test.enumerate;

import lombok.Getter;

/**
 * 可以把枚举类看成数据库
 * 如： ONE(1,v1,v2,v3,v4,....)
 * ONE 可看成一张表
 * 1，v1,v2,v3,.... 可看成表中的字段
 */
public enum CountryEnum {
    ONE(1, "齐"), TWO(2, "楚"), THREE(3, "燕"), FOUR(4, "赵"), FIVE(5, "魏"), SIX(6, "韩");

    @Getter
    private int retCode;
    @Getter
    private String retMessage;

    CountryEnum(int retCode, String retMessage) {
        this.retCode = retCode;
        this.retMessage = retMessage;
    }

    public static CountryEnum foreachCountryEnum(int index) {

        CountryEnum[] values = CountryEnum.values();
        for (CountryEnum value : values) {
            if (index == value.retCode) {
                return value;
            }
        }
        return null;
    }
}
