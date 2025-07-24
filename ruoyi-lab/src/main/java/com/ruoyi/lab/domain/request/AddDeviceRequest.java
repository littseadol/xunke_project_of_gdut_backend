package com.ruoyi.lab.domain.request;

import com.ruoyi.lab.domain.AiDevice;
import com.ruoyi.lab.domain.AiDeviceAlgorithm;

import java.util.List;

public class AddDeviceRequest {
    private AiDevice aiDevice;
    private List<AiDeviceAlgorithm> aiDeviceAlgorithmList;

    public AiDevice getAiDevice() {
        return aiDevice;
    }

    public void setAiDevice(AiDevice aiDevice) {
        this.aiDevice = aiDevice;
    }

    public List<AiDeviceAlgorithm> getAiDeviceAlgorithmList() {
        return aiDeviceAlgorithmList;
    }

    public void setAiDeviceAlgorithmList(List<AiDeviceAlgorithm> aiDeviceAlgorithmList) {
        this.aiDeviceAlgorithmList = aiDeviceAlgorithmList;
    }
}