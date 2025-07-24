package com.ruoyi.lab.nouse;
import cn.smartjavaai.common.entity.DetectionRectangle;
import cn.smartjavaai.common.entity.DetectionResponse;
import cn.smartjavaai.common.entity.R;
import cn.smartjavaai.common.entity.face.ExpressionResult;
import java.util.List;

public interface IAiExpressionRecService {


    /**
     * 表情识别（单人脸）
     * @param imagePath
     * @return
     */
    R<ExpressionResult> detectExpression(String imagePath);

    /**
     * 表情识别（多人脸）
     * @param imagePath
     * @return
     */
    R<DetectionResponse> detect(String imagePath);
    //R<DetectionResponse> detect(BufferedImage image);
    //R<DetectionResponse> detect(byte[] imageData);
    //R<DetectionResponse> detectBase64(String base64Image);




    /**
     * 人脸表情识别（多人脸）- 基于已检测出的人脸区域和关键点
     * @param imagePath
     * @param faceDetectionResponse
     * @return
     */
    R<List<ExpressionResult>> detect(String imagePath, DetectionResponse faceDetectionResponse);
    /*R<List<ExpressionResult>> detect(BufferedImage image,DetectionResponse faceDetectionResponse)
    R<List<ExpressionResult>> detect(byte[] imageData,DetectionResponse faceDetectionResponse)
    R<List<ExpressionResult>> detectBase64(String base64Image, DetectionResponse faceDetectionResponse)*/



    /**
     * 人脸表情识别（单人脸）- 基于已检测出的人脸区域和关键点
     *     imagePath：图片路径
     *     faceDetectionRectangle：人脸检测框（人脸检测返回结果）
     *     keyPoints：5个人脸关键点（人脸检测返回结果）,部分模型不需要
     * @param imagePath
     * @param faceDetectionRectangle
     * @param
     * @return
     */
    //R<ExpressionResult> detect(String imagePath, DetectionRectangle faceDetectionRectangle, List<Point> keyPoints);
    /*R<ExpressionResult> detect(BufferedImage image, DetectionRectangle faceDetectionRectangle, List<Point> keyPoints)
    R<ExpressionResult> detect(byte[] imageData, DetectionRectangle faceDetectionRectangle, List<Point> keyPoints)
    R<ExpressionResult> detectBase64(String base64Image, DetectionRectangle faceDetectionRectangle, List<Point> keyPoints)*/

    R<ExpressionResult> detect(String imagePath, DetectionRectangle faceDetectionRectangle);
    //R<ExpressionResult> detect(BufferedImage image, DetectionRectangle faceDetectionRectangle)
    //R<ExpressionResult> detect(byte[] imageData, DetectionRectangle faceDetectionRectangle)
    //R<ExpressionResult> detectBase64(String base64Image, DetectionRectangle faceDetectionRectangle)



    /**
     *
     脸表情识别（分数最高人脸）
     检测图片中分数最高的人脸
     * @param imagePath
     * @return
     */
    //R<ExpressionResult> detectTopFace(String imagePath);
    /*R<ExpressionResult> detectTopFace(BufferedImage image);
    R<ExpressionResult> detectTopFace(byte[] imageData);
    R<ExpressionResult> detectTopFaceBase64(String base64Image);*/



}
