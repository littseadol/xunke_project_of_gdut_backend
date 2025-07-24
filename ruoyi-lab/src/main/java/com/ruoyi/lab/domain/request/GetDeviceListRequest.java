package com.ruoyi.lab.domain.request;

import com.ruoyi.lab.domain.AiDevice;

public class GetDeviceListRequest {
    private Integer pageNum;
    private Integer pageSize;
    private AiDevice aiDevice;

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public AiDevice getAiDevice() {
        return aiDevice;
    }

    public void setAiDevice(AiDevice aiDevice) {
        this.aiDevice = aiDevice;
    }
}
