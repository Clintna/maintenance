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
 * @since 2022-05-10
 */
@Getter
@Setter
@TableName("st_user_auth")
public class StUserAuth implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 权限id
     */
    @TableId(value = "auth_id", type = IdType.AUTO)
    private Integer authId;

    /**
     * 父类id
     */
    @TableField("parent_id")
    private Integer parentId = 0;

    /**
     * 权限名
     */
    @TableField("auth_name")
    @NotBlank(message = "权限名不能为空")
    private String authName;

    /**
     * 权限路径，菜单URL
     */
    @TableField("auth_path")
    private String authPath;

    /**
     * 权限描述，授权码
     */
    @TableField("auth_perm")
    @NotBlank(message = "授权码不能为空")
    private String authPerm;

    /**
     * 权限类型0：目录   1：菜单   2：按钮
     */
    @TableField("auth_type")
    private Integer authType = 0;

    @TableField("component")
    private String component;

    /**
     * 菜单图标
     */
    @TableField("icon")
    private String icon;

    /**
     * 排序
     */
    @TableField("order_number")
    private Integer orderNumber = 0;

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
