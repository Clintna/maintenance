package com.bhhyy.aircraft.maintenance.materials.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

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
@TableName("ma_hang_lable_record")
public class MaHangLableRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 挂签唯一id
     */
    @TableId("hang_lable_id")
    private String hangLableId;

    /**
     * 航材入库唯一id
     */
    @TableField("material_stor_id")
    private String materialStorId;

    /**
     * 航材件号
     */
    @TableField("piece_number")
    private String pieceNumber;

    /**
     * 航材名称
     */
    @TableField("material_name")
    private String materialName;

    /**
     * 批次号
     */
    @TableField("batch_number")
    private String batchNumber;

    /**
     * 合同号
     */
    @TableField("contract_number")
    private String contractNumber;

    /**
     * 航材数量
     */
    @TableField("material_quantity")
    private Integer materialQuantity;

    /**
     * 原始适航标签种类
     */
    @TableField("original_airworthiness_label_type")
    private String originalAirworthinessLabelType;

    /**
     * 是否是时寿件
     */
    @TableField("is_life")
    private Boolean isLife;

    /**
     * 时寿件属性
     */
    @TableField("life_characteristic")
    private String lifeCharacteristic;

    /**
     * 如果是时寿件，OEM厂家给出的超期日期（必填，来自证书）
     */
    @TableField("life_due")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lifeDue;

    /**
     * 检验员
     */
    @TableField("material_inspector")
    private String materialInspector;

    /**
     * 检验日期
     */
    @TableField("inspection_time")
    private String inspectionTime;

    /**
     * 扩展信息1
     */
    @TableField("extend_info_one")
    private String extendInfoOne;

    /**
     * 扩展信息2
     */
    @TableField("extend_info_two")
    private String extendInfoTwo;

    /**
     * 创建日期
     */
    @TableField("create_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime createTime;

    /**
     * 修改日期
     */
    @TableField("modify_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime modifyTime;


}
