package com.ruoyi.lab.service;

import java.util.List;
import com.ruoyi.lab.domain.AiAlarmLevel;

/**
 * levelService接口
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
public interface IAiAlarmLevelService 
{
    /**
     * 查询level
     * 
     * @param alarmLevelId level主键
     * @return level
     */
    public AiAlarmLevel selectAiAlarmLevelByAlarmLevelId(Long alarmLevelId);

    /**
     * 查询level列表
     * 
     * @param aiAlarmLevel level
     * @return level集合
     */
    public List<AiAlarmLevel> selectAiAlarmLevelList(AiAlarmLevel aiAlarmLevel);

    /**
     * 新增level
     * 
     * @param aiAlarmLevel level
     * @return 结果
     */
    public int insertAiAlarmLevel(AiAlarmLevel aiAlarmLevel);

    /**
     * 修改level
     * 
     * @param aiAlarmLevel level
     * @return 结果
     */
    public int updateAiAlarmLevel(AiAlarmLevel aiAlarmLevel);

    /**
     * 批量删除level
     * 
     * @param alarmLevelIds 需要删除的level主键集合
     * @return 结果
     */
    public int deleteAiAlarmLevelByAlarmLevelIds(Long[] alarmLevelIds);

    /**
     * 删除level信息
     * 
     * @param alarmLevelId level主键
     * @return 结果
     */
    public int deleteAiAlarmLevelByAlarmLevelId(Long alarmLevelId);
}
