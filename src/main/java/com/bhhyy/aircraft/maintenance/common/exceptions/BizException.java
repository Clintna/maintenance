package com.bhhyy.aircraft.maintenance.common.exceptions;

public class BizException extends RuntimeException {

    private static final long serialVersionUID = 5524681570122303344L;

    private String code;
    private String msg;
    //the exception belong to which business
    private String bizScope;

    public BizException(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public BizException(String code, String msg, String bizScope) {
        this.code = code;
        this.msg = msg;
        this.bizScope = bizScope;
    }

    public static void throwException(String code, String msg) {
        throw new BizException(code, msg);
    }


    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getBizScope() {
        return bizScope;
    }

    public void setBizScope(String bizScope) {
        this.bizScope = bizScope;
    }
}