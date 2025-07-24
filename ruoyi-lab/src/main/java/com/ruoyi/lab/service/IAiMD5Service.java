package com.ruoyi.lab.service;

import java.io.InputStream;

public interface IAiMD5Service {
    String calculateMD5(InputStream inputStream) throws Exception;
}