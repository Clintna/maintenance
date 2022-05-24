package com.bhhyy.aircraft.maintenance.archives.controller;

import com.bhhyy.aircraft.maintenance.archives.entity.ArFileDeleteDTO;
import com.bhhyy.aircraft.maintenance.archives.enums.FileOptionsEnum;
import com.bhhyy.aircraft.maintenance.archives.handler.impl.FileProcessHandlerServiceImpl;
import com.bhhyy.aircraft.maintenance.archives.service.ArFileInfoService;
import com.bhhyy.aircraft.maintenance.common.controller.BaseController;
import com.bhhyy.aircraft.maintenance.common.entity.FlightResponse;
import com.bhhyy.aircraft.maintenance.common.entity.QueryRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.IOException;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jinglin
 * @since 2022-05-11
 */
@RestController
@RequestMapping("/arFileInfo")
@RequiredArgsConstructor
public class ArFileInfoController extends BaseController {

    @Resource
    private ArFileInfoService arFileInfoService;

    @Resource
    private FileProcessHandlerServiceImpl fileProcessHandlerService;

    @PostMapping("upload")
    public FlightResponse uploadFile(MultipartHttpServletRequest request) throws IOException {
        String pieceNumber = request.getParameter("pieceNumber");
        if (null == pieceNumber){
            throw new RuntimeException("缺少件号信息");
        }
        List<MultipartFile> files = request.getFiles("multipartFile");
        MultipartFile multipartFile = files.get(0);
        return new FlightResponse().success().put("fileId", fileProcessHandlerService.uploadFile(multipartFile, pieceNumber));
    }

    @PostMapping("download")
    public void downloadFile(String fileId, HttpServletResponse response) throws IOException {
        fileProcessHandlerService.fileDownloadHandler(fileId, FileOptionsEnum.FILE_DOWNLOAD.getCode(), response);
    }

    /**
     * 预览（调用下载方法，传入tag标识为下载或预览
     *
     * @param fileId
     * @param response
     * @throws IOException
     */
    @PostMapping("preview")
    public void previewFile(String fileId, HttpServletResponse response) throws IOException {
        fileProcessHandlerService.fileDownloadHandler(fileId, FileOptionsEnum.FILE_PREVIEW.getCode(), response);
    }

    @PostMapping("delete")
    public FlightResponse delete(@RequestBody ArFileDeleteDTO arFileDeleteDTO){
        fileProcessHandlerService.deleteFile(arFileDeleteDTO.getFileId(),arFileDeleteDTO.getPieceNumber());
        return new FlightResponse().success();
    }

    @PostMapping("list")
    public FlightResponse userList(QueryRequest request) {
        return new FlightResponse().success()
                .data(getDataTable(arFileInfoService.queryArFileInfoList(request)));
    }
}
