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
@TableName("ma_supplier_info")
public class MaSupplierInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 供应商编号
     */
    @TableId("vender_id")
    private String venderId;

    /**
     * 供应商名称
     */
    @TableField("vender_name")
    @NotBlank(message = "must")
    private String venderName;

    /**
     * 相关负责人名字
     */
    @TableField("charge_person")
    @NotBlank(message = "must")
    private String chargePerson;

    /**
     * 负责人手机号码
     */
    @TableField("charge_person_phone")
    @NotBlank(message = "must")
    private String chargePersonPhone;

    /**
     * 第二负责人
     */
    @TableField("second_in_charge")
    @NotBlank(message = "must")
    private String secondInCharge;

    /**
     * 第二负责人手机号码
     */
    @TableField("second_phone")
    @NotBlank(message = "must")
    private String secondPhone;

    /**
     * 统一社会信用代码
     */
    @TableField("credit_codes")
    @NotBlank(message = "must")
    private String creditCodes;

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
