package com.ruoyi.lab.mapper;

import java.util.List;
import com.ruoyi.lab.domain.AiClassSchedule;

/**
 * 班级课信息Mapper接口
 * 
 * @author Ldolphin
 * @date 2025-07-22
 */
public interface AiClassScheduleMapper 
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
     * 删除班级课信息
     * 
     * @param scheduleId 班级课信息主键
     * @return 结果
     */
    public int deleteAiClassScheduleByScheduleId(Long scheduleId);

    /**
     * 批量删除班级课信息
     * 
     * @param scheduleIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAiClassScheduleByScheduleIds(Long[] scheduleIds);
}
