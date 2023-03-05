package com.lixin.campusforum.service.impl;

import com.lixin.campusforum.common.exception.ForumSystemException;
import com.lixin.campusforum.common.exception.NotExpectedException;
import com.lixin.campusforum.common.result.DataResult;
import com.lixin.campusforum.common.utils.ResultUtils;
import com.lixin.campusforum.config.UploadFileConfig;
import com.lixin.campusforum.model.vo.FileUploadVo;
import com.lixin.campusforum.service.FileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author lixin
 */
@Slf4j
@Service("fileService")
public class FileServiceImpl implements FileService {

    private final UploadFileConfig uploadFileConfig;

    public FileServiceImpl(UploadFileConfig uploadFileConfig) {
        this.uploadFileConfig = uploadFileConfig;
    }

    @Override
    public DataResult<FileUploadVo> save(MultipartFile file) {
        if (file == null) {
            throw new NotExpectedException("file is null.");
        }
        String originalFilename = file.getOriginalFilename();
        if (originalFilename == null) {
            throw new NotExpectedException("originalFilename is null.");
        }
        String suffix = originalFilename.substring(originalFilename.lastIndexOf('.'));
        String filename = UUID.randomUUID().toString()
                .replace("-", "") + suffix;
        String path = uploadFileConfig.getImagePath();
        File dest = new File(path + "/" + filename);
        File directory = dest.getParentFile();
        if (!directory.exists()) {
            if (!directory.mkdirs()) {
                throw new ForumSystemException("create directory fail.");
            }
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            log.error("file save fail: ", e);
            throw new ForumSystemException("file save fail.");
        }
        FileUploadVo vo = new FileUploadVo();
        vo.setPath(filename);
        return ResultUtils.ok(vo);
    }

}
