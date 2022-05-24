package com.bhhyy.aircraft.maintenance.common.websocket.pojo;

import lombok.Data;

import javax.jws.Oneway;
import java.util.Map;

/**
 * @Author: Jinglin
 * @Date: 2022/05/18
 * @Description:
 */
@Data
public class ReqMessageBody {
    private String message;
    private Map<String, Object> data;
}
