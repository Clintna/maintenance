package com.bhhyy.aircraft.maintenance.common.controller;

import com.bhhyy.aircraft.maintenance.common.annotation.IsMobile;
import com.bhhyy.aircraft.maintenance.common.entity.Regexp;
import com.bhhyy.aircraft.maintenance.common.utils.FlightMainUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 校验是否为合法的手机号码
 *
 * @author MrBird
 */
public class MobileValidator implements ConstraintValidator<IsMobile, String> {

    @Override
    public void initialize(IsMobile isMobile) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            if (StringUtils.isBlank(s)) {
                return true;
            } else {
                String regex = Regexp.MOBILE_REG;
                return FlightMainUtil.match(regex, s);
            }
        } catch (Exception e) {
            return false;
        }
    }
}
