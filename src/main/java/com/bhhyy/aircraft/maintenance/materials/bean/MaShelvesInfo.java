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
@TableName("ma_shelves_info")
public class MaShelvesInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 货架id
     */
    @TableId("shelf_id")
    @NotBlank(message = "must")
    private String shelfId;

    /**
     * 仓库id
     */
    @TableField("warehouse_id")
    @NotBlank(message = "must")
    private String warehouseId;

    /**
     * 存储航材类型
     */
    @TableField("stor_type")
    @NotBlank(message = "must")
    private String storType;

    /**
     * 存储航材信息，格式为Map，key: pieceNumber_materialStorId, value: materialQuantity
     */
    @TableField("stor_material")
    private String storMaterial;

    /**
     * 注意事项
     */
    @TableField("attention")
    private String attention;

    /**
     * 扩展字段
     */
    @TableField("extend_info")
    private String extendInfo;

    /**
     * 货架到期时间
     */
    @TableField("life_due")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lifeDue;

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
