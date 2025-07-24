package com.ruoyi.lab.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * event对象 ai_alarm_event
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
public class AiAlarmEvent extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 告警ID */
    private Long eventId;

    /** 告警图片 */
    @Excel(name = "告警图片")
    private String alarmPicture;

    /** 告警短视频 */
    @Excel(name = "告警短视频")
    private String alarmVideo;

    /** 设备ID */
    @Excel(name = "设备ID")
    private Long deviceId;

    /** 事件类型 */
    @Excel(name = "事件类型")
    private Long eventType;

    /** 事件级别 */
    @Excel(name = "事件级别")
    private Long eventLevel;

    /** 事件状态 */
    @Excel(name = "事件状态")
    private Long eventStatus;

    /** 处理时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "处理时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date handleTime;

    /** 处理意见 */
    @Excel(name = "处理意见")
    private String handleOpinion;

    public void setEventId(Long eventId) 
    {
        this.eventId = eventId;
    }

    public Long getEventId() 
    {
        return eventId;
    }

    public void setAlarmPicture(String alarmPicture) 
    {
        this.alarmPicture = alarmPicture;
    }

    public String getAlarmPicture() 
    {
        return alarmPicture;
    }

    public void setAlarmVideo(String alarmVideo) 
    {
        this.alarmVideo = alarmVideo;
    }

    public String getAlarmVideo() 
    {
        return alarmVideo;
    }

    public void setDeviceId(Long deviceId) 
    {
        this.deviceId = deviceId;
    }

    public Long getDeviceId() 
    {
        return deviceId;
    }

    public void setEventType(Long eventType) 
    {
        this.eventType = eventType;
    }

    public Long getEventType() 
    {
        return eventType;
    }

    public void setEventLevel(Long eventLevel) 
    {
        this.eventLevel = eventLevel;
    }

    public Long getEventLevel() 
    {
        return eventLevel;
    }

    public void setEventStatus(Long eventStatus) 
    {
        this.eventStatus = eventStatus;
    }

    public Long getEventStatus() 
    {
        return eventStatus;
    }

    public void setHandleTime(Date handleTime) 
    {
        this.handleTime = handleTime;
    }

    public Date getHandleTime() 
    {
        return handleTime;
    }

    public void setHandleOpinion(String handleOpinion) 
    {
        this.handleOpinion = handleOpinion;
    }

    public String getHandleOpinion() 
    {
        return handleOpinion;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("eventId", getEventId())
            .append("alarmPicture", getAlarmPicture())
            .append("alarmVideo", getAlarmVideo())
            .append("deviceId", getDeviceId())
            .append("eventType", getEventType())
            .append("eventLevel", getEventLevel())
            .append("eventStatus", getEventStatus())
            .append("handleTime", getHandleTime())
            .append("handleOpinion", getHandleOpinion())
            .append("createTime", getCreateTime())
            .toString();
    }
}
