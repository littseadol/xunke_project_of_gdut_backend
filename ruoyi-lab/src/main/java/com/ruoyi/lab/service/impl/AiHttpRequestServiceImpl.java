package com.ruoyi.lab.service.impl;

import com.ruoyi.lab.service.IAiHttpRequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
@Slf4j
public class AiHttpRequestServiceImpl implements IAiHttpRequestService {

    private final RestTemplate restTemplate = new RestTemplate();
    
    public String getRequest(String url) {
        return restTemplate.getForObject(url, String.class);
    }
    
    public <T> T postRequest(String url, Object request, Class<T> responseType) {
        return restTemplate.postForObject(url, request, responseType);
    }

    @Override
    public String download(String url, String suffix) {
        try {
            byte[] bytes = restTemplate.getForObject(url, byte[].class);
            String filePath = "C:/Users/Ldolphin/Desktop/temp/" + UUID.randomUUID().toString().replace("-", "") + "." + suffix;
            FileOutputStream out = new FileOutputStream(filePath);
            out.write(bytes);
            return filePath;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}