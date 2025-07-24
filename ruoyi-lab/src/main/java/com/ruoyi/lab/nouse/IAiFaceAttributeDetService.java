package com.ruoyi.lab.nouse;

import cn.smartjavaai.common.entity.*;
import cn.smartjavaai.common.entity.face.FaceAttribute;
import cn.smartjavaai.common.enums.face.LivenessStatus;
import java.util.List;

public interface IAiFaceAttributeDetService {

    /**
     * 人脸属性检测（多人脸）
     * @param imagePath
     * @return
     */
    DetectionResponse detect(String imagePath);
    //DetectionResponse detect(BufferedImage image);
    //DetectionResponse detect(byte[] imageData);


    /**
     * 检测分数最高的人脸
     * @param imagePath
     * @return
     */
    LivenessStatus detectTopFace(String imagePath);
    //LivenessStatus detectTopFace(BufferedImage image);
    //LivenessStatus detectTopFace(byte[] imageData);


    /**
     * 人脸属性检测（多人脸）- 基于已检测出的人脸区域和关键点
     * @param imagePath
     * @param faceDetectionResponse
     * @return
     */
    List<FaceAttribute> detect(String imagePath, DetectionResponse faceDetectionResponse);
    //List<FaceAttribute> detect(BufferedImage image,DetectionResponse faceDetectionResponse);
    //List<FaceAttribute> detect(byte[] imageData, DetectionResponse faceDetectionResponse);


    /**
     * 人脸属性检测（单张人脸）- 基于已检测出的人脸区域和关键点
     * @param imagePath
     * @param faceDetectionRectangle
     * @param keyPoints
     * @return
     */
    FaceAttribute detect(String imagePath, DetectionRectangle faceDetectionRectangle, List<Point> keyPoints);
    //FaceAttribute detect(BufferedImage image, DetectionRectangle faceDetectionRectangle, List<Point> keyPoints);
    //FaceAttribute detect(byte[] imageData, DetectionRectangle faceDetectionRectangle, List<Point> keyPoints);

}
