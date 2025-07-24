package com.ruoyi.lab.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 人脸对象 ai_face
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
public class AiFace extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 人脸ID */
    private Long faceId;

    /** 头像 */
    @Excel(name = "头像")
    private String facePicture;

    /** 姓名 */
    @Excel(name = "姓名")
    private String studentName;

    /** 学号 */
    @Excel(name = "学号")
    private String sutdentId;

    /** 专业 */
    @Excel(name = "专业")
    private Long studentMajor;

    /** 年级 */
    @Excel(name = "年级")
    private Long studentGrade;

    /** 班级 */
    @Excel(name = "班级")
    private Long studentClass;

    /** 手机号 */
    @Excel(name = "手机号")
    private String studentPhone;

    /** 人脸分组 */
    @Excel(name = "人脸分组")
    private Long faceGroup;

    public void setFaceId(Long faceId) 
    {
        this.faceId = faceId;
    }

    public Long getFaceId() 
    {
        return faceId;
    }

    public void setFacePicture(String facePicture) 
    {
        this.facePicture = facePicture;
    }

    public String getFacePicture() 
    {
        return facePicture;
    }

    public void setStudentName(String studentName) 
    {
        this.studentName = studentName;
    }

    public String getStudentName() 
    {
        return studentName;
    }

    public void setSutdentId(String sutdentId) 
    {
        this.sutdentId = sutdentId;
    }

    public String getSutdentId() 
    {
        return sutdentId;
    }

    public void setStudentMajor(Long studentMajor)
    {
        this.studentMajor = studentMajor;
    }

    public Long getStudentMajor()
    {
        return studentMajor;
    }

    public void setStudentGrade(Long studentGrade)
    {
        this.studentGrade = studentGrade;
    }

    public Long getStudentGrade()
    {
        return studentGrade;
    }

    public void setStudentClass(Long studentClass)
    {
        this.studentClass = studentClass;
    }

    public Long getStudentClass()
    {
        return studentClass;
    }

    public void setStudentPhone(String studentPhone) 
    {
        this.studentPhone = studentPhone;
    }

    public String getStudentPhone() 
    {
        return studentPhone;
    }

    public void setFaceGroup(Long faceGroup) 
    {
        this.faceGroup = faceGroup;
    }

    public Long getFaceGroup() 
    {
        return faceGroup;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("faceId", getFaceId())
            .append("facePicture", getFacePicture())
            .append("studentName", getStudentName())
            .append("sutdentId", getSutdentId())
            .append("studentMajor", getStudentMajor())
            .append("studentGrade", getStudentGrade())
            .append("studentClass", getStudentClass())
            .append("studentPhone", getStudentPhone())
            .append("faceGroup", getFaceGroup())
            .append("createTime", getCreateTime())
            .toString();
    }
}
