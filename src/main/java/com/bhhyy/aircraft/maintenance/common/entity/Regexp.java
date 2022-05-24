package com.bhhyy.aircraft.maintenance.common.entity;

/**
 * 正则常量
 *
 * @author Jinglin
 */
public interface Regexp {

    /**
     * 简单手机号正则（这里只是简单校验是否为 11位，实际规则更复杂）
     */
    String MOBILE_REG = "/^1(3\\d|4[5-9]|5[0-35-9]|6[567]|7[0-8]|8\\d|9[0-35-9])\\d{8}$/";

}
