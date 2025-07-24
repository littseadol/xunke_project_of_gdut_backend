package com.ruoyi.lab.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lab.mapper.AiBuildingMapper;
import com.ruoyi.lab.domain.AiBuilding;
import com.ruoyi.lab.service.IAiBuildingService;

/**
 * 楼栋Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
@Service
public class AiBuildingServiceImpl implements IAiBuildingService 
{
    @Autowired
    private AiBuildingMapper aiBuildingMapper;

    /**
     * 查询楼栋
     * 
     * @param buildingId 楼栋主键
     * @return 楼栋
     */
    @Override
    public AiBuilding selectAiBuildingByBuildingId(Long buildingId)
    {
        return aiBuildingMapper.selectAiBuildingByBuildingId(buildingId);
    }

    /**
     * 查询楼栋列表
     * 
     * @param aiBuilding 楼栋
     * @return 楼栋
     */
    @Override
    public List<AiBuilding> selectAiBuildingList(AiBuilding aiBuilding)
    {
        return aiBuildingMapper.selectAiBuildingList(aiBuilding);
    }

    /**
     * 新增楼栋
     * 
     * @param aiBuilding 楼栋
     * @return 结果
     */
    @Override
    public int insertAiBuilding(AiBuilding aiBuilding)
    {
        aiBuilding.setCreateTime(DateUtils.getNowDate());
        return aiBuildingMapper.insertAiBuilding(aiBuilding);
    }

    /**
     * 修改楼栋
     * 
     * @param aiBuilding 楼栋
     * @return 结果
     */
    @Override
    public int updateAiBuilding(AiBuilding aiBuilding)
    {
        return aiBuildingMapper.updateAiBuilding(aiBuilding);
    }

    /**
     * 批量删除楼栋
     * 
     * @param buildingIds 需要删除的楼栋主键
     * @return 结果
     */
    @Override
    public int deleteAiBuildingByBuildingIds(Long[] buildingIds)
    {
        return aiBuildingMapper.deleteAiBuildingByBuildingIds(buildingIds);
    }

    /**
     * 删除楼栋信息
     * 
     * @param buildingId 楼栋主键
     * @return 结果
     */
    @Override
    public int deleteAiBuildingByBuildingId(Long buildingId)
    {
        return aiBuildingMapper.deleteAiBuildingByBuildingId(buildingId);
    }
}
