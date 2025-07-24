package com.ruoyi.lab.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lab.mapper.AiAlarmLevelMapper;
import com.ruoyi.lab.domain.AiAlarmLevel;
import com.ruoyi.lab.service.IAiAlarmLevelService;

/**
 * levelService业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
@Service
public class AiAlarmLevelServiceImpl implements IAiAlarmLevelService 
{
    @Autowired
    private AiAlarmLevelMapper aiAlarmLevelMapper;

    /**
     * 查询level
     * 
     * @param alarmLevelId level主键
     * @return level
     */
    @Override
    public AiAlarmLevel selectAiAlarmLevelByAlarmLevelId(Long alarmLevelId)
    {
        return aiAlarmLevelMapper.selectAiAlarmLevelByAlarmLevelId(alarmLevelId);
    }

    /**
     * 查询level列表
     * 
     * @param aiAlarmLevel level
     * @return level
     */
    @Override
    public List<AiAlarmLevel> selectAiAlarmLevelList(AiAlarmLevel aiAlarmLevel)
    {
        return aiAlarmLevelMapper.selectAiAlarmLevelList(aiAlarmLevel);
    }

    /**
     * 新增level
     * 
     * @param aiAlarmLevel level
     * @return 结果
     */
    @Override
    public int insertAiAlarmLevel(AiAlarmLevel aiAlarmLevel)
    {
        aiAlarmLevel.setCreateTime(DateUtils.getNowDate());
        return aiAlarmLevelMapper.insertAiAlarmLevel(aiAlarmLevel);
    }

    /**
     * 修改level
     * 
     * @param aiAlarmLevel level
     * @return 结果
     */
    @Override
    public int updateAiAlarmLevel(AiAlarmLevel aiAlarmLevel)
    {
        return aiAlarmLevelMapper.updateAiAlarmLevel(aiAlarmLevel);
    }

    /**
     * 批量删除level
     * 
     * @param alarmLevelIds 需要删除的level主键
     * @return 结果
     */
    @Override
    public int deleteAiAlarmLevelByAlarmLevelIds(Long[] alarmLevelIds)
    {
        return aiAlarmLevelMapper.deleteAiAlarmLevelByAlarmLevelIds(alarmLevelIds);
    }

    /**
     * 删除level信息
     * 
     * @param alarmLevelId level主键
     * @return 结果
     */
    @Override
    public int deleteAiAlarmLevelByAlarmLevelId(Long alarmLevelId)
    {
        return aiAlarmLevelMapper.deleteAiAlarmLevelByAlarmLevelId(alarmLevelId);
    }
}
