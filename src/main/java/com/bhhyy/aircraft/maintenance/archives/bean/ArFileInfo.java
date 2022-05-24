package com.bhhyy.aircraft.maintenance.archives.bean;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2022-05-11
 */
@Getter
@Setter
@TableName("ar_file_info")
public class ArFileInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableField(exist = false)
    private Integer DEFAULT_FILE_ID = 1;
    @TableField(exist = false)
    private Integer DEFAULT_FOLDER_ID = 0;
    @TableField(exist = false)
    private Integer DEFAULT_DOWN_TIME = 0;

    /**
     * 文件id
     */
    @TableId(value = "file_id")
    private String fileId;

    /**
     * 所属文件夹id
     */
    @TableField("folder_id")
    private Integer folderId;

    /**
     * 上传时初始文件名
     */
    @TableField("original_name")
    private String originalName;

    /**
     * 实际存储文件名
     */
    @TableField("new_name")
    private String newName;

    /**
     * 文件类型
     */
    @TableField("file_type")
    private String fileType;

    /**
     * 文件大小，单位是kb
     */
    @TableField("file_size")
    private Long fileSize;

    /**
     * 存储路径
     */
    @TableField("storage_path")
    private String storagePath;

    /**
     * 下载次数
     */
    @TableField("download_time")
    private Integer downloadTime;

    /**
     * 上传时间
     */
    @TableField("upload_time")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private LocalDateTime uploadTime;

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
