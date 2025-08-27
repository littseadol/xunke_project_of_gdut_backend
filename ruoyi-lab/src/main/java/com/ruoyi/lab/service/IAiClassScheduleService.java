package com.ruoyi.lab.service;

import java.util.List;
import com.ruoyi.lab.domain.AiClassSchedule;

/**
 * 排课信息Service接口
 * 
 * @author Ldolphin
 * @date 2025-07-24
 */
public interface IAiClassScheduleService 
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
     * 批量删除排课信息
     * 
     * @param scheduleIds 需要删除的排课信息主键集合
     * @return 结果
     */
    public int deleteAiClassScheduleByScheduleIds(Long[] scheduleIds);

    /**
     * 删除排课信息信息
     * 
     * @param scheduleId 排课信息主键
     * @return 结果
     */
    public int deleteAiClassScheduleByScheduleId(Long scheduleId);
}
