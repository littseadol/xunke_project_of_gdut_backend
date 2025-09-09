package com.ruoyi.lab.mapper;

import java.util.List;
import com.ruoyi.lab.domain.AiVideoRecord;
import com.ruoyi.lab.domain.AiDevice;

/**
 * 录像管理Mapper接口
 * 
 * @author littseadol
 * @date 2025-08-29
 */
public interface AiVideoRecordMapper 
{
    /**
     * 查询录像管理
     * 
     * @param recordId 录像管理主键
     * @return 录像管理
     */
    public AiVideoRecord selectAiVideoRecordByRecordId(Long recordId);

    /**
     * 查询录像管理列表
     * 
     * @param aiVideoRecord 录像管理
     * @return 录像管理集合
     */
    public List<AiVideoRecord> selectAiVideoRecordList(AiVideoRecord aiVideoRecord);

    /**
     * 新增录像管理
     * 
     * @param aiVideoRecord 录像管理
     * @return 结果
     */
    public int insertAiVideoRecord(AiVideoRecord aiVideoRecord);

    /**
     * 修改录像管理
     * 
     * @param aiVideoRecord 录像管理
     * @return 结果
     */
    public int updateAiVideoRecord(AiVideoRecord aiVideoRecord);

    /**
     * 删除录像管理
     * 
     * @param recordId 录像管理主键
     * @return 结果
     */
    public int deleteAiVideoRecordByRecordId(Long recordId);

    /**
     * 批量删除录像管理
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAiVideoRecordByRecordIds(Long[] recordIds);

    /**
     * 批量删除${subTable.functionName}
     * 
     * @param recordIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAiDeviceByDeviceIds(Long[] recordIds);
    
    /**
     * 批量新增${subTable.functionName}
     * 
     * @param aiDeviceList ${subTable.functionName}列表
     * @return 结果
     */
    public int batchAiDevice(List<AiDevice> aiDeviceList);
    

    /**
     * 通过录像管理主键删除${subTable.functionName}信息
     * 
     * @param recordId 录像管理ID
     * @return 结果
     */
    public int deleteAiDeviceByDeviceId(Long recordId);
}
