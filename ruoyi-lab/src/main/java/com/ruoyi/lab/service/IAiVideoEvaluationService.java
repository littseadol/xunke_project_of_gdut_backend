package com.ruoyi.lab.service;

import java.util.List;
import com.ruoyi.lab.domain.AiVideoEvaluation;

/**
 * 视频评价Service接口
 * 
 * @author Ldolphin
 * @date 2025-07-23
 */
public interface IAiVideoEvaluationService 
{
    /**
     * 查询视频评价
     * 
     * @param id 视频评价主键
     * @return 视频评价
     */
    public AiVideoEvaluation selectAiVideoEvaluationById(Long id);

    /**
     * 查询视频评价列表
     * 
     * @param aiVideoEvaluation 视频评价
     * @return 视频评价集合
     */
    public List<AiVideoEvaluation> selectAiVideoEvaluationList(AiVideoEvaluation aiVideoEvaluation);

    /**
     * 新增视频评价
     * 
     * @param aiVideoEvaluation 视频评价
     * @return 结果
     */
    public int insertAiVideoEvaluation(AiVideoEvaluation aiVideoEvaluation);

    /**
     * 修改视频评价
     * 
     * @param aiVideoEvaluation 视频评价
     * @return 结果
     */
    public int updateAiVideoEvaluation(AiVideoEvaluation aiVideoEvaluation);

    /**
     * 批量删除视频评价
     * 
     * @param ids 需要删除的视频评价主键集合
     * @return 结果
     */
    public int deleteAiVideoEvaluationByIds(Long[] ids);

    /**
     * 删除视频评价信息
     * 
     * @param id 视频评价主键
     * @return 结果
     */
    public int deleteAiVideoEvaluationById(Long id);
}
