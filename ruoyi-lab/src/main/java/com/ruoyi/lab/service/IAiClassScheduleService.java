package com.ruoyi.lab.service;

import java.util.List;
import com.ruoyi.lab.domain.AiClassSchedule;

/**
 * 班级课信息Service接口
 * 
 * @author Ldolphin
 * @date 2025-07-22
 */
public interface IAiClassScheduleService 
{
    /**
     * 查询班级课信息
     * 
     * @param scheduleId 班级课信息主键
     * @return 班级课信息
     */
    public AiClassSchedule selectAiClassScheduleByScheduleId(Long scheduleId);

    /**
     * 查询班级课信息列表
     * 
     * @param aiClassSchedule 班级课信息
     * @return 班级课信息集合
     */
    public List<AiClassSchedule> selectAiClassScheduleList(AiClassSchedule aiClassSchedule);

    /**
     * 新增班级课信息
     * 
     * @param aiClassSchedule 班级课信息
     * @return 结果
     */
    public int insertAiClassSchedule(AiClassSchedule aiClassSchedule);

    /**
     * 修改班级课信息
     * 
     * @param aiClassSchedule 班级课信息
     * @return 结果
     */
    public int updateAiClassSchedule(AiClassSchedule aiClassSchedule);

    /**
     * 批量删除班级课信息
     * 
     * @param scheduleIds 需要删除的班级课信息主键集合
     * @return 结果
     */
    public int deleteAiClassScheduleByScheduleIds(Long[] scheduleIds);

    /**
     * 删除班级课信息信息
     * 
     * @param scheduleId 班级课信息主键
     * @return 结果
     */
    public int deleteAiClassScheduleByScheduleId(Long scheduleId);
}
