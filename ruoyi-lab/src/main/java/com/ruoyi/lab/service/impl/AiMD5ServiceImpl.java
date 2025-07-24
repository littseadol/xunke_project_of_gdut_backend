package com.ruoyi.lab.service.impl;

import com.ruoyi.lab.service.IAiMD5Service;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.security.MessageDigest;

@Service
public class AiMD5ServiceImpl implements IAiMD5Service {
    public String calculateMD5(InputStream inputStream) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] buffer = new byte[8192];
        int length;
        while ((length = inputStream.read(buffer)) != -1) {
            md.update(buffer, 0, length);
        }
        byte[] digest = md.digest();

        StringBuilder sb = new StringBuilder();
        for (byte b : digest) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
