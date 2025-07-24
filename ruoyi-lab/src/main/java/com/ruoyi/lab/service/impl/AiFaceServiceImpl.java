package com.ruoyi.lab.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.smartjavaai.common.entity.DetectionInfo;
import cn.smartjavaai.common.entity.DetectionRectangle;
import cn.smartjavaai.common.entity.DetectionResponse;
import cn.smartjavaai.common.entity.R;
import cn.smartjavaai.common.entity.face.FaceAttribute;
import cn.smartjavaai.common.entity.face.FaceSearchResult;
import cn.smartjavaai.face.entity.FaceRegisterInfo;
import cn.smartjavaai.face.entity.FaceSearchParams;
import com.alibaba.fastjson2.JSON;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.lab.domain.AiFaceRectangle;
import com.ruoyi.lab.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lab.mapper.AiFaceMapper;
import com.ruoyi.lab.domain.AiFace;
import org.springframework.transaction.annotation.Transactional;

/**
 * 人脸Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
@Slf4j
@Service
@Transactional(rollbackFor = Exception.class)
public class AiFaceServiceImpl implements IAiFaceService {

    @Autowired
    private AiFaceMapper aiFaceMapper;

    @Autowired
    private IAiFaceRecService iAiFaceRecService;

    @Autowired
    private IAiHttpRequestService iaiHttpRequestService;

    @Autowired
    private IAiImageIOService iAiImageIOService;
    @Autowired
    private IAiMinioService iAiMinioService;

    /**
     * 查询人脸
     * 
     * @param faceId 人脸主键
     * @return 人脸
     */
    @Override
    public AiFace selectAiFaceByFaceId(Long faceId)
    {
        return aiFaceMapper.selectAiFaceByFaceId(faceId);
    }

    /**
     * 查询人脸列表
     * 
     * @param aiFace 人脸
     * @return 人脸
     */
    @Override
    public List<AiFace> selectAiFaceList(AiFace aiFace)
    {
        return aiFaceMapper.selectAiFaceList(aiFace);
    }

    /**
     * 新增人脸
     * 
     * @param aiFace 人脸
     * @return 结果
     */
    @Override
    public int insertAiFace(AiFace aiFace)
    {
        aiFace.setCreateTime(DateUtils.getNowDate());
        int result = aiFaceMapper.insertAiFace(aiFace);
        if (result == 0) {
            return result;
        }

        // 把人脸数据注册到向量数据库
        Long faceId = aiFace.getFaceId();
        log.debug("faceId：{}", faceId);

        String facePath = iaiHttpRequestService.download(aiFace.getFacePicture(), "jpg");

        FaceRegisterInfo faceRegisterInfo = new FaceRegisterInfo();
        faceRegisterInfo.setId(String.valueOf(faceId));
        faceRegisterInfo.setMetadata(JSON.toJSONString(aiFace));
        return iAiFaceRecService.upsertFace(faceRegisterInfo, facePath);
    }

    /**
     * 修改人脸
     * 
     * @param aiFace 人脸
     * @return 结果
     */
    @Override
    public int updateAiFace(AiFace aiFace)
    {
        int result = aiFaceMapper.updateAiFace(aiFace);
        if (result == 0) {
            return result;
        }

        // 把人脸数据注册到向量数据库
        Long faceId = aiFace.getFaceId();
        log.debug("faceId：{}", faceId);

        String facePath = iaiHttpRequestService.download(aiFace.getFacePicture(), "jpg");

        FaceRegisterInfo faceRegisterInfo = new FaceRegisterInfo();
        faceRegisterInfo.setId(String.valueOf(faceId));
        faceRegisterInfo.setMetadata(JSON.toJSONString(aiFace));
        return iAiFaceRecService.upsertFace(faceRegisterInfo, facePath);
    }

    /**
     * 批量删除人脸
     * 
     * @param faceIds 需要删除的人脸主键
     * @return 结果
     */
    @Override
    public int deleteAiFaceByFaceIds(Long[] faceIds)
    {
        int result = aiFaceMapper.deleteAiFaceByFaceIds(faceIds);
        if (result == 0) {
            return result;
        }

        String[] strings = new String[faceIds.length];
        for (int i = 0; i < faceIds.length; i++) {
            strings[i] = String.valueOf(faceIds[i]);
        }

        return iAiFaceRecService.removeRegister(strings);
    }

    /**
     * 删除人脸信息
     * 
     * @param faceId 人脸主键
     * @return 结果
     */
    @Override
    public int deleteAiFaceByFaceId(Long faceId)
    {
        int result = aiFaceMapper.deleteAiFaceByFaceId(faceId);
        if (result == 0) {
            return result;
        }
        return iAiFaceRecService.removeRegister(String.valueOf(faceId));
    }

    /**
     * 比对人脸
     * @param facePicture1 人脸1
     * @param facePicture2 人脸2
     * @return 结果
     */
    @Override
    public Float compareFace(String facePicture1, String facePicture2) {

        // 下载到本地
        String facePath1 = iaiHttpRequestService.download(facePicture1, "jpg");
        log.debug("facePath1:{}", facePath1);
        String facePath2 = iaiHttpRequestService.download(facePicture2, "jpg");
        log.debug("facePath2:{}", facePath2);

        return iAiFaceRecService.featureComparison(facePath1, facePath2);
    }

    @Override
    public Map<String, Object> searchFace(String facePicture) {

        // 下载到本地
        String facePath = iaiHttpRequestService.download(facePicture, "jpg");
        R<DetectionResponse> detectionResponseR = iAiFaceRecService.search(facePath, new FaceSearchParams());
        log.debug("人脸检索结果：{}", JSON.toJSONString(detectionResponseR));

        // 矩形区域列表
        List<AiFaceRectangle> aiFaceRectangles = new ArrayList<>();
        List<DetectionInfo> detectionInfos = detectionResponseR.getData().getDetectionInfoList();
        for (DetectionInfo detectionInfo : detectionInfos) {
            DetectionRectangle detectionRectangle = detectionInfo.getDetectionRectangle();
            int x = detectionRectangle.getX();
            int y = detectionRectangle.getY();
            int width = detectionRectangle.getWidth();
            int height = detectionRectangle.getHeight();

            List<FaceSearchResult> faceSearchResults = detectionInfo.getFaceInfo().getFaceSearchResults();
            String content;
            if (faceSearchResults != null && !faceSearchResults.isEmpty()) {
                FaceSearchResult faceSearchResult = faceSearchResults.get(0);
                String metadata = faceSearchResult.getMetadata();
                AiFace aiFace = JSON.parseObject(metadata, AiFace.class);
                content = aiFace.getStudentName();
            } else {
                content = "检索失败";
            }

            AiFaceRectangle aiFaceRectangle = new AiFaceRectangle(content, x, y, width, height);
            aiFaceRectangles.add(aiFaceRectangle);
        }

        // 绘制矩形，并且返回图片路径
        String outputPath = iAiImageIOService.drawRect(facePath, aiFaceRectangles);

        // 图片上传到minio
        String drawUrl = iAiMinioService.putObject("tmp", outputPath);
        Map<String, Object> data = new HashMap<>();
        data.put("drawRectUrl", drawUrl);
        data.put("detectionInfoList", detectionInfos);
        return data;
    }

    @Override
    public Map<String, Object> detectExpression(String facePicture) {
        // 下载到本地
        String facePath = iaiHttpRequestService.download(facePicture, "jpg");

        // 调用模型检测
        R<DetectionResponse> detectionResponseR = iAiFaceRecService.detectExpression(facePath);

        // 矩形区域列表
        List<AiFaceRectangle> aiFaceRectangles = new ArrayList<>();
        List<DetectionInfo> detectionInfos = detectionResponseR.getData().getDetectionInfoList();
        for (DetectionInfo detectionInfo : detectionInfos) {
            DetectionRectangle detectionRectangle = detectionInfo.getDetectionRectangle();

            int x = detectionRectangle.getX();
            int y = detectionRectangle.getY();
            int width = detectionRectangle.getWidth();
            int height = detectionRectangle.getHeight();

            String content = detectionInfo.getFaceInfo().getExpressionResult().getExpression().getDescription();
            AiFaceRectangle aiFaceRectangle = new AiFaceRectangle(content, x, y, width, height);
            aiFaceRectangles.add(aiFaceRectangle);
        }

        // 绘制矩形，并且返回图片路径
        String outputPath = iAiImageIOService.drawRect(facePath, aiFaceRectangles);

        // 图片上传到minio
        String drawRectUrl = iAiMinioService.putObject("tmp", outputPath);
        Map<String, Object> data = new HashMap<>();
        data.put("drawRectUrl", drawRectUrl);
        data.put("detectionInfoList", aiFaceRectangles);
        return data;
    }

    @Override
    public Map<String, Object> detectFaceAttribute(String facePicture) {

        // 下载到本地
        String facePath = iaiHttpRequestService.download(facePicture, "jpg");

        // 调用模型检测
        DetectionResponse detectionResponse = iAiFaceRecService.detectFaceAttribute(facePath);

        // 矩形区域列表
        List<AiFaceRectangle> aiFaceRectangles = new ArrayList<>();
        List<DetectionInfo> detectionInfos = detectionResponse.getDetectionInfoList();
        for (DetectionInfo detectionInfo : detectionInfos) {
            DetectionRectangle detectionRectangle = detectionInfo.getDetectionRectangle();

            int x = detectionRectangle.getX();
            int y = detectionRectangle.getY();
            int width = detectionRectangle.getWidth();
            int height = detectionRectangle.getHeight();

            FaceAttribute faceAttribute = detectionInfo.getFaceInfo().getFaceAttribute();
            String gender = faceAttribute.getGenderType().getDescription();
            int age = faceAttribute.getAge();
            String content = gender + " " + age + "岁";
            AiFaceRectangle aiFaceRectangle = new AiFaceRectangle(content, x, y, width, height);
            aiFaceRectangles.add(aiFaceRectangle);
        }

        // 绘制矩形，并且返回图片路径
        String outputPath = iAiImageIOService.drawRect(facePath, aiFaceRectangles);

        // 图片上传到minio
        String drawRectUrl = iAiMinioService.putObject("tmp", outputPath);
        Map<String, Object> data = new HashMap<>();
        data.put("drawRectUrl", drawRectUrl);
        data.put("detectionInfoList", detectionInfos);
        return data;
    }
}
