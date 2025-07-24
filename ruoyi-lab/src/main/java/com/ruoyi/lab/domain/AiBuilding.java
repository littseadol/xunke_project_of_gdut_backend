package com.ruoyi.lab.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 楼栋对象 ai_building
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
public class AiBuilding extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 楼栋ID */
    private Long buildingId;

    /** 楼栋名称 */
    @Excel(name = "楼栋名称")
    private String buildingName;

    /** 楼栋总楼层 */
    @Excel(name = "楼栋总楼层")
    private Long buildingTotalFloor;

    /** 楼栋类型 */
    @Excel(name = "楼栋类型")
    private Long buildingType;

    public void setBuildingId(Long buildingId) 
    {
        this.buildingId = buildingId;
    }

    public Long getBuildingId() 
    {
        return buildingId;
    }

    public void setBuildingName(String buildingName) 
    {
        this.buildingName = buildingName;
    }

    public String getBuildingName() 
    {
        return buildingName;
    }

    public void setBuildingTotalFloor(Long buildingTotalFloor) 
    {
        this.buildingTotalFloor = buildingTotalFloor;
    }

    public Long getBuildingTotalFloor() 
    {
        return buildingTotalFloor;
    }

    public void setBuildingType(Long buildingType) 
    {
        this.buildingType = buildingType;
    }

    public Long getBuildingType() 
    {
        return buildingType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("buildingId", getBuildingId())
            .append("buildingName", getBuildingName())
            .append("buildingTotalFloor", getBuildingTotalFloor())
            .append("buildingType", getBuildingType())
            .append("createTime", getCreateTime())
            .toString();
    }
}
