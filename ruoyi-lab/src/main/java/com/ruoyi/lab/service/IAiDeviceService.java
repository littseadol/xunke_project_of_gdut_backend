package com.ruoyi.lab.service;

import com.ruoyi.lab.domain.AiDevice;
import com.ruoyi.lab.domain.AiDeviceAlgorithm;

import java.util.List;

public interface IAiDeviceService {
    void addDevice(AiDevice device, List<AiDeviceAlgorithm> aiDeviceAlgorithmList);
    void updateDevice(AiDevice device, List<AiDeviceAlgorithm> aiDeviceAlgorithmList);
    List<AiDevice> getDeviceList(AiDevice aiDeviceFilter);
    List<AiDevice> selectAllDeviceList();
    void deleteDevice(Long[] deviceIds);
    List<AiDeviceAlgorithm> getDeviceAlgorithmListByDeviceId(Long deviceId);
    void updateCheckStatus(AiDevice aiDevice);
    List<AiDevice> getCheckedDeviceList();
    void addStreamProxy(AiDevice aiDevice);

    // 更新坐标
    int updateCoordinate(AiDevice aiDevice);
}