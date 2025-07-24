package com.ruoyi.lab.nouse;

import cn.smartjavaai.common.entity.*;
import cn.smartjavaai.common.entity.face.FaceAttribute;
import cn.smartjavaai.common.entity.face.FaceInfo;
import cn.smartjavaai.common.enums.face.LivenessStatus;
import cn.smartjavaai.face.config.FaceAttributeConfig;
import cn.smartjavaai.face.config.FaceDetConfig;
import cn.smartjavaai.face.enums.FaceAttributeModelEnum;
import cn.smartjavaai.face.enums.FaceDetModelEnum;
import cn.smartjavaai.face.factory.FaceAttributeModelFactory;
import cn.smartjavaai.face.factory.FaceDetModelFactory;
import cn.smartjavaai.face.model.attribute.FaceAttributeModel;
import cn.smartjavaai.face.model.facedect.FaceDetModel;
import cn.smartjavaai.face.utils.FaceUtils;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.file.Paths;
import java.util.List;

/**
 * 人脸属性检测demo
 * 模型下载地址：https://pan.baidu.com/s/10l22x5fRz_gwLr8EAHa1Jg?pwd=1234 提取码: 1234
 * @author dwj
 */
@Slf4j
public class AiFaceAttributeDetServiceImpl implements IAiFaceAttributeDetService {
    public FaceAttributeModel getFaceAttributeModel() {
        FaceAttributeConfig config = new FaceAttributeConfig();
        config.setModelEnum(FaceAttributeModelEnum.SEETA_FACE6_MODEL);
        //需替换为实际模型存储路径
        config.setModelPath("C:/Users/Administrator/Downloads/sf3.0_models/sf3.0_models");
        return FaceAttributeModelFactory.getInstance().getModel(config);
    }

    public FaceDetModel getFaceDetModel() {
        //需替换为实际模型存储路径
        String modelPath = "C:/Users/Administrator/Downloads/sf3.0_models/sf3.0_models";
        FaceDetConfig faceDetectModelConfig = new FaceDetConfig();
        faceDetectModelConfig.setModelEnum(FaceDetModelEnum.SEETA_FACE6_MODEL);
        faceDetectModelConfig.setModelPath(modelPath);
        return FaceDetModelFactory.getInstance().getModel(faceDetectModelConfig);
    }


    /**
     * 人脸属性检测（多人脸）
     */
    @Override
    public DetectionResponse detect(String imagePath){
        try (FaceAttributeModel faceAttributeModel = getFaceAttributeModel()){
            DetectionResponse detectionResponse = faceAttributeModel.detect(imagePath);
            //绘制并导出人脸属性图片，小人脸仅有人脸框
            BufferedImage image = ImageIO.read(new File(Paths.get(imagePath).toAbsolutePath().toString()));
            //需指定输出图片路径
            FaceUtils.drawBoxesWithFaceAttribute(image, detectionResponse,"C:/Users/Administrator/Downloads/double_person_.png");
            log.info("人脸属性检测结果：{}", JSONObject.toJSONString(detectionResponse));
            return detectionResponse;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 图片人脸属性检测（分数最高人脸）
     */
    @Override
    public LivenessStatus detectTopFace(String imagePath){
        try (FaceAttributeModel faceAttributeModel = getFaceAttributeModel()){
            FaceAttribute faceAttribute = faceAttributeModel.detectTopFace(imagePath);
            log.info("人脸属性检测结果：{}", JSONObject.toJSONString(faceAttribute));
            return LivenessStatus.LIVE;
        } catch (Exception e) {
            e.printStackTrace();
            return LivenessStatus.UNKNOWN;
        }
    }

    /**
     * 图片多人脸属性检测（基于已检测出的人脸区域和关键点）
     */
    @Override
    public List<FaceAttribute> detect(String imagePath, DetectionResponse faceDetectionResponse){
        try (FaceAttributeModel faceAttributeModel = getFaceAttributeModel()){
            FaceAttribute faceAttribute = faceAttributeModel.detectTopFace(imagePath);
            log.info("人脸属性检测结果：{}", JSONObject.toJSONString(faceAttribute));
            return faceAttributeModel.detect(imagePath, faceDetectionResponse);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        //人脸检测



    }

    /**
     * 图片单人脸人脸属性检测（基于已检测出的人脸区域和关键点）
     */
    @Override
    public FaceAttribute detect(String imagePath, DetectionRectangle faceDetectionRectangle, List<Point> keyPoints){
        try (FaceDetModel faceDetModel = getFaceDetModel();
                FaceAttributeModel faceAttributeModel = getFaceAttributeModel()){
            //人脸检测
            BufferedImage image = ImageIO.read(new File(Paths.get(imagePath).toAbsolutePath().toString()));
            R<DetectionResponse> detectionResponse = faceDetModel.detect(image);
            if(detectionResponse.isSuccess()){
                log.info("人脸检测结果：{}", JSONObject.toJSONString(detectionResponse.getData()));
                //检测到人脸
                if(detectionResponse.getData() != null && detectionResponse.getData().getDetectionInfoList() != null && detectionResponse.getData().getDetectionInfoList().size() > 0){
                    for (DetectionInfo detectionInfo : detectionResponse.getData().getDetectionInfoList()){
                        FaceInfo faceInfo = detectionInfo.getFaceInfo();
                        FaceAttribute faceAttribute = faceAttributeModel.detect(image, detectionInfo.getDetectionRectangle(), faceInfo.getKeyPoints());
                        log.info("人脸属性检测结果：{}", JSONObject.toJSONString(faceAttribute));
                    }
                    return faceAttributeModel.detect(image, faceDetectionRectangle, keyPoints);
                }
            }else{
                log.info("人脸检测失败：{}", detectionResponse.getMessage());
                return null;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
