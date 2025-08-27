package com.ruoyi.lab.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
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
public class AiVideoEvaluation extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 评价ID
     */
    private Long id;

    /**
     * 摄像头ID
     */
    @Excel(name = "摄像头ID")
    private Long deviceId;

    /**
     * 评分(1-5)
     */
    @Excel(name = "评分(1-5)")
    private Long score;

    /**
     * 评价内容
     */
    @Excel(name = "评价内容")
    private String content;

    /**
     * 改进建议
     */
    @Excel(name = "改进建议")
    private String suggestion;

    /**
     * 截图路径
     */
    @Excel(name = "截图路径")
    private String snapshotPath;

    /**
     * 截图时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "截图时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date snapshotTime;

    /**
     * 视频时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "视频时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date videoTime;

    /**
     * 排课id
     */
    @Excel(name = "排课id")
    private Long scheduleId;  // 新增字段

    /**
     * 课程信息
     */
    @Excel(name = "课程信息")
    private String courseName;

    /**
     * 教师信息
     */
    @Excel(name = "教师信息")
    private String teacherName;


    // 关联的排课信息（非数据库字段）
    private AiClassSchedule schedule;

    /**
     * 教学目标评分1：紧扣教学大纲，教学目标明确，注重因材施教 (1-10分)
     */
    @Excel(name = "教学目标评分1")
    private Integer teachingObjectiveScore1;

    /**
     * 教学目标评分2：立德树人，重视教学过程对学生的德育培养和价值引导 (1-10分)
     */
    @Excel(name = "教学目标评分2")
    private Integer teachingObjectiveScore2;

    /**
     * 教学内容评分1：内容饱满，材料丰富，融入学科前沿知识或者最新成果 (1-10分)
     */
    @Excel(name = "教学内容评分1")
    private Integer contentScore1;

    /**
     * 教学内容评分2：重点突出，条理清晰，循序渐进，注重理论联系实际 (1-10分)
     */
    @Excel(name = "教学内容评分2")
    private Integer contentScore2;

    /**
     * 教学方法评分1：引入合适的教学形式，注重学生创新意识和实践能力的培养 (1-10分)
     */
    @Excel(name = "教学方法评分1")
    private Integer methodScore1;

    /**
     * 教学方法评分2：善于启发引导，积极互动，有效调动学生思考和学习主动性 (1-10分)
     */
    @Excel(name = "教学方法评分2")
    private Integer methodScore2;

    /**
     * 教学态度评分1：备课充分，课程内容娴熟，教态自然 (1-10分)
     */
    @Excel(name = "教学态度评分1")
    private Integer attitudeScore1;




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getScore() {
        return score;
    }

    public void setScore(Long score) {
        this.score = score;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getSnapshotPath() {
        return snapshotPath;
    }

    public void setSnapshotPath(String snapshotPath) {
        this.snapshotPath = snapshotPath;
    }

    public Date getSnapshotTime() {
        return snapshotTime;
    }

    public void setSnapshotTime(Date snapshotTime) {
        this.snapshotTime = snapshotTime;
    }

    public Date getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(Date videoTime) {
        this.videoTime = videoTime;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public AiClassSchedule getSchedule() {
        return schedule;
    }

    public void setSchedule(AiClassSchedule schedule) {
        this.schedule = schedule;
    }

    public Integer getTeachingObjectiveScore1() {
        return teachingObjectiveScore1;
    }

    public void setTeachingObjectiveScore1(Integer teachingObjectiveScore1) {
        this.teachingObjectiveScore1 = teachingObjectiveScore1;
    }

    public Integer getTeachingObjectiveScore2() {
        return teachingObjectiveScore2;
    }

    public void setTeachingObjectiveScore2(Integer teachingObjectiveScore2) {
        this.teachingObjectiveScore2 = teachingObjectiveScore2;
    }

    public Integer getContentScore1() {
        return contentScore1;
    }

    public void setContentScore1(Integer contentScore1) {
        this.contentScore1 = contentScore1;
    }

    public Integer getContentScore2() {
        return contentScore2;
    }

    public void setContentScore2(Integer contentScore2) {
        this.contentScore2 = contentScore2;
    }

    public Integer getMethodScore1() {
        return methodScore1;
    }

    public void setMethodScore1(Integer methodScore1) {
        this.methodScore1 = methodScore1;
    }

    public Integer getMethodScore2() {
        return methodScore2;
    }

    public void setMethodScore2(Integer methodScore2) {
        this.methodScore2 = methodScore2;
    }

    public Integer getAttitudeScore1() {
        return attitudeScore1;
    }

    public void setAttitudeScore1(Integer attitudeScore1) {
        this.attitudeScore1 = attitudeScore1;
    }

}
