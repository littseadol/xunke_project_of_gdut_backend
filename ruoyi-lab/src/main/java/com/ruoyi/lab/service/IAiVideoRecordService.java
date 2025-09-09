package com.ruoyi.lab.service;

import java.util.List;
import com.ruoyi.lab.domain.AiVideoRecord;

/**
 * 录像管理Service接口
 * 
 * @author littseadol
 * @date 2025-08-29
 */
public interface IAiVideoRecordService 
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
     * 批量删除录像管理
     * 
     * @param recordIds 需要删除的录像管理主键集合
     * @return 结果
     */
    public int deleteAiVideoRecordByRecordIds(Long[] recordIds);

    /**
     * 删除录像管理信息
     * 
     * @param recordId 录像管理主键
     * @return 结果
     */
    public int deleteAiVideoRecordByRecordId(Long recordId);
}
