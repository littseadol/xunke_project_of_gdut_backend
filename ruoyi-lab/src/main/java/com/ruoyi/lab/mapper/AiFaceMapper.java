package com.ruoyi.lab.mapper;

import java.util.List;
import com.ruoyi.lab.domain.AiFace;
import org.apache.ibatis.annotations.Mapper;

/**
 * 人脸Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
@Mapper
public interface AiFaceMapper 
{
    /**
     * 查询人脸
     * 
     * @param faceId 人脸主键
     * @return 人脸
     */
    public AiFace selectAiFaceByFaceId(Long faceId);

    /**
     * 查询人脸列表
     * 
     * @param aiFace 人脸
     * @return 人脸集合
     */
    public List<AiFace> selectAiFaceList(AiFace aiFace);

    /**
     * 新增人脸
     * 
     * @param aiFace 人脸
     * @return 结果
     */
    public int insertAiFace(AiFace aiFace);

    /**
     * 修改人脸
     * 
     * @param aiFace 人脸
     * @return 结果
     */
    public int updateAiFace(AiFace aiFace);

    /**
     * 删除人脸
     * 
     * @param faceId 人脸主键
     * @return 结果
     */
    public int deleteAiFaceByFaceId(Long faceId);

    /**
     * 批量删除人脸
     * 
     * @param faceIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAiFaceByFaceIds(Long[] faceIds);

    public int selectCount();
}
