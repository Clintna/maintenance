package com.bhhyy.aircraft.maintenance.archives.enums;


/**
 * @Author: Jinglin
 * @Date: 2022/05/12
 * @Description:
 */
public enum FileOptionsEnum {

    FILE_PREVIEW("inline;fileName=", "文件预览标签"),
    FILE_DOWNLOAD("attachment;fileName=", "文件下载标签");


    private FileOptionsEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private final String code;
    private final String msg;

    public String getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

}
