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
@TableName("ma_storage_in_record")
public class MaStorageInRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 航材入库唯一id
     */
    @TableId("material_stor_id")
    private String materialStorId;

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
     * 是否是电子件
     */
    @TableField("is_elec_material")
    private Boolean isElecMaterial;

    /**
     * 电子件版本号
     */
    @TableField("elec_version")
    private String elecVersion;

    /**
     * 如果不是电子件批次号
     */
    @TableField("batch_number")
    private String batchNumber;

    /**
     * 上一家航材分销商
     */
    @TableField("vender_id")
    @NotBlank(message = "{must}")
    private String venderId;

    /**
     * 分销商编制的批次号
     */
    @TableField("vender_batch")
    @NotBlank(message = "{must}")
    private String venderBatch;

    /**
     * 合同号
     */
    @TableField("contract_number")
    @NotBlank(message = "{must}")
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
    @NotBlank(message = "{must}")
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
     * 是否是新件
     */
    @TableField("is_new")
    private Boolean isNew;

    /**
     * 如果不是新件，使用历史和记录
     */
    @TableField("usage_history")
    private String usageHistory;

    /**
     * 适用性
     */
    @TableField("material_applicability")
    private String materialApplicability;

    /**
     * 来源
     */
    @TableField("material_resource")
    private String materialResource;

    /**
     * 追溯性
     */
    @TableField("material_retroactivity")
    private String materialRetroactivity;

    /**
     * 原制造商后期，可加入与IPC中的VENDOR比对，以确认是否合规）
     */
    @TableField("raw_manufacturer")
    private String rawManufacturer;

    /**
     * 部件所有者
     */
    @TableField("material_owner")
    @NotBlank(message = "{must}")
    private String materialOwner;

    /**
     * 检验员
     */
    @TableField("material_inspector")
    @NotBlank(message = "{must}")
    private String materialInspector;

    /**
     * 检验日期
     */
    @TableField("inspection_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime inspectionTime;

    /**
     * 库管员
     */
    @TableField("warehouse_keeper")
    @NotBlank(message = "{must}")
    private String warehouseKeeper;

    /**
     * 库存要求
     */
    @TableField("stor_requirement")
    @NotBlank(message = "{must}")
    private String storRequirement;

    /**
     * 使用限制
     */
    @TableField("usage_restraint")
    @NotBlank(message = "{must}")
    private String usageRestraint;

    /**
     * 可用类型（可用、废弃、可疑
     */
    @TableField("available_type")
    @NotBlank(message = "{must}")
    private String availableType;

    /**
     * 货架号
     */
    @TableField("shelf_id")
    @NotBlank(message = "{must}")
    private String shelfId;

    /**
     * 是否是新件
     */
    @TableField("is_deleted")
    private Boolean isDeleted = true;

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
