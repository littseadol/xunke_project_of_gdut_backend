package com.ruoyi.lab.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 周报对象 ai_week_report
 * 
 * @author ruoyi
 * @date 2025-07-17
 */
public class AiWeekReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 周报ID */
    private Long weekReportId;

    @Excel(name = "周报编号")
    private String weekReportNumber;

    /** 周报评分 */
    @Excel(name = "周报评分")
    private Double weekReportScore;

    /** 周报内容 */
    @Excel(name = "周报内容")
    private String weekReportContent;

    @Excel(name = "房间名称")
    private String roomName;

    @Excel(name = "所在楼层")
    private Long buildingFloor;

    @Excel(name = "楼栋名称")
    private String buildingName;

    @Excel(name = "团队名称")
    private String teamName;

    public String getWeekReportNumber() {
        return weekReportNumber;
    }

    public void setWeekReportNumber(String weekReportNumber) {
        this.weekReportNumber = weekReportNumber;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Long getBuildingFloor() {
        return buildingFloor;
    }

    public void setBuildingFloor(Long buildingFloor) {
        this.buildingFloor = buildingFloor;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setWeekReportId(Long weekReportId)
    {
        this.weekReportId = weekReportId;
    }

    public Long getWeekReportId() 
    {
        return weekReportId;
    }

    public void setWeekReportScore(Double weekReportScore)
    {
        this.weekReportScore = weekReportScore;
    }

    public Double getWeekReportScore()
    {
        return weekReportScore;
    }

    public void setWeekReportContent(String weekReportContent) 
    {
        this.weekReportContent = weekReportContent;
    }

    public String getWeekReportContent() 
    {
        return weekReportContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("weekReportId", getWeekReportId())
            .append("weekReportScore", getWeekReportScore())
            .append("weekReportContent", getWeekReportContent())
            .append("createTime", getCreateTime())
            .toString();
    }
}
