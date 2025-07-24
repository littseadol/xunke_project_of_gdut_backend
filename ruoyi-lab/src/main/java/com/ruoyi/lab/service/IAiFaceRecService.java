package com.ruoyi.lab.service;

import cn.smartjavaai.common.entity.DetectionResponse;
import java.awt.image.BufferedImage;
import cn.smartjavaai.common.entity.R;
import cn.smartjavaai.common.entity.face.FaceSearchResult;
import cn.smartjavaai.face.entity.FaceRegisterInfo;
import cn.smartjavaai.face.entity.FaceSearchParams;
import java.util.List;

public interface IAiFaceRecService {
    //提取人脸特征(多人脸场景)
    /**
     * 从图片路径提取多人脸特征：三种重载方法
     * @param imagePath 图片路径
     * @return 包含人脸检测结果的响应对象
     */
    R<DetectionResponse> extractFeatures(String imagePath);
    R<DetectionResponse> extractFeatures(BufferedImage sourceImage);
    R<DetectionResponse> extractFeatures(byte[] imageData);

    //人脸比对1：1
    /**
     * 基于图像直接比对（1:1）：三种重载方法
     * @param imagePath1 第一张图片路径
     * @param imagePath2 第二张图片路径
     * @return 相似度值
     */
    Float featureComparison(String imagePath1, String imagePath2);
    float featureComparison(BufferedImage sourceImage1, BufferedImage sourceImage2);
    float featureComparison(byte[] imageData1, byte[] imageData2);

    /**
     * 基于特征值比对（1:1）
     * @param imagePath1 第一张图片的人脸特征
     * @param imagePath2 第二张图片的人脸特征
     * @return 相似度值
     */
    float featureComparison2(String imagePath1, String imagePath2);


    //人脸注册
    /**
     * 人脸注册：根据图像注册
     * @param faceRegisterInfo
     * @param imagePath
     * @return
     */
    Integer register(FaceRegisterInfo faceRegisterInfo, String imagePath);
    //R<String> register(FaceRegisterInfo faceRegisterInfo, BufferedImage sourceImage);
    //R<String> register(FaceRegisterInfo faceRegisterInfo, byte[] imageData);
    /**
     * 人脸注册：根据特征值注册
     * @param faceRegisterInfo
     * @param feature
     * @return
     */
    R<String> register(FaceRegisterInfo faceRegisterInfo, float[] feature);

    //人脸更新

    /**
     *  人脸更新
     * @param faceRegisterInfo
     * @param imagePath
     */
    Integer upsertFace(FaceRegisterInfo faceRegisterInfo, String imagePath);
    //void upsertFace(FaceRegisterInfo faceRegisterInfo, BufferedImage sourceImage);
    //void upsertFace(FaceRegisterInfo faceRegisterInfo, byte[] imageData);

    /**
     *  人脸更新
     * @param faceRegisterInfo
     * @param feature
     */
    void upsertFace(FaceRegisterInfo faceRegisterInfo, float[] feature);


    //人脸删除
    /**
     * 删除人脸
     * @param keys
     */
    Integer removeRegister(String... keys);
    void clearFace();

    //人脸查询：1：N模式
    /**
     * 多人脸情形
     * @param imagePath
     * @param params
     * @return
     */
    R<DetectionResponse> search(String imagePath, FaceSearchParams params);
    //R<DetectionResponse> search(BufferedImage sourceImage, FaceSearchParams params);
    //R<DetectionResponse> search(byte[] imageData, FaceSearchParams params);

    /**
     * 单人脸查询
     * @param imagePath
     * @param params
     * @return
     */
    R<List<FaceSearchResult>> searchByTopFace(String imagePath, FaceSearchParams params);
    //R<List<FaceSearchResult>> searchByTopFace(BufferedImage sourceImage, FaceSearchParams params);
    //R<List<FaceSearchResult>> searchByTopFace(byte[] imageData, FaceSearchParams params);
    /**
     * 基于特征值查询
     * @param feature
     * @param params
     * @return
     */
    List<FaceSearchResult> search(float[] feature, FaceSearchParams params);

    /**
     * 检测表情
     * @param imagePath 图片路径
     * @return 结果
     */
    public R<DetectionResponse> detectExpression(String imagePath);

    /**
     * 检测人脸属性
     * @param imagePath 图片路径
     * @return 结果
     */
    public DetectionResponse detectFaceAttribute(String imagePath);
}
