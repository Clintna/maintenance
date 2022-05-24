package com.bhhyy.aircraft.maintenance.materials.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * <p>
 *
 * </p>
 *
 * @author Jinglin
 * @since 2022-04-25
 */
@Getter
@Setter
@TableName("ma_defect_materials")
public class MaDefectMaterials implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 故障件记录编号
     */
    @TableId("defect_material_id")
    @NotBlank(message = "{must}")
    private String defectMaterialId;

    /**
     * 航材件号
     */
    @TableField("piece_number")
    @NotBlank(message = "{must}")
    private String pieceNumber;

    /**
     * 航材名称
     */
    @TableField("material_name")
    @NotBlank(message = "{must}")
    private String materialName;

    /**
     * 故障件操作员
     */
    @TableField("operator")
    @NotBlank(message = "{must}")
    private String operator;

    /**
     * 操作时间
     */
    @TableField("operation_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime operationTime;

    /**
     * 故障原因
     */
    @TableField("defect_reason")
    @NotBlank(message = "{must}")
    private String defectReason;

    /**
     * 航班号
     */
    @TableField("flight_number")
    @NotBlank(message = "{must}")
    private String flightNumber;

    /**
     * 航班时间
     */
    @TableField("flight_time")
    @NotBlank(message = "{must}")
    private String flightTime;

    /**
     * 航材挂签编号
     */
    @TableField("hang_label_id")
    @NotBlank(message = "{must}")
    private String hangLabelId;

    /**
     * 扩展字段
     */
    @TableField("extend_info")
    private String extendInfo;

    /**
     * 创建时间
     */
    @TableField("create_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField("modify_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime modifyTime;


}
