package com.bhhyy.aircraft.maintenance.materials.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

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
@TableName("ma_base_info")
public class MaBaseInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 航材件号
     */
    @TableId("piece_number")
    @NotBlank(message = "{must}")
    private String pieceNumber;

    /**
     * 航材名称
     */
    @TableField("material_name")
    @NotBlank(message = "{must}")
    private String materialName;

    /**
     * 航材库存
     */
    @TableField("material_inventory")
    private Integer materialInventory = 0;

    /**
     * 航材类型
     */
    @TableField("material_type")
    @NotBlank(message = "{must}")
    private String materialType;

    /**
     * 货架号
     */
    @TableField("shelves_number")
    @NotBlank(message = "{must}")
    private String shelvesNumber;

    /**
     * 是否是时寿件
     */
    @TableField("is_life")
    private Boolean isLife;

    /**
     * 如果是时寿件，到期时间
     */
    @TableField("life_due")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lifeDue;

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
