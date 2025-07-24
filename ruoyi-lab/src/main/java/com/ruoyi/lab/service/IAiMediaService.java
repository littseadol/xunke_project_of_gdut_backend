package com.ruoyi.lab.service;

import java.io.InputStream;

public interface IAiMediaService {
    void addStreamProxy(String stream, String rtspUrl);
    void deleteStreamProxy(String stream);
    String getLiveUrlByStream(String stream);
    String getSnap(String rtspUrl);
}