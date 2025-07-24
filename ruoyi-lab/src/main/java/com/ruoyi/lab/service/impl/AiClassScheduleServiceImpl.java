package com.ruoyi.lab.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lab.mapper.AiClassScheduleMapper;
import com.ruoyi.lab.domain.AiClassSchedule;
import com.ruoyi.lab.service.IAiClassScheduleService;

/**
 * 班级课信息Service业务层处理
 * 
 * @author Ldolphin
 * @date 2025-07-22
 */
@Service
public class AiClassScheduleServiceImpl implements IAiClassScheduleService 
{
    @Autowired
    private AiClassScheduleMapper aiClassScheduleMapper;

    /**
     * 查询班级课信息
     * 
     * @param scheduleId 班级课信息主键
     * @return 班级课信息
     */
    @Override
    public AiClassSchedule selectAiClassScheduleByScheduleId(Long scheduleId)
    {
        return aiClassScheduleMapper.selectAiClassScheduleByScheduleId(scheduleId);
    }

    /**
     * 查询班级课信息列表
     * 
     * @param aiClassSchedule 班级课信息
     * @return 班级课信息
     */
    @Override
    public List<AiClassSchedule> selectAiClassScheduleList(AiClassSchedule aiClassSchedule)
    {
        return aiClassScheduleMapper.selectAiClassScheduleList(aiClassSchedule);
    }

    /**
     * 新增班级课信息
     * 
     * @param aiClassSchedule 班级课信息
     * @return 结果
     */
    @Override
    public int insertAiClassSchedule(AiClassSchedule aiClassSchedule)
    {
        aiClassSchedule.setCreateTime(DateUtils.getNowDate());
        return aiClassScheduleMapper.insertAiClassSchedule(aiClassSchedule);
    }

    /**
     * 修改班级课信息
     * 
     * @param aiClassSchedule 班级课信息
     * @return 结果
     */
    @Override
    public int updateAiClassSchedule(AiClassSchedule aiClassSchedule)
    {
        aiClassSchedule.setUpdateTime(DateUtils.getNowDate());
        return aiClassScheduleMapper.updateAiClassSchedule(aiClassSchedule);
    }

    /**
     * 批量删除班级课信息
     * 
     * @param scheduleIds 需要删除的班级课信息主键
     * @return 结果
     */
    @Override
    public int deleteAiClassScheduleByScheduleIds(Long[] scheduleIds)
    {
        return aiClassScheduleMapper.deleteAiClassScheduleByScheduleIds(scheduleIds);
    }

    /**
     * 删除班级课信息信息
     * 
     * @param scheduleId 班级课信息主键
     * @return 结果
     */
    @Override
    public int deleteAiClassScheduleByScheduleId(Long scheduleId)
    {
        return aiClassScheduleMapper.deleteAiClassScheduleByScheduleId(scheduleId);
    }
}
