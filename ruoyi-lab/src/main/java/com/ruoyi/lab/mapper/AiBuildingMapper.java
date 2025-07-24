package com.ruoyi.lab.mapper;

import java.util.List;
import com.ruoyi.lab.domain.AiBuilding;
import org.apache.ibatis.annotations.Mapper;

/**
 * 楼栋Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
@Mapper
public interface AiBuildingMapper 
{
    /**
     * 查询楼栋
     * 
     * @param buildingId 楼栋主键
     * @return 楼栋
     */
    public AiBuilding selectAiBuildingByBuildingId(Long buildingId);

    /**
     * 查询楼栋列表
     * 
     * @param aiBuilding 楼栋
     * @return 楼栋集合
     */
    public List<AiBuilding> selectAiBuildingList(AiBuilding aiBuilding);

    /**
     * 新增楼栋
     * 
     * @param aiBuilding 楼栋
     * @return 结果
     */
    public int insertAiBuilding(AiBuilding aiBuilding);

    /**
     * 修改楼栋
     * 
     * @param aiBuilding 楼栋
     * @return 结果
     */
    public int updateAiBuilding(AiBuilding aiBuilding);

    /**
     * 删除楼栋
     * 
     * @param buildingId 楼栋主键
     * @return 结果
     */
    public int deleteAiBuildingByBuildingId(Long buildingId);

    /**
     * 批量删除楼栋
     * 
     * @param buildingIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAiBuildingByBuildingIds(Long[] buildingIds);

    public int selectCount();
}
