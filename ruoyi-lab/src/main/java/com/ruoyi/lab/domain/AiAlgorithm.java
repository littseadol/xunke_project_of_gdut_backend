package com.ruoyi.lab.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 算法对象 ai_algorithm
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
public class AiAlgorithm extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 算法ID */
    private Long algorithmId;

    /** 算法名称 */
    @Excel(name = "算法名称")
    private String algorithmName;

    /** 算法平台 */
    @Excel(name = "算法平台")
    private Long algorithmPlatform;

    /** 算法图片 */
    @Excel(name = "算法图片")
    private String algorithmPicture;

    /** 算法文案 */
    @Excel(name = "算法文案")
    private String algorithmCopywriting;

    /** 告警等级ID */
    @Excel(name = "告警等级ID")
    private Long alarmLevelId;

    /** 告警等级名称 */
    @Excel(name = "告警等级名称")
    private String alarmLevelName;

    public Long getAlarmLevelId() {
        return alarmLevelId;
    }

    public void setAlarmLevelId(Long alarmLevelId) {
        this.alarmLevelId = alarmLevelId;
    }

    public String getAlarmLevelName() {
        return alarmLevelName;
    }

    public void setAlarmLevelName(String alarmLevelName) {
        this.alarmLevelName = alarmLevelName;
    }

    public void setAlgorithmId(Long algorithmId)
    {
        this.algorithmId = algorithmId;
    }

    public Long getAlgorithmId() 
    {
        return algorithmId;
    }

    public void setAlgorithmName(String algorithmName) 
    {
        this.algorithmName = algorithmName;
    }

    public String getAlgorithmName() 
    {
        return algorithmName;
    }

    public void setAlgorithmPlatform(Long algorithmPlatform) 
    {
        this.algorithmPlatform = algorithmPlatform;
    }

    public Long getAlgorithmPlatform() 
    {
        return algorithmPlatform;
    }

    public void setAlgorithmPicture(String algorithmPicture) 
    {
        this.algorithmPicture = algorithmPicture;
    }

    public String getAlgorithmPicture() 
    {
        return algorithmPicture;
    }

    public void setAlgorithmCopywriting(String algorithmCopywriting) 
    {
        this.algorithmCopywriting = algorithmCopywriting;
    }

    public String getAlgorithmCopywriting() 
    {
        return algorithmCopywriting;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("algorithmId", getAlgorithmId())
            .append("algorithmName", getAlgorithmName())
            .append("algorithmPlatform", getAlgorithmPlatform())
            .append("algorithmPicture", getAlgorithmPicture())
            .append("algorithmCopywriting", getAlgorithmCopywriting())
                .append("alarmLevelId", getAlarmLevelId())
                .append("alarmLevelName", getAlarmLevelName())
            .append("createTime", getCreateTime())
            .toString();
    }
}
