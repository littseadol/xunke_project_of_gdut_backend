package com.ruoyi.lab.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lab.mapper.AiVideoEvaluationMapper;
import com.ruoyi.lab.domain.AiVideoEvaluation;
import com.ruoyi.lab.service.IAiVideoEvaluationService;

/**
 * 视频评价Service业务层处理
 * 
 * @author Ldolphin
 * @date 2025-07-23
 */
@Service
public class AiVideoEvaluationServiceImpl implements IAiVideoEvaluationService 
{
    @Autowired
    private AiVideoEvaluationMapper aiVideoEvaluationMapper;

    /**
     * 查询视频评价
     * 
     * @param id 视频评价主键
     * @return 视频评价
     */
    @Override
    public AiVideoEvaluation selectAiVideoEvaluationById(Long id)
    {
        return aiVideoEvaluationMapper.selectAiVideoEvaluationById(id);
    }

    /**
     * 查询视频评价列表
     * 
     * @param aiVideoEvaluation 视频评价
     * @return 视频评价
     */
    @Override
    public List<AiVideoEvaluation> selectAiVideoEvaluationList(AiVideoEvaluation aiVideoEvaluation)
    {
        return aiVideoEvaluationMapper.selectAiVideoEvaluationList(aiVideoEvaluation);
    }

    /**
     * 新增视频评价
     * 
     * @param aiVideoEvaluation 视频评价
     * @return 结果
     */
    @Override
    public int insertAiVideoEvaluation(AiVideoEvaluation aiVideoEvaluation)
    {
        aiVideoEvaluation.setCreateTime(DateUtils.getNowDate());
        return aiVideoEvaluationMapper.insertAiVideoEvaluation(aiVideoEvaluation);
    }

    /**
     * 修改视频评价
     * 
     * @param aiVideoEvaluation 视频评价
     * @return 结果
     */
    @Override
    public int updateAiVideoEvaluation(AiVideoEvaluation aiVideoEvaluation)
    {
        aiVideoEvaluation.setUpdateTime(DateUtils.getNowDate());
        return aiVideoEvaluationMapper.updateAiVideoEvaluation(aiVideoEvaluation);
    }

    /**
     * 批量删除视频评价
     * 
     * @param ids 需要删除的视频评价主键
     * @return 结果
     */
    @Override
    public int deleteAiVideoEvaluationByIds(Long[] ids)
    {
        return aiVideoEvaluationMapper.deleteAiVideoEvaluationByIds(ids);
    }

    /**
     * 删除视频评价信息
     * 
     * @param id 视频评价主键
     * @return 结果
     */
    @Override
    public int deleteAiVideoEvaluationById(Long id)
    {
        return aiVideoEvaluationMapper.deleteAiVideoEvaluationById(id);
    }
}
