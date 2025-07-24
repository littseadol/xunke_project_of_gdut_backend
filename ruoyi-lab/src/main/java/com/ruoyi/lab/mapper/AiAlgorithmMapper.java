package com.ruoyi.lab.mapper;

import java.util.List;
import com.ruoyi.lab.domain.AiAlgorithm;
import org.apache.ibatis.annotations.Mapper;

/**
 * 算法Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
@Mapper
public interface AiAlgorithmMapper 
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
    public int insertAlgorithmAlarmLevel(AiAlgorithm aiAlgorithm);

    /**
     * 修改算法
     * 
     * @param aiAlgorithm 算法
     * @return 结果
     */
    public int updateAiAlgorithm(AiAlgorithm aiAlgorithm);
    public int updateAlgorithmAlarmLevel(AiAlgorithm aiAlgorithm);

    /**
     * 删除算法
     * 
     * @param algorithmId 算法主键
     * @return 结果
     */
    public int deleteAiAlgorithmByAlgorithmId(Long algorithmId);
    public int deleteAlgorithmAlarmLevelByAlgorithmId(Long algorithmId);

    /**
     * 批量删除算法
     * 
     * @param algorithmIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAiAlgorithmByAlgorithmIds(Long[] algorithmIds);
    public int deleteAiAlgorithmAlarmLevelByAlgorithmIds(Long[] algorithmIds);

    // 获取个数
    public int selectCount();
}
