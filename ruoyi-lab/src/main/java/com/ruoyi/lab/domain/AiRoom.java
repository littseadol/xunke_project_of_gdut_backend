package com.ruoyi.lab.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 房间对象 ai_room
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
public class AiRoom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 房间ID */
    private Long roomId;

    /** 房间名字 */
    @Excel(name = "房间名字")
    private String roomName;

    /** 房间类型 */
    @Excel(name = "房间类型")
    private Long roomType;

    /** 负责人姓名 */
    @Excel(name = "负责人姓名")
    private String chargeName;

    /** 负责人联系方式 */
    @Excel(name = "负责人联系方式")
    private String chargePhone;

    /** 负责人工号 */
    @Excel(name = "负责人工号")
    private String chargeWorkNumber;

    /** 楼栋ID */
    @Excel(name = "楼栋ID")
    private Long buildingId;

    /** 楼栋名称 */
    @Excel(name = "楼栋名称")
    private String buildingName;

    /** 所在楼层 */
    @Excel(name = "所在楼层")
    private Long buildingFloor;

    /** 团队ID */
    @Excel(name = "团队ID")
    private Long teamId;

    /** 团队名称 */
    @Excel(name = "团队名称")
    private String teamName;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Long getBuildingFloor() {
        return buildingFloor;
    }

    public void setBuildingFloor(Long buildingFloor) {
        this.buildingFloor = buildingFloor;
    }

    public Long getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public void setRoomId(Long roomId)
    {
        this.roomId = roomId;
    }

    public Long getRoomId() 
    {
        return roomId;
    }

    public void setRoomName(String roomName) 
    {
        this.roomName = roomName;
    }

    public String getRoomName() 
    {
        return roomName;
    }

    public void setRoomType(Long roomType) 
    {
        this.roomType = roomType;
    }

    public Long getRoomType() 
    {
        return roomType;
    }

    public void setChargeName(String chargeName) 
    {
        this.chargeName = chargeName;
    }

    public String getChargeName() 
    {
        return chargeName;
    }

    public void setChargePhone(String chargePhone) 
    {
        this.chargePhone = chargePhone;
    }

    public String getChargePhone() 
    {
        return chargePhone;
    }

    public void setChargeWorkNumber(String chargeWorkNumber) 
    {
        this.chargeWorkNumber = chargeWorkNumber;
    }

    public String getChargeWorkNumber() 
    {
        return chargeWorkNumber;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roomId", getRoomId())
            .append("roomName", getRoomName())
            .append("roomType", getRoomType())
            .append("chargeName", getChargeName())
            .append("chargePhone", getChargePhone())
            .append("chargeWorkNumber", getChargeWorkNumber())
                .append("buildingId", getBuildingId())
                .append("buildingName", getBuildingName())
                .append("buildingFloor", getBuildingFloor())
                .append("teamId", getTeamId())
                .append("teamName", getTeamName())
            .append("createTime", getCreateTime())
            .toString();
    }
}
