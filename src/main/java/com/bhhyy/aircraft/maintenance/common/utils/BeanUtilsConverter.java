package com.bhhyy.aircraft.maintenance.common.utils;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import java.util.Date;

/**
 * @Author: Jinglin
 * @Date: 2022/05/05
 * @Description:
 */
public class BeanUtilsConverter {
    public static void copyBean(Object target, Object from) {
        ConvertUtils.register(new Converter() {
            public Object convert(Class cls, Object obj) {
                if (obj instanceof Date) {
                    return obj;
                }
                String p = (String) obj;
                if (p == null || p.trim().length() == 0) {
                    return null;
                }
                return obj;

            }
        }, Date.class);
        try {
            BeanUtils.copyProperties(target, from);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
