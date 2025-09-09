package com.ruoyi.lab.domain;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 录像管理对象 ai_video_record
 * 
 * @author littseadol
 * @date 2025-08-29
 */
public class AiVideoRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 录像ID */
    private Long recordId;

    /** 关联设备ID */
    @Excel(name = "关联设备ID")
    private Long deviceId;

    /** 录像名称 */
    @Excel(name = "录像名称")
    private String recordName;

    /** 录像存储路径 */
    @Excel(name = "录像存储路径")
    private String recordPath;

    /** 录像访问URL */
    @Excel(name = "录像访问URL")
    private String recordUrl;

    /** 录像开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "录像开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startTime;

    /** 录像结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "录像结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endTime;

    /** 录像时长(秒) */
    @Excel(name = "录像时长(秒)")
    private Long duration;

    /** 文件大小(字节) */
    @Excel(name = "文件大小(字节)")
    private Long fileSize;

    /** 分辨率(如1920x1080) */
    @Excel(name = "分辨率(如1920x1080)")
    private String resolution;

    /** 录像类型(1-定时录像 2-事件录像 3-手动录像) */
    @Excel(name = "录像类型(1-定时录像 2-事件录像 3-手动录像)")
    private Long recordType;

    /** 状态(0-未处理 1-已处理 2-处理中 3-已删除) */
    @Excel(name = "状态(0-未处理 1-已处理 2-处理中 3-已删除)")
    private Long status;

    /** $table.subTable.functionName信息 */
    private List<AiDevice> aiDeviceList;

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }

    public void setDeviceId(Long deviceId) 
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() 
    {
        return deviceId;
    }

    public void setRecordName(String recordName) 
    {
        this.recordName = recordName;
    }

    public String getRecordName() 
    {
        return recordName;
    }

    public void setRecordPath(String recordPath) 
    {
        this.recordPath = recordPath;
    }

    public String getRecordPath() 
    {
        return recordPath;
    }

    public void setRecordUrl(String recordUrl) 
    {
        this.recordUrl = recordUrl;
    }

    public String getRecordUrl() 
    {
        return recordUrl;
    }

    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }

    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }

    public void setDuration(Long duration) 
    {
        this.duration = duration;
    }

    public Long getDuration() 
    {
        return duration;
    }

    public void setFileSize(Long fileSize) 
    {
        this.fileSize = fileSize;
    }

    public Long getFileSize() 
    {
        return fileSize;
    }

    public void setResolution(String resolution) 
    {
        this.resolution = resolution;
    }

    public String getResolution() 
    {
        return resolution;
    }

    public void setRecordType(Long recordType) 
    {
        this.recordType = recordType;
    }

    public Long getRecordType() 
    {
        return recordType;
    }

    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }

    public List<AiDevice> getAiDeviceList()
    {
        return aiDeviceList;
    }

    public void setAiDeviceList(List<AiDevice> aiDeviceList)
    {
        this.aiDeviceList = aiDeviceList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("recordId", getRecordId())
            .append("deviceId", getDeviceId())
            .append("recordName", getRecordName())
            .append("recordPath", getRecordPath())
            .append("recordUrl", getRecordUrl())
            .append("startTime", getStartTime())
            .append("endTime", getEndTime())
            .append("duration", getDuration())
            .append("fileSize", getFileSize())
            .append("resolution", getResolution())
            .append("recordType", getRecordType())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("createBy", getCreateBy())
            .append("updateBy", getUpdateBy())
            .append("remark", getRemark())
            .append("aiDeviceList", getAiDeviceList())
            .toString();
    }
}
