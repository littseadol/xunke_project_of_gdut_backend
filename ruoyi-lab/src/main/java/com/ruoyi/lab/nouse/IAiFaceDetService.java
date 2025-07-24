package com.ruoyi.lab.nouse;
import cn.smartjavaai.common.entity.DetectionResponse;
import cn.smartjavaai.common.entity.R;

public interface IAiFaceDetService {
    /**
     * 检测图片
     * @param imagePath
     * @return
     */
    //模型1：联网下载模型
    R<DetectionResponse> detect1(String imagePath);
    //模型2：高精度模型
    R<DetectionResponse> detect2(String imagePath);
    //模型3：SeetaFace6模型
    public R<DetectionResponse> detect3(String imagePath);
    //R<DetectionResponse> detect(BufferedImage image);
    //R<DetectionResponse> detect(byte[] imageData);
    //R<DetectionResponse> detect(InputStream imageInputStream);
    //R<DetectionResponse> detectBase64(String base64Image);


    /**
     * 检测并绘制人脸
     * @param imagePath 图片输入路径（包含文件名称）
     * @param outputPath 图片输出路径（包含文件名称）
     */
    R<Void> detectAndDraw(String imagePath, String outputPath);

    /**
     * 检测并绘制人脸
     * @param sourceImage
     * @return
     */
    //R<BufferedImage> detectAndDraw(BufferedImage sourceImage);
}
