package com.ruoyi.lab.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lab.mapper.AiAlarmEventMapper;
import com.ruoyi.lab.domain.AiAlarmEvent;
import com.ruoyi.lab.service.IAiAlarmEventService;

/**
 * eventService业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
@Service
public class AiAlarmEventServiceImpl implements IAiAlarmEventService 
{
    @Autowired
    private AiAlarmEventMapper aiAlarmEventMapper;

    /**
     * 查询event
     * 
     * @param eventId event主键
     * @return event
     */
    @Override
    public AiAlarmEvent selectAiAlarmEventByEventId(Long eventId)
    {
        return aiAlarmEventMapper.selectAiAlarmEventByEventId(eventId);
    }

    /**
     * 查询event列表
     * 
     * @param aiAlarmEvent event
     * @return event
     */
    @Override
    public List<AiAlarmEvent> selectAiAlarmEventList(AiAlarmEvent aiAlarmEvent)
    {
        return aiAlarmEventMapper.selectAiAlarmEventList(aiAlarmEvent);
    }

    /**
     * 新增event
     * 
     * @param aiAlarmEvent event
     * @return 结果
     */
    @Override
    public int insertAiAlarmEvent(AiAlarmEvent aiAlarmEvent)
    {
        aiAlarmEvent.setCreateTime(DateUtils.getNowDate());
        return aiAlarmEventMapper.insertAiAlarmEvent(aiAlarmEvent);
    }

    /**
     * 修改event
     * 
     * @param aiAlarmEvent event
     * @return 结果
     */
    @Override
    public int updateAiAlarmEvent(AiAlarmEvent aiAlarmEvent)
    {
        return aiAlarmEventMapper.updateAiAlarmEvent(aiAlarmEvent);
    }

    /**
     * 批量删除event
     * 
     * @param eventIds 需要删除的event主键
     * @return 结果
     */
    @Override
    public int deleteAiAlarmEventByEventIds(Long[] eventIds)
    {
        return aiAlarmEventMapper.deleteAiAlarmEventByEventIds(eventIds);
    }

    /**
     * 删除event信息
     * 
     * @param eventId event主键
     * @return 结果
     */
    @Override
    public int deleteAiAlarmEventByEventId(Long eventId)
    {
        return aiAlarmEventMapper.deleteAiAlarmEventByEventId(eventId);
    }
}
