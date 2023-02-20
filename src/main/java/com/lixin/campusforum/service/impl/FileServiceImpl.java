package com.lixin.campusforum.service.impl;

import com.lixin.campusforum.service.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lixin
 */
@Service("fileService")
public class FileServiceImpl implements FileService {

    @Override
    public String save(MultipartFile file, String path) {
        

        return null;
    }

}
