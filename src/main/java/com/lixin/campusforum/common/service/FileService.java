package com.lixin.campusforum.common.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author lixin
 */
public interface FileService {
    /**
     * ...
     *
     * @param file file
     * @param path path
     * @return path
     */
    String save(MultipartFile file, String path);
}
