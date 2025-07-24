package com.ruoyi.lab.mapper;

import java.util.List;
import com.ruoyi.lab.domain.AiAlarmLevel;

/**
 * levelMapper接口
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
public interface AiAlarmLevelMapper 
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
     * 删除level
     * 
     * @param alarmLevelId level主键
     * @return 结果
     */
    public int deleteAiAlarmLevelByAlarmLevelId(Long alarmLevelId);

    /**
     * 批量删除level
     * 
     * @param alarmLevelIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAiAlarmLevelByAlarmLevelIds(Long[] alarmLevelIds);
}
