package com.bhhyy.aircraft.maintenance.common.entity;

import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * @author MrBird
 */
public class FlightResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -8713837118340960775L;

    public FlightResponse code(HttpStatus status) {
        put("code", status.value());
        return this;
    }

    public FlightResponse message(String message) {
        put("message", message);
        return this;
    }

    public FlightResponse data(Object data) {
        put("data", data);
        return this;
    }

    public FlightResponse success() {
        code(HttpStatus.OK);
        put("success", true);
        return this;
    }

    public FlightResponse fail() {
        code(HttpStatus.INTERNAL_SERVER_ERROR);
        put("success", false);
        return this;
    }

    @Override
    public FlightResponse put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
