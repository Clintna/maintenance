package com.bhhyy.aircraft.maintenance.common.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @Author: Jinglin
 * @Date: 2022/05/07
 * @Description:
 */
@Data
public class DeleteStorInRecordDTO {
    List<String> deletedRecords;
    List<ErrorUtilDTO> unDeletedRecords;
}
