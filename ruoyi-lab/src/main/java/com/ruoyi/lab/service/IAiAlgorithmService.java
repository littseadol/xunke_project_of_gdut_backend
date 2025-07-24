package com.ruoyi.lab.service;

import java.util.List;
import com.ruoyi.lab.domain.AiAlgorithm;

/**
 * 算法Service接口
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
public interface IAiAlgorithmService 
{
    /**
     * 查询算法
     * 
     * @param algorithmId 算法主键
     * @return 算法
     */
    public AiAlgorithm selectAiAlgorithmByAlgorithmId(Long algorithmId);

    /**
     * 查询算法列表
     * 
     * @param aiAlgorithm 算法
     * @return 算法集合
     */
    public List<AiAlgorithm> selectAiAlgorithmList(AiAlgorithm aiAlgorithm);

    /**
     * 新增算法
     * 
     * @param aiAlgorithm 算法
     * @return 结果
     */
    public int insertAiAlgorithm(AiAlgorithm aiAlgorithm);

    /**
     * 修改算法
     * 
     * @param aiAlgorithm 算法
     * @return 结果
     */
    public int updateAiAlgorithm(AiAlgorithm aiAlgorithm);

    /**
     * 批量删除算法
     * 
     * @param algorithmIds 需要删除的算法主键集合
     * @return 结果
     */
    public int deleteAiAlgorithmByAlgorithmIds(Long[] algorithmIds);

    /**
     * 删除算法信息
     * 
     * @param algorithmId 算法主键
     * @return 结果
     */
    public int deleteAiAlgorithmByAlgorithmId(Long algorithmId);
}
