package com.bhhyy.aircraft.maintenance.archives.handler.impl;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: Jinglin
 * @Date: 2022/05/11
 * @Description:
 */
public interface FileProcessHandlerService {
    /**
     * 上传文件
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    String uploadFile(MultipartFile multipartFile, String pieceNumber) throws IOException;

    /**
     * 处理文件下载流程
     *
     * @param fileId
     */
    void fileDownloadHandler(String fileId, String tag, HttpServletResponse response) throws IOException;

    /**
     * 处理文件删除流程
     *
     * @param fileId
     * @param pieceNumber
     * @return
     */
    Boolean deleteFile(String fileId, String pieceNumber);
}
