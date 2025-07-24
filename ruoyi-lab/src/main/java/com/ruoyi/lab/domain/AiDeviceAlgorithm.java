package com.ruoyi.lab.domain;

public class AiDeviceAlgorithm {
    private Integer id;
    private Long deviceId;
    private Integer algorithmId;
    private String algorithmName;
    private Integer status;
    private Double sensitivity;
    private String pixels;
    private String alarmTimes;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Integer getAlgorithmId() {
        return algorithmId;
    }

    public void setAlgorithmId(Integer algorithmId) {
        this.algorithmId = algorithmId;
    }

    public String getAlgorithmName() {
        return algorithmName;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getSensitivity() {
        return sensitivity;
    }

    public void setSensitivity(Double sensitivity) {
        this.sensitivity = sensitivity;
    }

    public String getPixels() {
        return pixels;
    }

    public void setPixels(String pixels) {
        this.pixels = pixels;
    }

    public String getAlarmTimes() {
        return alarmTimes;
    }

    public void setAlarmTimes(String alarmTimes) {
        this.alarmTimes = alarmTimes;
    }
}
