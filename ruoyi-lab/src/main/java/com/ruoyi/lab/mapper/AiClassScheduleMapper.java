package com.ruoyi.lab.mapper;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import com.ruoyi.lab.domain.AiClassSchedule;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 排课信息Mapper接口
 * 
 * @author Ldolphin
 * @date 2025-07-24
 */
public interface AiClassScheduleMapper 
{
    /**
     * 查询排课信息
     * 
     * @param scheduleId 排课信息主键
     * @return 排课信息
     */
    public AiClassSchedule selectAiClassScheduleByScheduleId(Long scheduleId);

    /**
     * 查询排课信息列表
     * 
     * @param aiClassSchedule 排课信息
     * @return 排课信息集合
     */
    public List<AiClassSchedule> selectAiClassScheduleList(AiClassSchedule aiClassSchedule);


    /**
     * 根据位置、日期和时间点查询排课信息
     * @param location 教室位置
     * @param date 排课日期
     * @param time 查询时间点
     * @return 匹配的排课记录
     */
    @Select("SELECT " +
            "schedule_id as scheduleId, " +
            "academic_year as academicYear, " +
            "week_num as weekNum, " +
            "college_name as collegeName, " +
            "major_name as majorName, " +
            "grade, " +
            "class_name as className, " +
            "course_name as courseName, " +
            "teacher_name as teacherName, " +
            "weekday, " +
            "section, " +
            "location, " +
            "schedule_date as scheduleDate, " +
            "start_time as startTime, " +
            "end_time as endTime, " +
            "course_order as courseOrder, " +
            "course_type as courseType, " +
            "course_desc as courseDesc, " +
            "credits, " +
            "student_count as studentCount, " +
            "status, " +
            "del_flag as delFlag, " +
            "create_by as createBy, " +
            "create_time as createTime, " +
            "update_by as updateBy, " +
            "update_time as updateTime, " +
            "remark " +
            "FROM ai_class_schedule " +
            "WHERE location = #{location} " +
            "AND schedule_date = #{date} " +
            "AND start_time <= #{time} " +
            "AND end_time >= #{time} " +
            "LIMIT 1")
    AiClassSchedule selectByLocationAndTime(
            @Param("location") String location,
            @Param("date") LocalDate date,
            @Param("time") LocalTime time);

    /**
     * 新增排课信息
     * 
     * @param aiClassSchedule 排课信息
     * @return 结果
     */
    public int insertAiClassSchedule(AiClassSchedule aiClassSchedule);

    /**
     * 修改排课信息
     * 
     * @param aiClassSchedule 排课信息
     * @return 结果
     */
    public int updateAiClassSchedule(AiClassSchedule aiClassSchedule);

    /**
     * 删除排课信息
     * 
     * @param scheduleId 排课信息主键
     * @return 结果
     */
    public int deleteAiClassScheduleByScheduleId(Long scheduleId);

    /**
     * 批量删除排课信息
     * 
     * @param scheduleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAiClassScheduleByScheduleIds(Long[] scheduleIds);
}
