package com.ruoyi.lab.nouse;

import cn.smartjavaai.common.entity.DetectionResponse;
import cn.smartjavaai.common.entity.R;
import cn.smartjavaai.face.config.FaceDetConfig;
import cn.smartjavaai.face.constant.FaceDetectConstant;
import cn.smartjavaai.face.enums.FaceDetModelEnum;
import cn.smartjavaai.face.factory.FaceDetModelFactory;
import cn.smartjavaai.face.model.facedect.FaceDetModel;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;

/**
 * 人脸检测模型demo
 * 支持系统：windows 64位，linux 64位, macos M系列
 * 支持功能：人脸检测
 * 模型下载地址：https://pan.baidu.com/s/1d2YlJ2YOdGn3Y-AegyAhmQ?pwd=1234 提取码: 1234
 * @author dwj
 */
@Slf4j
public class AiFaceDetServiceImpl implements IAiFaceDetService {
    /**
     * 获取人脸检测模型
     * 注意事项：高精度模型，速度较慢
     * @return
     */
    public FaceDetModel getFaceDetModel(){
        FaceDetConfig config = new FaceDetConfig();
        config.setModelEnum(FaceDetModelEnum.RETINA_FACE);//人脸检测模型
        config.setConfidenceThreshold(FaceDetectConstant.DEFAULT_CONFIDENCE_THRESHOLD);//只返回相似度大于该值的人脸
        config.setNmsThresh(FaceDetectConstant.NMS_THRESHOLD);//用于去除重复的人脸框，当两个框的重叠度超过该值时，只保留一个
        return FaceDetModelFactory.getInstance().getModel(config);
    }

    /**
     * 获取Seetaface6 人脸检测模型
     * 注意：不支持macos
     * @return
     */
    public FaceDetModel getSeetaface6DetModel(){
        FaceDetConfig config = new FaceDetConfig();
        //指定模型
        config.setModelEnum(FaceDetModelEnum.SEETA_FACE6_MODEL);
        //指定模型路径：请根据实际情况替换为本地模型文件的绝对路径（模型下载地址请查看文档）
        config.setModelPath("C:/Users/Administrator/Downloads/sf3.0_models/sf3.0_models");
        return FaceDetModelFactory.getInstance().getModel(config);
    }

    /**
     * 人脸检测(默认配置)
     * 使用默认模型参数检测，默认模型：retinaface，需联网，会自动下载模型
     * 图片参数：图片路径
     */
    @Override
    public R<DetectionResponse> detect1(String imagePath) {
        try (FaceDetModel faceModel = FaceDetModelFactory.getInstance().getModel()) {
            R<DetectionResponse> detectedResult = faceModel.detect(imagePath);
            if(detectedResult.isSuccess()){
                log.info("人脸检测结果：{}", JSONObject.toJSONString(detectedResult.getData()));
                return detectedResult;
            }else{
                log.info("人脸检测失败：{}", detectedResult.getMessage());
                return R.fail(R.Status.NO_FACE_DETECTED);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return R.ok();
    }

    /**
     * 人脸检测(自定义配置)
     * 高精度模型：速度较慢
     * @param imagePath
     * @return
     */
    @Override
    public R<DetectionResponse> detect2(String imagePath) {
        // 可以在这里实现不同模型的检测逻辑
        try (FaceDetModel faceModel = getFaceDetModel()){
            R<DetectionResponse> detectedResult = faceModel.detect(imagePath);
            if(detectedResult.isSuccess()){
                log.info("人脸检测结果：{}", JSONObject.toJSONString(detectedResult.getData()));
                return detectedResult;
            }else{
                log.info("人脸检测失败：{}", detectedResult.getMessage());
                return R.fail(R.Status.NO_FACE_DETECTED);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 人脸检测(自定义配置)
     * Seetaface6模型
     * @param imagePath
     * @return
     */
    @Override
    public R<DetectionResponse> detect3(String imagePath) {
        // 可以在这里实现不同模型的检测逻辑
        try (FaceDetModel faceModel = getSeetaface6DetModel()){
            R<DetectionResponse> detectedResult = faceModel.detect(imagePath);
            if(detectedResult.isSuccess()){
                log.info("人脸检测结果：{}", JSONObject.toJSONString(detectedResult.getData()));
                return detectedResult;
            }else{
                log.info("人脸检测失败：{}", detectedResult.getMessage());
                return R.fail(R.Status.NO_FACE_DETECTED);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 人脸检测并绘制结果
     * @param imagePath
     * @param outputPath
     * @return
     */
    @Override
    public R<Void> detectAndDraw(String imagePath, String outputPath){
        try (FaceDetModel faceModel = getFaceDetModel()){
            faceModel.detectAndDraw(imagePath,outputPath);
            return R.ok();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
