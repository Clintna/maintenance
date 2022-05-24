package com.bhhyy.aircraft.maintenance.archives.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bhhyy.aircraft.maintenance.archives.bean.ArFileInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.materials.bean.MaBaseInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Jinglin
 * @since 2022-05-11
 */
public interface ArFileInfoService extends IService<ArFileInfo> {
    /**
     * 上传文件
     *
     * @param arFileInfo
     * @return
     */
    Boolean saveFile(ArFileInfo arFileInfo);

    /**
     * 根据id查询文件具体信息
     *
     * @param fileId
     * @return
     */
    ArFileInfo findByFileId(String fileId);

    /**
     * 根据文件id更新下载次数
     *
     * @return
     */
    Boolean updateTimesByFileId(ArFileInfo file);

    /**
     * 根据文件id删除文件记录
     *
     * @param fileId
     * @return
     */
    Boolean deleteFileByFileId(String fileId);

    /**
     * 分页查询文件信息
     *
     * @param request request
     * @return IPage
     */
    IPage<ArFileInfo> queryArFileInfoList(QueryRequest request);
}
