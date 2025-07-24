package com.ruoyi.lab.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * level对象 ai_alarm_level
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
public class AiAlarmLevel extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 告警等级ID */
    private Long alarmLevelId;

    /** 告警等级名称 */
    @Excel(name = "告警等级名称")
    private String alarmLevelName;

    /** 告警等级颜色 */
    @Excel(name = "告警等级颜色")
    private String alarmLevelColor;

    /** 告警等级展示方式 */
    @Excel(name = "告警等级展示方式")
    private String alarmLevelDemonstrations;

    public void setAlarmLevelId(Long alarmLevelId) 
    {
        this.alarmLevelId = alarmLevelId;
    }

    public Long getAlarmLevelId() 
    {
        return alarmLevelId;
    }

    public void setAlarmLevelName(String alarmLevelName) 
    {
        this.alarmLevelName = alarmLevelName;
    }

    public String getAlarmLevelName() 
    {
        return alarmLevelName;
    }

    public void setAlarmLevelColor(String alarmLevelColor) 
    {
        this.alarmLevelColor = alarmLevelColor;
    }

    public String getAlarmLevelColor() 
    {
        return alarmLevelColor;
    }

    public void setAlarmLevelDemonstrations(String alarmLevelDemonstrations) 
    {
        this.alarmLevelDemonstrations = alarmLevelDemonstrations;
    }

    public String getAlarmLevelDemonstrations() 
    {
        return alarmLevelDemonstrations;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("alarmLevelId", getAlarmLevelId())
            .append("alarmLevelName", getAlarmLevelName())
            .append("alarmLevelColor", getAlarmLevelColor())
            .append("alarmLevelDemonstrations", getAlarmLevelDemonstrations())
            .append("createTime", getCreateTime())
            .toString();
    }
}
