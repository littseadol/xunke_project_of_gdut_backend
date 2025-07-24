package com.ruoyi.lab.service.impl;

import com.ruoyi.lab.service.IAiHttpRequestService;
import com.ruoyi.lab.service.IAiMediaService;
import com.ruoyi.lab.service.IAiMinioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AiMediaServiceImpl implements IAiMediaService {
    @Autowired
    private IAiHttpRequestService httpRequestService;

    @Value("${media.ip}")
    private String ip;

    @Value("${media.port}")
    private String port;

    @Value("${media.secret}")
    private String secret;

    @Autowired
    private IAiMinioService iAiMinioService;

    public void addStreamProxy(String stream, String rtspUrl) {
        // 必须设置自动关闭
        String url = "http://%s:%s/index/api/addStreamProxy?secret=%s&vhost=__defaultVhost__&app=proxy&stream=%s&url=%s&auto_close=false";
        url = String.format(url, ip, port, secret, stream, rtspUrl);
        log.debug("url={}", url);
        String response = httpRequestService.getRequest(url);
        log.debug("response={}", response);
    }

    public void deleteStreamProxy(String stream) {
        String url = "http://%s:%s/index/api/delStreamProxy?secret=%s&key=__defaultVhost__/proxy/%s";
        url = String.format(url, ip, port, secret, stream);
        log.debug("url={}", url);
        String response = httpRequestService.getRequest(url);
        log.debug("response={}", response);
    }

    public String getLiveUrlByStream(String stream) {
        String liveUrl = "http://%s:%s/proxy/%s.live.mp4";
        liveUrl = String.format(liveUrl, ip, port, stream);
        return liveUrl;
    }

    @Override
    public String getSnap(String rtspUrl) {
        try {
            String url = "http://%s:%s/index/api/getSnap?secret=%s&url=%s&timeout_sec=10&expire_sec=30";
            url = String.format(url, ip, port, secret, rtspUrl);
            log.debug("url={}", url);
            String filePath = httpRequestService.download(url, "jpg");
            log.debug("filePath={}", filePath);

            String type = "device-snapshot";
            return iAiMinioService.putObject(type, filePath);
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}