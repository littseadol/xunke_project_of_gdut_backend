package com.ruoyi.lab.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 视频评价对象 ai_video_evaluation
 * 
 * @author Ldolphin
 * @date 2025-07-23
 */
public class AiVideoEvaluation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 评价ID */
    private Long id;

    /** 摄像头ID */
    @Excel(name = "摄像头ID")
    private String deviceId;
    

    /** 评分(1-5) */
    @Excel(name = "评分(1-5)")
    private int score;

    /** 评价内容 */
    @Excel(name = "评价内容")
    private String content;

    /** 改进建议 */
    @Excel(name = "改进建议")
    private String suggestion;

    /** 截图路径 */
    @Excel(name = "截图路径")
    private String snapshotPath;

    /** 截图时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截图时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date snapshotTime;

    /** 视频时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "视频时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date videoTime;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setDeviceId(String deviceId)
    {
        this.deviceId = deviceId;
    }

    public String getdeviceId() 
    {
        return deviceId;
    }




    public void setScore(int score)
    {
        this.score = score;
    }

    public int getScore()
    {
        return score;
    }

    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }

    public void setSuggestion(String suggestion) 
    {
        this.suggestion = suggestion;
    }

    public String getSuggestion() 
    {
        return suggestion;
    }

    public void setSnapshotPath(String snapshotPath) 
    {
        this.snapshotPath = snapshotPath;
    }

    public String getSnapshotPath() 
    {
        return snapshotPath;
    }

    public void setSnapshotTime(Date snapshotTime) 
    {
        this.snapshotTime = snapshotTime;
    }

    public Date getSnapshotTime() 
    {
        return snapshotTime;
    }

    public void setVideoTime(Date videoTime) 
    {
        this.videoTime = videoTime;
    }

    public Date getVideoTime() 
    {
        return videoTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceId", getdeviceId())
            .append("score", getScore())
            .append("content", getContent())
            .append("suggestion", getSuggestion())
            .append("snapshotPath", getSnapshotPath())
            .append("snapshotTime", getSnapshotTime())
            .append("videoTime", getVideoTime())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
