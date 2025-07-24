package com.ruoyi.lab.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lab.mapper.AiAlgorithmMapper;
import com.ruoyi.lab.domain.AiAlgorithm;
import com.ruoyi.lab.service.IAiAlgorithmService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 算法Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
@Service
public class AiAlgorithmServiceImpl implements IAiAlgorithmService 
{
    @Autowired
    private AiAlgorithmMapper aiAlgorithmMapper;

    /**
     * 查询算法
     * 
     * @param algorithmId 算法主键
     * @return 算法
     */
    @Override
    public AiAlgorithm selectAiAlgorithmByAlgorithmId(Long algorithmId)
    {
        return aiAlgorithmMapper.selectAiAlgorithmByAlgorithmId(algorithmId);
    }

    /**
     * 查询算法列表
     * 
     * @param aiAlgorithm 算法
     * @return 算法
     */
    @Override
    public List<AiAlgorithm> selectAiAlgorithmList(AiAlgorithm aiAlgorithm)
    {
        return aiAlgorithmMapper.selectAiAlgorithmList(aiAlgorithm);
    }

    /**
     * 新增算法
     * 
     * @param aiAlgorithm 算法
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertAiAlgorithm(AiAlgorithm aiAlgorithm)
    {
        aiAlgorithm.setCreateTime(DateUtils.getNowDate());
        int result = aiAlgorithmMapper.insertAiAlgorithm(aiAlgorithm);
        if (result == 0) {
            return result;
        }
        result = aiAlgorithmMapper.insertAlgorithmAlarmLevel(aiAlgorithm);
        return result;
    }

    /**
     * 修改算法
     * 
     * @param aiAlgorithm 算法
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateAiAlgorithm(AiAlgorithm aiAlgorithm)
    {
        int result = aiAlgorithmMapper.updateAiAlgorithm(aiAlgorithm);
        if (result == 0) {
            return result;
        }
        result = aiAlgorithmMapper.updateAlgorithmAlarmLevel(aiAlgorithm);
        return result;
    }

    /**
     * 批量删除算法
     * 
     * @param algorithmIds 需要删除的算法主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAiAlgorithmByAlgorithmIds(Long[] algorithmIds)
    {
        int result = aiAlgorithmMapper.deleteAiAlgorithmByAlgorithmIds(algorithmIds);
        if (result == 0) {
            return result;
        }
        result = aiAlgorithmMapper.deleteAiAlgorithmAlarmLevelByAlgorithmIds(algorithmIds);
        return result;
    }

    /**
     * 删除算法信息
     * 
     * @param algorithmId 算法主键
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int deleteAiAlgorithmByAlgorithmId(Long algorithmId) {
        int result = aiAlgorithmMapper.deleteAiAlgorithmByAlgorithmId(algorithmId);
        if (result == 0) {
            return result;
        }
        result = aiAlgorithmMapper.deleteAlgorithmAlarmLevelByAlgorithmId(algorithmId);
        return result;
    }
}
