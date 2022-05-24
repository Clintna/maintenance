package com.bhhyy.aircraft.maintenance.archives.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bhhyy.aircraft.maintenance.archives.bean.ArFileInfo;
import com.bhhyy.aircraft.maintenance.archives.mapper.ArFileInfoMapper;
import com.bhhyy.aircraft.maintenance.archives.service.ArFileInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import com.bhhyy.aircraft.maintenance.common.tools.TaskIdGenerator;
import com.bhhyy.aircraft.maintenance.common.transaction.TxManager;
import com.bhhyy.aircraft.maintenance.materials.bean.MaBaseInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Jinglin
 * @since 2022-05-11
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ArFileInfoServiceImpl extends ServiceImpl<ArFileInfoMapper, ArFileInfo> implements ArFileInfoService {

    @Resource
    private ArFileInfoMapper arFileInfoMapper;

    @Override
    public Boolean saveFile(ArFileInfo arFileInfo) {
        return save(arFileInfo);
    }

    @Override
    public ArFileInfo findByFileId(String fileId) {
        return getOne(new LambdaQueryWrapper<ArFileInfo>().eq(ArFileInfo::getFileId, fileId));
    }

    @Override
    public Boolean updateTimesByFileId(ArFileInfo file) {
        file.setDownloadTime(file.getDownloadTime() + 1);
        file.setModifyTime(LocalDateTime.now());
        return updateById(file);
    }

    @Override
    public Boolean deleteFileByFileId(String fileId) {
        return removeById(fileId);
    }

    @Override
    public IPage<ArFileInfo> queryArFileInfoList(QueryRequest request) {
        QueryWrapper<ArFileInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("create_time");
        return arFileInfoMapper.selectPage(new Page<>(request.getPageNum(), request.getPageSize()), queryWrapper);
    }
}
