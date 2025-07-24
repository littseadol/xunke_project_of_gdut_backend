package com.ruoyi.lab.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 班级课信息对象 ai_class_schedule
 * 
 * @author Ldolphin
 * @date 2025-07-22
 */
public class AiClassSchedule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 排课ID */
    private Long scheduleId;

    /** 学年学期 */
    @Excel(name = "学年学期")
    private String academicYear;

    /** 周次 */
    @Excel(name = "周次")
    private Long weekNum;

    /** 院系名称 */
    @Excel(name = "院系名称")
    private String collegeName;

    /** 专业名称 */
    @Excel(name = "专业名称")
    private String majorName;

    /** 年级 */
    @Excel(name = "年级")
    private String grade;

    /** 班级名称 */
    @Excel(name = "班级名称")
    private String className;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String courseName;

    /** 教师名称 */
    @Excel(name = "教师名称")
    private String teacherName;

    /** 星期(1-7) */
    @Excel(name = "星期(1-7)")
    private Long weekday;

    /** 节次 */
    @Excel(name = "节次")
    private String section;

    /** 上课地点 */
    @Excel(name = "上课地点")
    private String location;

    /** 排课日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "排课日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date scheduleDate;

    /** 课序 */
    @Excel(name = "课序")
    private Long courseOrder;

    /** 课程类型 */
    @Excel(name = "课程类型")
    private String courseType;

    /** 授课内容简介 */
    @Excel(name = "授课内容简介")
    private String courseDesc;

    /** 学分 */
    @Excel(name = "学分")
    private BigDecimal credits;

    /** 学生人数 */
    @Excel(name = "学生人数")
    private Long studentCount;

    /** 状态（0正常 1停用） */
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setScheduleId(Long scheduleId) 
    {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId() 
    {
        return scheduleId;
    }

    public void setAcademicYear(String academicYear) 
    {
        this.academicYear = academicYear;
    }

    public String getAcademicYear() 
    {
        return academicYear;
    }

    public void setWeekNum(Long weekNum) 
    {
        this.weekNum = weekNum;
    }

    public Long getWeekNum() 
    {
        return weekNum;
    }

    public void setCollegeName(String collegeName) 
    {
        this.collegeName = collegeName;
    }

    public String getCollegeName() 
    {
        return collegeName;
    }

    public void setMajorName(String majorName) 
    {
        this.majorName = majorName;
    }

    public String getMajorName() 
    {
        return majorName;
    }

    public void setGrade(String grade) 
    {
        this.grade = grade;
    }

    public String getGrade() 
    {
        return grade;
    }

    public void setClassName(String className) 
    {
        this.className = className;
    }

    public String getClassName() 
    {
        return className;
    }

    public void setCourseName(String courseName) 
    {
        this.courseName = courseName;
    }

    public String getCourseName() 
    {
        return courseName;
    }

    public void setTeacherName(String teacherName) 
    {
        this.teacherName = teacherName;
    }

    public String getTeacherName() 
    {
        return teacherName;
    }

    public void setWeekday(Long weekday) 
    {
        this.weekday = weekday;
    }

    public Long getWeekday() 
    {
        return weekday;
    }

    public void setSection(String section) 
    {
        this.section = section;
    }

    public String getSection() 
    {
        return section;
    }

    public void setLocation(String location) 
    {
        this.location = location;
    }

    public String getLocation() 
    {
        return location;
    }

    public void setScheduleDate(Date scheduleDate) 
    {
        this.scheduleDate = scheduleDate;
    }

    public Date getScheduleDate() 
    {
        return scheduleDate;
    }

    public void setCourseOrder(Long courseOrder) 
    {
        this.courseOrder = courseOrder;
    }

    public Long getCourseOrder() 
    {
        return courseOrder;
    }

    public void setCourseType(String courseType) 
    {
        this.courseType = courseType;
    }

    public String getCourseType() 
    {
        return courseType;
    }

    public void setCourseDesc(String courseDesc) 
    {
        this.courseDesc = courseDesc;
    }

    public String getCourseDesc() 
    {
        return courseDesc;
    }

    public void setCredits(BigDecimal credits) 
    {
        this.credits = credits;
    }

    public BigDecimal getCredits() 
    {
        return credits;
    }

    public void setStudentCount(Long studentCount) 
    {
        this.studentCount = studentCount;
    }

    public Long getStudentCount() 
    {
        return studentCount;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("scheduleId", getScheduleId())
            .append("academicYear", getAcademicYear())
            .append("weekNum", getWeekNum())
            .append("collegeName", getCollegeName())
            .append("majorName", getMajorName())
            .append("grade", getGrade())
            .append("className", getClassName())
            .append("courseName", getCourseName())
            .append("teacherName", getTeacherName())
            .append("weekday", getWeekday())
            .append("section", getSection())
            .append("location", getLocation())
            .append("scheduleDate", getScheduleDate())
            .append("courseOrder", getCourseOrder())
            .append("courseType", getCourseType())
            .append("courseDesc", getCourseDesc())
            .append("credits", getCredits())
            .append("studentCount", getStudentCount())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
