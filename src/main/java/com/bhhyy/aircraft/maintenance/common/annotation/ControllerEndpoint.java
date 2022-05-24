package com.bhhyy.aircraft.maintenance.common.annotation;

import com.bhhyy.aircraft.maintenance.common.entity.Strings;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Jinglin
 * @date 2022年4月25日16:08:53
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ControllerEndpoint {

    String operation() default Strings.EMPTY;

    String exceptionMessage() default "飞机维修系统内部异常";
}
