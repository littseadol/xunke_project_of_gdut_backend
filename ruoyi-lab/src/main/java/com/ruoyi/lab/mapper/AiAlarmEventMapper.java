package com.ruoyi.lab.mapper;

import java.util.List;
import com.ruoyi.lab.domain.AiAlarmEvent;

/**
 * eventMapper接口
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
public interface AiAlarmEventMapper 
{
    /**
     * 查询event
     * 
     * @param eventId event主键
     * @return event
     */
    public AiAlarmEvent selectAiAlarmEventByEventId(Long eventId);

    /**
     * 查询event列表
     * 
     * @param aiAlarmEvent event
     * @return event集合
     */
    public List<AiAlarmEvent> selectAiAlarmEventList(AiAlarmEvent aiAlarmEvent);

    /**
     * 新增event
     * 
     * @param aiAlarmEvent event
     * @return 结果
     */
    public int insertAiAlarmEvent(AiAlarmEvent aiAlarmEvent);

    /**
     * 修改event
     * 
     * @param aiAlarmEvent event
     * @return 结果
     */
    public int updateAiAlarmEvent(AiAlarmEvent aiAlarmEvent);

    /**
     * 删除event
     * 
     * @param eventId event主键
     * @return 结果
     */
    public int deleteAiAlarmEventByEventId(Long eventId);

    /**
     * 批量删除event
     * 
     * @param eventIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAiAlarmEventByEventIds(Long[] eventIds);
}
