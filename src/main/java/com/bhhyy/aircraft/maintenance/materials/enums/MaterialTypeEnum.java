package com.bhhyy.aircraft.maintenance.materials.enums;

import org.apache.commons.lang3.StringUtils;

/**
 * @Author: Jinglin
 * @Date: 2022/04/29
 * @Description:
 */

public enum MaterialTypeEnum {
    USABLE("usable", "可用航材"),
    DUBIOUS("dubious", "可疑航材"),
    WASTE("waste", "报废航材");

    private final String code;
    private final String msg;


    private MaterialTypeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public static MaterialTypeEnum queryByCode(final String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        } else {
            MaterialTypeEnum[] var1 = values();
            int var2 = var1.length;

            for (int var3 = 0; var3 < var2; ++var3) {
                MaterialTypeEnum materialTypeEnum = var1[var3];
                if (materialTypeEnum.code.equals(code)) {
                    return materialTypeEnum;
                }
            }

            return null;
        }
    }
}
