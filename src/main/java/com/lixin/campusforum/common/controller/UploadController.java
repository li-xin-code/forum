package com.lixin.campusforum.common.controller;

import com.lixin.campusforum.common.annotation.LoginRequired;
import com.lixin.campusforum.common.config.UploadFileConfig;
import com.lixin.campusforum.common.exception.NotExpectedException;
import com.lixin.campusforum.common.exception.SystemException;
import com.lixin.campusforum.common.result.Result;
import com.lixin.campusforum.common.utils.ResultUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author lixin
 */
@RestController
@RequestMapping("/upload")
public class UploadController {

    private final UploadFileConfig uploadFileConfig;

    public UploadController(UploadFileConfig uploadFileConfig) {
        this.uploadFileConfig = uploadFileConfig;
    }

    @LoginRequired
    @PostMapping
    public Result<String> upload(@RequestParam MultipartFile file) {
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
        if (!dest.getParentFile().exists()) {
            boolean b = dest.getParentFile().mkdirs();
            if (!b) {
                throw new SystemException("mkdirs fail.");
            }
        }
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new SystemException("file save fail.");
        }
        return ResultUtils.ok("path", filename);
    }

}

