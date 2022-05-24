package com.bhhyy.aircraft.maintenance.staff.bean;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2022-05-23
 */
@Getter
@Setter
@TableName("st_dept_info")
public class StDeptInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final Integer TOP_NODE = 0;

    /**
     * 部门id
     */
    @TableId(value = "dept_id", type = IdType.AUTO)
    private Integer deptId;

    /**
     * 部门名称
     */
    @TableField("dept_name")
    @NotBlank(message = "部门名称")
    private String deptName;

    /**
     * 父部门id
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 相同等级部门排序
     */
    @TableField("order_num")
    @NotBlank(message = "展示顺序不能为空，最低为1")
    private Integer orderNum;

    /**
     * 部门简介
     */
    @TableField("short_desc")
    private String shortDesc;

    /**
     * 扩展信息
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
