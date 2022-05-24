package com.bhhyy.aircraft.maintenance.archives.handler.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bhhyy.aircraft.maintenance.archives.enums.FileOptionsEnum;
import com.bhhyy.aircraft.maintenance.archives.bean.ArFileInfo;
import com.bhhyy.aircraft.maintenance.archives.service.ArFileInfoService;
import com.bhhyy.aircraft.maintenance.common.tools.TaskIdGenerator;
import com.bhhyy.aircraft.maintenance.common.transaction.TxManager;
import com.bhhyy.aircraft.maintenance.materials.bean.MaBaseInfo;
import com.bhhyy.aircraft.maintenance.materials.service.MaBaseInfoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * @Author: Jinglin
 * @Date: 2022/05/11
 * @Description:
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class FileProcessHandlerServiceImpl implements FileProcessHandlerService {

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("/yyyy/MM/dd/");
    @Resource
    private ArFileInfoService arFileInfoService;
    @Resource
    private MaBaseInfoService maBaseInfoService;
    @Value(value = "${arfileinfo.folder.folderPath}")
    private String folderPath;
    @Resource
    private final TxManager txManager;

    @Override
    public String uploadFile(MultipartFile multipartFile, String pieceNumber) throws IOException {
        final String originalFilename = multipartFile.getOriginalFilename();
        final String extension = "." + FilenameUtils.getExtension(originalFilename);
        final String newName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + UUID.randomUUID().toString().replace("-", "") + extension;

        final Long size = multipartFile.getSize();

        final String contentType = multipartFile.getContentType();

        String format = "/" + pieceNumber;
        String path = folderPath.concat(format);

        ArFileInfo arFileInfo = new ArFileInfo();
        arFileInfo.setFileId(TaskIdGenerator.nextId());
        arFileInfo.setOriginalName(originalFilename);
        arFileInfo.setNewName(newName);
        arFileInfo.setFolderId(arFileInfo.getDEFAULT_FOLDER_ID());
        arFileInfo.setFileSize(size);
        arFileInfo.setFileType(contentType);
        arFileInfo.setStoragePath(path);
        arFileInfo.setDownloadTime(arFileInfo.getDEFAULT_DOWN_TIME());
        arFileInfo.setUploadTime(LocalDateTime.now());
        arFileInfo.setCreateTime(LocalDateTime.now());
        arFileInfo.setModifyTime(LocalDateTime.now());


        txManager.call(() -> {
            try {
                File folder = new File(path);
                if (!folder.exists()) {
                    folder.mkdirs();
                }
                arFileInfoService.save(arFileInfo);
                final MaBaseInfo maBaseInfo = maBaseInfoService.queryMaBaseInfo(pieceNumber);
                List<String> list = new ArrayList<>();
                if (null != maBaseInfo.getExtendInfo() && !Objects.equals(maBaseInfo.getExtendInfo(), "")){
                    list = JSONArray.parseArray(maBaseInfo.getExtendInfo()).toJavaList(String.class);
                    list.forEach(l->{
                        final ArFileInfo byFileId = arFileInfoService.findByFileId(l);
                        if (arFileInfo.getOriginalName().equals(byFileId.getOriginalName())){
                            throw new RuntimeException("已存在该文件，请检查");
                        }
                    });
                }
                list.add(arFileInfo.getFileId());
                maBaseInfo.setExtendInfo(JSONObject.toJSONString(list));
                final boolean save = maBaseInfoService.updateMaBaseInfo(maBaseInfo);

                if (save) {
                    multipartFile.transferTo(new File(folder, newName));
                }
                return true;
            } catch (IOException ioException) {
                log.error("上传文件错误, e：{},文件名：{}", ioException.getMessage(), originalFilename);
                throw new RuntimeException("上传文件错误");
            } catch (Exception exception) {
                log.error("保存文件信息错误，e：{}", (Object) exception.getStackTrace());
                throw new RuntimeException("保存文件错误");
            }
        });
        return arFileInfo.getFileId();
    }

    @Override
    public void fileDownloadHandler(String fileId, String tag, HttpServletResponse response) throws IOException {
        try {
            final ArFileInfo file = arFileInfoService.findByFileId(fileId);
            if (null == file){
                throw new RuntimeException("查询不到该文件，请检查");
            }
            String path = file.getStoragePath();

            FileInputStream inputStream = new FileInputStream(new File(path, file.getNewName()));

            response.setHeader("content-disposition", tag + URLEncoder.encode(file.getOriginalName(), "UTF-8"));

            ServletOutputStream outputStream = response.getOutputStream();
            txManager.call(() -> {
                try {
                    IOUtils.copy(inputStream, outputStream);
                    IOUtils.closeQuietly(inputStream);
                    IOUtils.closeQuietly(outputStream);
                    if (tag.equals(FileOptionsEnum.FILE_DOWNLOAD.getCode())) {
                        arFileInfoService.updateTimesByFileId(file);
                    }
                    // TODO: 2022/5/11 日志
                    return true;
                } catch (IOException e) {
                    log.error("文件下载失败，e:{},fileId:{}", e.toString(), fileId);
                    throw new RuntimeException("文件下载失败");
                } catch (Exception ex) {
                    log.error("系统异常，e:{}, file:{}", ex.toString(), JSONObject.toJSONString(file));
                    throw new RuntimeException("系统内部异常");
                }
            });
        } catch (Exception e) {
            log.error("下载文件失败，e：{}，fileId：{}", e.getStackTrace(), fileId);
            throw new RuntimeException("找不到文件信息，下载失败");
        }

    }

    @Override
    public Boolean deleteFile(String fileId, String pieceNumber) {
        try {
            final ArFileInfo file = arFileInfoService.findByFileId(fileId);
            String path = file.getStoragePath();

            File file1 = new File(path, file.getNewName());
            // TODO: 2022/5/12 日志
            if (file1.exists()) {
                final boolean delete = file1.delete();
                if (delete) {
                    return txManager.call(()->{
                        final MaBaseInfo maBaseInfo = maBaseInfoService.queryMaBaseInfo(pieceNumber);
                        final List<String> list = JSONArray.parseArray(maBaseInfo.getExtendInfo()).toJavaList(String.class);
                        list.remove(fileId);
                        maBaseInfo.setExtendInfo(JSONObject.toJSONString(list));
                        maBaseInfoService.updateMaBaseInfo(maBaseInfo);
                        arFileInfoService.deleteFileByFileId(fileId);
                        return true;
                    });
                } else {
                    log.error("文件删除失败，请联系管理员，file:{}", JSONObject.toJSONString(file));
                    throw new Exception();
                }
            } else {
                log.error("文件不存在，file:{}", JSONObject.toJSONString(file));
                throw new Exception();
            }
        } catch (Exception e) {
            log.error("文件删除失败,e:{},file:{}", e.getStackTrace(), JSONObject.toJSONString(fileId));
            throw new RuntimeException("文件删除失败，请联系管理员检查原因");
        }
    }
}
