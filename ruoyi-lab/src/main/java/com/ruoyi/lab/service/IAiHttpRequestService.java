package com.ruoyi.lab.service;

import java.io.InputStream;

public interface IAiHttpRequestService {
    String getRequest(String url);
    <T> T postRequest(String url, Object request, Class<T> responseType);
    String download(String url, String suffix);
}