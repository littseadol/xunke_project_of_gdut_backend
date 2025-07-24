package com.ruoyi.lab.nouse;
import cn.smartjavaai.common.entity.DetectionRectangle;
import cn.smartjavaai.common.entity.DetectionResponse;
import cn.smartjavaai.common.entity.R;
import cn.smartjavaai.common.entity.face.LivenessResult;
import cn.smartjavaai.common.enums.face.LivenessStatus;
import java.awt.*;
import java.io.InputStream;
import java.util.List;
public interface IAiLivenessDetService {
    /**
     * 图片活体检测（多人脸）
     */
    R<DetectionResponse> detect(String imagePath);
    /*R<DetectionResponse> detect(BufferedImage image);
    R<DetectionResponse> detect(byte[] imageData);
    R<DetectionResponse> detectBase64(String base64Image);*/

    /**
     * 基于已检测出的人脸区域和关键点的活体检测（多人脸）
     */
    R<List<LivenessResult>> detect(String imagePath, DetectionResponse faceDetectionResponse);
    /*R<List<LivenessResult>> detect(BufferedImage image, DetectionResponse faceDetectionResponse);
    R<List<LivenessResult>> detect(byte[] imageData, DetectionResponse faceDetectionResponse);
    R<List<LivenessResult>> detectBase64(String base64Image, DetectionResponse faceDetectionResponse);*/

    /**
     * 基于已检测出的人脸区域和关键点的活体检测（单人脸）
     */
    R<LivenessResult> detect(String imagePath, DetectionRectangle faceDetectionRectangle, List<Point> keyPoints);
    /*R<LivenessResult> detect(BufferedImage image, DetectionRectangle faceDetectionRectangle, List<Point> keyPoints);
    R<LivenessResult> detect(byte[] imageData, DetectionRectangle faceDetectionRectangle, List<Point> keyPoints);
    R<LivenessResult> detectBase64(String base64Image, DetectionRectangle faceDetectionRectangle, List<Point> keyPoints);*/

    /**
     * 基于已检测出的人脸区域的活体检测（单人脸）
     */
    R<LivenessResult> detect(String imagePath, DetectionRectangle faceDetectionRectangle);
    /*R<LivenessResult> detect(BufferedImage image, DetectionRectangle faceDetectionRectangle);
    R<LivenessResult> detect(byte[] imageData, DetectionRectangle faceDetectionRectangle);
    R<LivenessResult> detectBase64(String base64Image, DetectionRectangle faceDetectionRectangle);*/

    /**
     * 检测图片中分数最高的人脸
     */

    R<LivenessResult> detectTopFace(String imagePath);
    /*R<LivenessResult> detectTopFace(BufferedImage image);
    R<LivenessResult> detectTopFace(byte[] imageData);
    R<DetectionResponse> detectTopFaceBase64(String base64Image);*/

    /**
     * 视频活体检测
     */
    R<LivenessStatus> detectVideo(InputStream videoInputStream);
    R<LivenessStatus> detectVideo(String videoPath);

}
