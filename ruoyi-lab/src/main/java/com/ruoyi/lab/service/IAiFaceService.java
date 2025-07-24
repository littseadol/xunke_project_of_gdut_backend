package com.ruoyi.lab.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.lab.domain.AiFace;

/**
 * 人脸Service接口
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
public interface IAiFaceService 
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
     * 批量删除人脸
     * 
     * @param faceIds 需要删除的人脸主键集合
     * @return 结果
     */
    public int deleteAiFaceByFaceIds(Long[] faceIds);

    /**
     * 删除人脸信息
     * 
     * @param faceId 人脸主键
     * @return 结果
     */
    public int deleteAiFaceByFaceId(Long faceId);

    /**
     * 比对人脸信息
     *
     * @param facePicture1 人脸1
     * @param facePicture2 人脸2
     * @return 结果
     */
    public Float compareFace(String facePicture1, String facePicture2);

    /**
     * 搜索人脸(多个人脸)
     * @param facePicture 人脸
     * @return 结果
     */
    public Map<String, Object> searchFace(String facePicture);

    /**
     * 检测表情
     * @param facePicture
     * @return
     */
    public Map<String, Object> detectExpression(String facePicture);

    /**
     * 人脸属性检测
     * @param facePicture 图片路径
     * @return 结果
     */
    public Map<String, Object> detectFaceAttribute(String facePicture);
}
