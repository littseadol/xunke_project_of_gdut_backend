package com.ruoyi.lab.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.lab.domain.AiDevice;
import com.ruoyi.lab.mapper.AiVideoRecordMapper;
import com.ruoyi.lab.domain.AiVideoRecord;
import com.ruoyi.lab.service.IAiVideoRecordService;

/**
 * 录像管理Service业务层处理
 * 
 * @author littseadol
 * @date 2025-08-29
 */
@Service
public class AiVideoRecordServiceImpl implements IAiVideoRecordService 
{
    @Autowired
    private AiVideoRecordMapper aiVideoRecordMapper;

    /**
     * 查询录像管理
     * 
     * @param recordId 录像管理主键
     * @return 录像管理
     */
    @Override
    public AiVideoRecord selectAiVideoRecordByRecordId(Long recordId)
    {
        return aiVideoRecordMapper.selectAiVideoRecordByRecordId(recordId);
    }

    /**
     * 查询录像管理列表
     * 
     * @param aiVideoRecord 录像管理
     * @return 录像管理
     */
    @Override
    public List<AiVideoRecord> selectAiVideoRecordList(AiVideoRecord aiVideoRecord)
    {
        return aiVideoRecordMapper.selectAiVideoRecordList(aiVideoRecord);
    }

    /**
     * 新增录像管理
     * 
     * @param aiVideoRecord 录像管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertAiVideoRecord(AiVideoRecord aiVideoRecord)
    {
        aiVideoRecord.setCreateTime(DateUtils.getNowDate());
        int rows = aiVideoRecordMapper.insertAiVideoRecord(aiVideoRecord);
        insertAiDevice(aiVideoRecord);
        return rows;
    }

    /**
     * 修改录像管理
     * 
     * @param aiVideoRecord 录像管理
     * @return 结果
     */
    @Transactional
    @Override
    public int updateAiVideoRecord(AiVideoRecord aiVideoRecord)
    {
        aiVideoRecord.setUpdateTime(DateUtils.getNowDate());
        aiVideoRecordMapper.deleteAiDeviceByDeviceId(aiVideoRecord.getRecordId());
        insertAiDevice(aiVideoRecord);
        return aiVideoRecordMapper.updateAiVideoRecord(aiVideoRecord);
    }

    /**
     * 批量删除录像管理
     * 
     * @param recordIds 需要删除的录像管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAiVideoRecordByRecordIds(Long[] recordIds)
    {
        aiVideoRecordMapper.deleteAiDeviceByDeviceIds(recordIds);
        return aiVideoRecordMapper.deleteAiVideoRecordByRecordIds(recordIds);
    }

    /**
     * 删除录像管理信息
     * 
     * @param recordId 录像管理主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteAiVideoRecordByRecordId(Long recordId)
    {
        aiVideoRecordMapper.deleteAiDeviceByDeviceId(recordId);
        return aiVideoRecordMapper.deleteAiVideoRecordByRecordId(recordId);
    }

    /**
     * 新增${subTable.functionName}信息
     * 
     * @param aiVideoRecord 录像管理对象
     */
    public void insertAiDevice(AiVideoRecord aiVideoRecord)
    {
        List<AiDevice> aiDeviceList = aiVideoRecord.getAiDeviceList();
        Long recordId = aiVideoRecord.getRecordId();
        if (StringUtils.isNotNull(aiDeviceList))
        {
            List<AiDevice> list = new ArrayList<AiDevice>();
            for (AiDevice aiDevice : aiDeviceList)
            {
                aiDevice.setDeviceId(recordId);
                list.add(aiDevice);
            }
            if (list.size() > 0)
            {
                aiVideoRecordMapper.batchAiDevice(list);
            }
        }
    }
}
