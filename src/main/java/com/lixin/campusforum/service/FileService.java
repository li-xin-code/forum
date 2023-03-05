package com.lixin.campusforum.service;

import com.lixin.campusforum.common.result.DataResult;
import com.lixin.campusforum.model.vo.FileUploadVo;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lixin
 */
public interface FileService {
    /**
     * ...
     *
     * @param file file
     * @return path
     */
    DataResult<FileUploadVo> save(MultipartFile file);
}
