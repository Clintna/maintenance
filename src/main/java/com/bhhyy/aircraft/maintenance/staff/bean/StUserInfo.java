package com.bhhyy.aircraft.maintenance.staff.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.bhhyy.aircraft.maintenance.common.annotation.IsMobile;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * <p>
 *
 * </p>
 *
 * @author Jinglin
 * @since 2022-04-29
 */
@Getter
@Setter
@TableName("st_user_info")
public class StUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private Integer DEFAULT_DEPARTMENT_ID = 513;
    /**
     * 用户id
     */
    @TableId("user_id")
    private String userId;

    /**
     * 用户名
     */
    @TableField("user_name")
    @NotBlank(message = "must")
    private String userName;

    /**
     * 密码
     */
    @TableField("password")
    @NotBlank(message = "must")
    private String password;

    /**
     * 邮箱
     */
    @TableField("email")
    @NotBlank(message = "must")
    @Email(message = "邮箱格式不正确")
    private String email;

    /**
     * 头像
     */
    @TableField("avatar")
    private String avatar;

    /**
     * 部门id
     */
    @TableField("department_id")
    private Integer departmentId;

    /**
     * 手机号码
     */
    @TableField("phone_number")
    @NotBlank(message = "must")
//    @IsMobile(message = "手机号")
    private String phoneNumber;

    /**
     * 用户角色
     */
    @TableField("user_roles")
    private String userRoles;

    /**
     * MD5用户密码加密盐值
     */
    @TableField("password_salt")
    private String passwordSalt;

    /**
     * 最后一次登陆时间
     */
    @TableField("last_login")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime lastLogin;

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
