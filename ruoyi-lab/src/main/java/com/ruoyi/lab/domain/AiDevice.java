package com.ruoyi.lab.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.util.List;

public class AiDevice extends BaseEntity {

    @Excel(name="设备ID")
    private Long deviceId;

    @Excel(name="设备名称")
    private String deviceName;

    @Excel(name = "分组ID")
    private Long groupId;

    @Excel(name="分组名称")
    private String groupName;

    @Excel(name = "关联算法个数")
    private Integer algorithmNumber;

    @Excel(name="rtsp流地址")
    private String rtspUrl;

    @Excel(name = "快照地址")
    private String snapshotUrl;

    @Excel(name = "识别间隔")
    private Integer identifyInterval;

    @Excel(name = "告警间隔")
    private Integer alarmInterval;

    @Excel(name = "经度")
    private Double deviceLng;

    @Excel(name = "纬度")
    private Double deviceLat;

    private String liveUrl;
    private Integer checkStatus;
    private List<AiDeviceAlgorithm> deviceAlgorithmList;

    public Double getDeviceLng() {
        return deviceLng;
    }

    public void setDeviceLng(Double deviceLng) {
        this.deviceLng = deviceLng;
    }

    public Double getDeviceLat() {
        return deviceLat;
    }

    public void setDeviceLat(Double deviceLat) {
        this.deviceLat = deviceLat;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getAlgorithmNumber() {
        return algorithmNumber;
    }

    public void setAlgorithmNumber(Integer algorithmNumber) {
        this.algorithmNumber = algorithmNumber;
    }

    public String getRtspUrl() {
        return rtspUrl;
    }

    public void setRtspUrl(String rtspUrl) {
        this.rtspUrl = rtspUrl;
    }

    public String getSnapshotUrl() {
        return snapshotUrl;
    }

    public void setSnapshotUrl(String snapshotUrl) {
        this.snapshotUrl = snapshotUrl;
    }

    public Integer getIdentifyInterval() {
        return identifyInterval;
    }

    public void setIdentifyInterval(Integer identifyInterval) {
        this.identifyInterval = identifyInterval;
    }

    public Integer getAlarmInterval() {
        return alarmInterval;
    }

    public void setAlarmInterval(Integer alarmInterval) {
        this.alarmInterval = alarmInterval;
    }

    public String getLiveUrl() {
        return liveUrl;
    }

    public void setLiveUrl(String liveUrl) {
        this.liveUrl = liveUrl;
    }

    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public List<AiDeviceAlgorithm> getDeviceAlgorithmList() {
        return deviceAlgorithmList;
    }

    public void setDeviceAlgorithmList(List<AiDeviceAlgorithm> deviceAlgorithmList) {
        this.deviceAlgorithmList = deviceAlgorithmList;
    }
}
