package com.ruoyi.lab.service.impl;

import com.ruoyi.lab.domain.AiDevice;
import com.ruoyi.lab.domain.AiDeviceAlgorithm;
import com.ruoyi.lab.mapper.AiDeviceMapper;
import com.ruoyi.lab.service.IAiDeviceService;
import com.ruoyi.lab.service.IAiMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AiDeviceServiceImpl implements IAiDeviceService {

    @Autowired
    private AiDeviceMapper aiDeviceMapper;

    @Autowired
    private IAiMediaService mediaService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addDevice(AiDevice device, List<AiDeviceAlgorithm> aiDeviceAlgorithmList) {

        // 入库
        aiDeviceMapper.addDevice(device);
        for (AiDeviceAlgorithm aiDeviceAlgorithm : aiDeviceAlgorithmList) {
            aiDeviceAlgorithm.setDeviceId(device.getDeviceId());
            aiDeviceMapper.addDeviceAlgorithm(aiDeviceAlgorithm);
        }
        aiDeviceMapper.insertAiDeviceGroup(device.getDeviceId(), device.getGroupId());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateDevice(AiDevice device, List<AiDeviceAlgorithm> aiDeviceAlgorithmList) {
        aiDeviceMapper.updateDevice(device);
        aiDeviceMapper.updateDeviceGroup(device.getDeviceId(), device.getGroupId());
        for (AiDeviceAlgorithm aiDeviceAlgorithm : aiDeviceAlgorithmList) {
            aiDeviceMapper.updateDeviceAlgorithm(aiDeviceAlgorithm);
        }
    }

    @Override
    public List<AiDevice> getDeviceList(AiDevice aiDeviceFilter) {
        List<AiDevice> aiDeviceList = aiDeviceMapper.getDeviceList(aiDeviceFilter);
        for (AiDevice aiDevice : aiDeviceList) {
            aiDevice.setLiveUrl(mediaService.getLiveUrlByStream(String.valueOf(aiDevice.getDeviceId())));
        }
        return aiDeviceList;
    }

    @Override
    public List<AiDevice> selectAllDeviceList() {
        return aiDeviceMapper.selectAllDeviceList();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDevice(Long[] deviceIds) {
        for (Long deviceId : deviceIds) {
            aiDeviceMapper.deleteAiDeviceGroup(deviceId); // 删除摄像头对应的分组数据
            aiDeviceMapper.deleteDeviceAlgorithm(deviceId); // 删除摄像头对应的算法数据
            aiDeviceMapper.deleteDevice(deviceId); // 删除摄像头数据
        }
    }

    @Override
    public List<AiDeviceAlgorithm> getDeviceAlgorithmListByDeviceId(Long deviceId) {
        return aiDeviceMapper.getDeviceAlgorithmListByDeviceId(deviceId);
    }

    @Override
    public void updateCheckStatus(AiDevice aiDevice) {
        aiDeviceMapper.updateCheckStatus(aiDevice);
        if (aiDevice.getCheckStatus() == 0) {
            String rtspUrl = aiDeviceMapper.getRtspUrlByDeviceId(aiDevice.getDeviceId());
            mediaService.addStreamProxy(String.valueOf(aiDevice.getDeviceId()), rtspUrl);
        } else {
            mediaService.deleteStreamProxy(String.valueOf(aiDevice.getDeviceId()));
        }
    }

    @Override
    public List<AiDevice> getCheckedDeviceList() {
        List<AiDevice> aiDeviceList = aiDeviceMapper.getCheckedDeviceList();
        for (AiDevice aiDevice : aiDeviceList) {
            aiDevice.setLiveUrl(mediaService.getLiveUrlByStream(String.valueOf(aiDevice.getDeviceId())));
        }
        return aiDeviceList;
    }

    @Override
    public void addStreamProxy(AiDevice aiDevice) {
        // 到流媒体服务器注册代理
        mediaService.addStreamProxy(String.valueOf(aiDevice.getDeviceId()), aiDevice.getRtspUrl());
    }

    @Override
    public int updateCoordinate(AiDevice aiDevice) {
        return aiDeviceMapper.updateCoordinate(aiDevice);
    }
}