package com.ruoyi.lab.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 月报对象 ai_month_report
 * 
 * @author ruoyi
 * @date 2025-07-17
 */
public class AiMonthReport extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 月报ID */
    private Long monthReportId;

    @Excel(name = "月报编号")
    private String monthReportNumber;

    /** 月报评分 */
    @Excel(name = "月报评分")
    private Double monthReportScore;

    /** 月报内容 */
    @Excel(name = "月报内容")
    private String monthReportContent;

    @Excel(name = "所属房间")
    private String roomName;

    @Excel(name = "所属楼栋")
    private String buildingName;

    @Excel(name = "所属楼层")
    private Long buildingFloor;

    @Excel(name = "所属团队")
    private String teamName;

    public String getMonthReportNumber() {
        return monthReportNumber;
    }

    public void setMonthReportNumber(String monthReportNumber) {
        this.monthReportNumber = monthReportNumber;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public Long getBuildingFloor() {
        return buildingFloor;
    }

    public void setBuildingFloor(Long buildingFloor) {
        this.buildingFloor = buildingFloor;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setMonthReportId(Long monthReportId)
    {
        this.monthReportId = monthReportId;
    }

    public Long getMonthReportId() 
    {
        return monthReportId;
    }

    public void setMonthReportScore(Double monthReportScore) 
    {
        this.monthReportScore = monthReportScore;
    }

    public Double getMonthReportScore() 
    {
        return monthReportScore;
    }

    public void setMonthReportContent(String monthReportContent) 
    {
        this.monthReportContent = monthReportContent;
    }

    public String getMonthReportContent() 
    {
        return monthReportContent;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("monthReportId", getMonthReportId())
            .append("monthReportScore", getMonthReportScore())
            .append("monthReportContent", getMonthReportContent())
            .append("createTime", getCreateTime())
            .toString();
    }
}
