package com.ruoyi.lab.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 团队对象 ai_team
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
public class AiTeam extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 团队ID */
    private Long teamId;

    /** 团队名称 */
    @Excel(name = "团队名称")
    private String teamName;

    /** 负责人姓名 */
    @Excel(name = "负责人姓名")
    private String chargeName;


    /** 负责人工号 */
    @Excel(name = "负责人工号")
    private String chargeWorkNumber;

    /** 负责人联系方式 */
    @Excel(name = "负责人联系方式")
    private String chargePhone;

    /** 管理员姓名 */
    @Excel(name = "管理员姓名")
    private String adminName;

    /** 管理员工号 */
    @Excel(name = "管理员工号")
    private String adminWorkNumber;

    /** 管理员联系方式 */
    @Excel(name = "管理员联系方式")
    private String adminPhone;

    public String getChargeWorkNumber() {
        return chargeWorkNumber;
    }

    public void setChargeWorkNumber(String chargeWorkNumber) {
        this.chargeWorkNumber = chargeWorkNumber;
    }

    public void setTeamId(Long teamId)
    {
        this.teamId = teamId;
    }

    public Long getTeamId() 
    {
        return teamId;
    }

    public void setTeamName(String teamName) 
    {
        this.teamName = teamName;
    }

    public String getTeamName() 
    {
        return teamName;
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

    public void setAdminName(String adminName) 
    {
        this.adminName = adminName;
    }

    public String getAdminName() 
    {
        return adminName;
    }

    public void setAdminWorkNumber(String adminWorkNumber) 
    {
        this.adminWorkNumber = adminWorkNumber;
    }

    public String getAdminWorkNumber() 
    {
        return adminWorkNumber;
    }

    public void setAdminPhone(String adminPhone) 
    {
        this.adminPhone = adminPhone;
    }

    public String getAdminPhone() 
    {
        return adminPhone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("teamId", getTeamId())
            .append("teamName", getTeamName())
            .append("chargeName", getChargeName())
            .append("chargePhone", getChargePhone())
            .append("adminName", getAdminName())
            .append("adminWorkNumber", getAdminWorkNumber())
            .append("adminPhone", getAdminPhone())
            .append("createTime", getCreateTime())
            .toString();
    }
}
