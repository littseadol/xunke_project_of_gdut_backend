package com.ruoyi.lab.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface IAiMinioService {
    Boolean bucketExists();
    String putObject(String type, String filePath);

    public String store(MultipartFile file) throws Exception;
}