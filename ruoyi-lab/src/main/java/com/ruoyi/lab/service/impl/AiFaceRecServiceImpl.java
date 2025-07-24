package com.ruoyi.lab.service.impl;

import cn.smartjavaai.common.config.Config;
import cn.smartjavaai.common.entity.DetectionInfo;
import cn.smartjavaai.common.entity.DetectionResponse;
import cn.smartjavaai.common.entity.R;
import cn.smartjavaai.common.entity.face.FaceSearchResult;
import cn.smartjavaai.common.enums.DeviceEnum;
import cn.smartjavaai.face.config.FaceAttributeConfig;
import cn.smartjavaai.face.config.FaceDetConfig;
import cn.smartjavaai.face.config.FaceExpressionConfig;
import cn.smartjavaai.face.config.FaceRecConfig;
import cn.smartjavaai.face.constant.FaceDetectConstant;
import cn.smartjavaai.face.entity.FaceRegisterInfo;
import cn.smartjavaai.face.entity.FaceSearchParams;
import cn.smartjavaai.face.enums.*;
import cn.smartjavaai.face.factory.ExpressionModelFactory;
import cn.smartjavaai.face.factory.FaceAttributeModelFactory;
import cn.smartjavaai.face.factory.FaceDetModelFactory;
import cn.smartjavaai.face.factory.FaceRecModelFactory;
import cn.smartjavaai.face.model.attribute.FaceAttributeModel;
import cn.smartjavaai.face.model.expression.ExpressionModel;
import cn.smartjavaai.face.model.facedect.FaceDetModel;
import cn.smartjavaai.face.model.facerec.FaceRecModel;
import cn.smartjavaai.face.utils.FaceUtils;
import cn.smartjavaai.face.vector.config.MilvusConfig;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.ruoyi.lab.service.IAiFaceRecService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 人脸识别服务实现类:人脸检测+人脸比对+人脸检索（向量数据库milvus 人脸增删改查）+人脸表情
 */


/**
 * FaceNet人脸算法模型demo
 * 支持系统：windows 64位，linux 64位，macOS M系列芯片
 * 支持功能：人脸特征提取、人脸比对（1：1）、人脸比对（1：N）、人脸注册
 * 模型下载地址：https://pan.baidu.com/s/10l22x5fRz_gwLr8EAHa1Jg?pwd=1234 提取码: 1234
 * @author dwj
 * @date 2025/4/11
 */
@Service
@Slf4j
public class AiFaceRecServiceImpl implements IAiFaceRecService {
    private String cachePath;

    private String elasticFaceModelPath;

    private String milvusUsername;
    private String milvusPassword;
    private String milvusIp;

    private Integer milvusPort;

    private String milvusCollectionName;

    //设备类型
    public static DeviceEnum device = DeviceEnum.CPU;

    private final FaceDetModel faceDetModel;
//    private final FaceRecModel faceRecModel;
    private final FaceRecModel faceRecModelWithDbConfig;
    private final ExpressionModel expressionModel;
    private final FaceAttributeModel faceAttributeModel;

    public AiFaceRecServiceImpl(@Value("${smartJavaAI.cachePath}") String cachePath, @Value("${smartJavaAI.elasticFaceModelPath}") String elasticFaceModelPath, @Value("${milvus.ip}") String milvusIp, @Value("${milvus.port}") Integer milvusPort, @Value("${milvus.collectionName}") String milvusCollectionName, @Value("${milvus.username}") String milvusUsername, @Value("${milvus.password}") String milvusPassword) {
        this.cachePath = cachePath;
        this.elasticFaceModelPath = elasticFaceModelPath;
        this.milvusUsername = milvusUsername;
        this.milvusPassword = milvusPassword;
        this.milvusIp = milvusIp;
        this.milvusPort = milvusPort;
        this.milvusCollectionName = milvusCollectionName;

        // 创建bean的时候设置缓存路径
        Config.setCachePath(this.cachePath);

        // 一开始加载模型
        faceDetModel = getFaceDetModel(); // 检测人脸
        log.debug("初始化人脸检测成功");

//        faceRecModel = getFaceRecModel(); // 比对
//        log.debug("初始化人脸比对成功");

        faceRecModelWithDbConfig = getFaceRecModelWithDbConfig(); // 检索
        log.debug("初始化人脸检索成功");

        expressionModel = getExpressionModel();
        log.debug("初始化人脸表情模型成功");

        faceAttributeModel = getFaceAttributeModel();
        log.debug("初始化人脸属性成功");
    }

    /**
     * 获取人脸检测模型
     * @return
     */
    public FaceDetModel getFaceDetModel(){
        FaceDetConfig config = new FaceDetConfig();
        config.setConfidenceThreshold(FaceDetectConstant.DEFAULT_CONFIDENCE_THRESHOLD);//只返回相似度大于该值的人脸
        config.setNmsThresh(FaceDetectConstant.NMS_THRESHOLD);//用于去除重复的人脸框，当两个框的重叠度超过该值时，只保留一个
        config.setDevice(device);

        config.setModelEnum(FaceDetModelEnum.SEETA_FACE6_MODEL);
        config.setModelPath("C:/Users/Administrator/Downloads/sf3.0_models/sf3.0_models");

        return FaceDetModelFactory.getInstance().getModel(config);
    }

    /**
     * 获取人脸识别模型
     * @return
     */
    public FaceRecModel getFaceRecModel(){
        FaceRecConfig config = new FaceRecConfig();

        //裁剪人脸：如果图片已经是裁剪过的，则请将此参数设置为false
        config.setCropFace(true);
        //开启人脸对齐：适用于人脸不正的场景，开启将提升人脸特征准确度，关闭可以提升性能
        config.setAlign(true);
        config.setDevice(device);
        //指定人脸检测模型
        config.setDetectModel(faceDetModel);

        config.setModelEnum(FaceRecModelEnum.SEETA_FACE6_MODEL);
        config.setModelPath("C:/Users/Administrator/Downloads/sf3.0_models/sf3.0_models");

        return FaceRecModelFactory.getInstance().getModel(config);
    }

    /**
     * 获取人脸识别模型(带向量数据库配置)
     * @return
     */
    public FaceRecModel getFaceRecModelWithDbConfig(){
        FaceRecConfig config = new FaceRecConfig();

        //裁剪人脸：如果图片已经是裁剪过的，则请将此参数设置为false
        config.setCropFace(true);
        //开启人脸对齐：适用于人脸不正的场景，开启将提升人脸特征准确度，关闭可以提升性能
        config.setAlign(true);
        //指定人脸检测模型
        config.setDetectModel(faceDetModel);
        config.setDevice(device);

        config.setModelEnum(FaceRecModelEnum.SEETA_FACE6_MODEL);
        config.setModelPath("C:/Users/Administrator/Downloads/sf3.0_models/sf3.0_models");
//        config.setModelEnum(FaceRecModelEnum.ELASTIC_FACE_MODEL);//人脸检测模型
//        config.setModelPath("/home/zhangqinxiong/elasticface.pt");


        //初始化向量数据库：Milvus数据库配置
        MilvusConfig vectorDBConfig = new MilvusConfig();
//        vectorDBConfig.setUsername(milvusUsername);
//        vectorDBConfig.setPassword(milvusPassword);
        vectorDBConfig.setHost(milvusIp);
        vectorDBConfig.setPort(milvusPort);
        vectorDBConfig.setCollectionName(milvusCollectionName);

        //ID策略：自动生成
        vectorDBConfig.setIdStrategy(IdStrategy.CUSTOM);

        //索引类型:内积 (Inner Product) 不建议修改
        //vectorDBConfig.setMetricType(MetricType.IP);

        config.setVectorDBConfig(vectorDBConfig);
        return FaceRecModelFactory.getInstance().getModel(config);
    }

    /**
     * 获取表情识别模型
     * @return
     */
    public ExpressionModel getExpressionModel(){
        FaceExpressionConfig config = new FaceExpressionConfig();
        config.setModelEnum(ExpressionModelEnum.FrEmotion);
        config.setModelPath("C:/Users/Administrator/Downloads/FrEmotion/fr_expression.onnx");
        config.setDevice(device);
        config.setAlign(true);
        config.setDetectModel(getFaceDetModel());
        return ExpressionModelFactory.getInstance().getModel(config);
    }

    public FaceAttributeModel getFaceAttributeModel() {
        FaceAttributeConfig config = new FaceAttributeConfig();
        config.setModelEnum(FaceAttributeModelEnum.SEETA_FACE6_MODEL);
        //需替换为实际模型存储路径
        config.setModelPath("C:/Users/Administrator/Downloads/sf3.0_models/sf3.0_models");
        config.setDevice(device);
        return FaceAttributeModelFactory.getInstance().getModel(config);
    }

    /**
     * 检测表情
     * @param imagePath 图片路径
     * @return 结果
     */
    public R<DetectionResponse> detectExpression(String imagePath) {
        R<DetectionResponse> result = expressionModel.detect(imagePath);

        if(result.isSuccess()){
            List<DetectionInfo> detectionInfos = result.getData().getDetectionInfoList();
            for (DetectionInfo detectionInfo : detectionInfos) {
                log.info("识别结果：{}", JSONObject.toJSONString(detectionInfo.getFaceInfo().getExpressionResult().getExpression().getDescription()));
            }
        }else{
            log.info("识别失败：{}", result.getMessage());
        }
        return result;
    }

    /**
     * 检测人脸属性
     * @param imagePath 图片路径
     * @return 结果
     */
    public DetectionResponse detectFaceAttribute(String imagePath) {
        DetectionResponse detectionResponse = faceAttributeModel.detect(imagePath);
        log.info("人脸属性检测结果：{}", JSONObject.toJSONString(detectionResponse));
        return detectionResponse;
    }

//------------------------------------------------------------------------------------------------------------------------------
    /**
     * 提取人脸特征(多人脸场景)
     * 自动裁剪人脸（处理耗时略有增加）
     * 注意事项：
     * 1、首次调用接口，可能会较慢。只要不关闭程序，后续调用会明显加快。若每次重启程序，则每次首次调用都将重新加载，仍会较慢。
     * 2、若人脸朝向不正，可开启人脸对齐以提升特征提取准确度。（方法参考自定义配置人脸特征提取）
     */
    @Override
    public R<DetectionResponse> extractFeatures(String imagePath) {
        try {
            R<DetectionResponse> result = getFaceRecModel().extractFeatures(imagePath);
            logResult(result);
            return result;
        } catch (Exception e) {
            log.error("提取人脸特征失败，图片路径: {}", imagePath, e);
            return R.fail(-1, "提取人脸特征失败: " + e.getMessage());
        }
    }
    @Override
    public R<DetectionResponse> extractFeatures(BufferedImage sourceImage) {
        try {
            R<DetectionResponse> result = getFaceRecModel().extractFeatures(sourceImage);
            logResult(result);
            return result;
        } catch (Exception e) {
            log.error("提取人脸特征失败", e);
            return R.fail(-1, "提取人脸特征失败: " + e.getMessage());
        }
    }
    @Override
    public R<DetectionResponse> extractFeatures(byte[] imageData) {
        try {
            R<DetectionResponse> result = getFaceRecModel().extractFeatures(imageData);
            logResult(result);
            return result;
        } catch (Exception e) {
            log.error("提取人脸特征失败", e);
            return R.fail(-1, "提取人脸特征失败: " + e.getMessage());
        }
    }
    /**
     * 记录提取结果日志
     */
    private void logResult(R<DetectionResponse> result) {
        if (result.isSuccess()) {
            DetectionResponse data = result.getData();
            if (data != null && data.getDetectionInfoList() != null) {
                log.info("成功提取 {} 张人脸特征", data.getDetectionInfoList().size());
            }
        } else {
            log.warn("人脸特征提取失败: {}", result.getMessage());
        }
    }
    //---------------------------------------------------------------------------------------------------------------------------
    /**
     * 人脸比对1：1（基于图像直接比对）
     * 流程：从输入图像中裁剪分数最高的人脸 → 提取其人脸特征 → 比对两张图片中提取的人脸特征。（接口内自动完成）
     * 注意事项：
     * 1、首次调用接口，可能会较慢。只要不关闭程序，后续调用会明显加快。若每次重启程序，则每次首次调用都将重新加载，仍会较慢。
     * 2、若人脸朝向不正，可开启人脸对齐以提升特征提取准确度。（方法参考自定义配置人脸特征提取）
     * @throws Exception
     */
    @Override
    public Float featureComparison(String imagePath1, String imagePath2) {
        try {
            R<Float> similarResult = faceRecModelWithDbConfig.featureComparison(imagePath1, imagePath2);
            log.debug("similarResult: {}", JSON.toJSONString(similarResult));
            if(similarResult.isSuccess()){
                //相似度阈值不同模型不同，具体参看文档
                log.info("人脸比对相似度：{}", JSONObject.toJSONString(similarResult.getData()));
                return similarResult.getData();
            }else{
                log.info("人脸比对失败：{}", similarResult.getMessage());
                return null;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public float featureComparison(BufferedImage sourceImage1, BufferedImage sourceImage2) {
        try (FaceRecModel faceRecModel = getFaceRecModel()){
            R<Float> similarResult = faceRecModel.featureComparison(sourceImage1, sourceImage2);
            if(similarResult.isSuccess()){
                //相似度阈值不同模型不同，具体参看文档
                log.info("人脸比对相似度：{}", JSONObject.toJSONString(similarResult.getData()));
                return similarResult.getData();
            }else{
                log.info("人脸比对失败：{}", similarResult.getMessage());
                return 0.0f;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return 0.0f;
        }
    }

    @Override
    public float featureComparison(byte[] imageData1, byte[] imageData2) {
        try (FaceRecModel faceRecModel = getFaceRecModel()){
            R<Float> similarResult = faceRecModel.featureComparison(imageData1, imageData2);
            if(similarResult.isSuccess()){
                //相似度阈值不同模型不同，具体参看文档
                log.info("人脸比对相似度：{}", JSONObject.toJSONString(similarResult.getData()));
                return similarResult.getData();
            }else{
                log.info("人脸比对失败：{}", similarResult.getMessage());
                return 0.0f;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return 0.0f;
        }
    }

    /**
     * 人脸比对1：1（基于特征值比对）
     * 流程：从输入图像中裁剪分数最高的人脸 → 提取其人脸特征 → 比对两张图片中提取的人脸特征。
     * 注意事项：
     * 1、首次调用接口，可能会较慢。只要不关闭程序，后续调用会明显加快。若每次重启程序，则每次首次调用都将重新加载，仍会较慢。
     * 2、若人脸朝向不正，可开启人脸对齐以提升特征提取准确度。（方法参考自定义配置人脸特征提取）
     * @throws Exception
     */

    public float featureComparison2(String imagePath1, String imagePath2){
        try (FaceRecModel faceRecModel = getFaceRecModel()){
            //特征提取（提取分数最高人脸特征）,适用于单人脸场景
            R<float[]> featureResult1 = faceRecModel.extractTopFaceFeature(imagePath1);
            if(featureResult1.isSuccess()){
                log.info("图片1人脸特征提取成功：{}", JSONObject.toJSONString(featureResult1.getData()));
            }else{
                log.info("图片1人脸特征提取失败：{}", featureResult1.getMessage());
            }
            //特征提取（提取分数最高人脸特征）,适用于单人脸场景
            R<float[]> featureResult2 = faceRecModel.extractTopFaceFeature(imagePath2);
            if(featureResult2.isSuccess()){
                log.info("图片2人脸特征提取成功：{}", JSONObject.toJSONString(featureResult2.getData()));
            }else{
                log.info("图片2人脸特征提取失败：{}", featureResult2.getMessage());
            }
            //计算相似度
            float similar = faceRecModel.calculSimilar(featureResult1.getData(), featureResult2.getData());
            log.info("相似度：{}", similar);
            return similar;
        }
        catch (Exception e){
            e.printStackTrace();
            return 0.0f;
        }
    }

    //---------------------------------------------------------------------------------------------------------------------------


    /**
     * 人脸注册 + 人脸更新 + 人脸查询 + 人脸删除（使用向量数据库Milvus）
     * 流程：从输入图像中裁剪分数最高的人脸 → 提取其人脸特征 → 注册人脸
     * 注意事项：
     * 1、首次调用接口，可能会较慢。只要不关闭程序，后续调用会明显加快。若每次重启程序，则每次首次调用都将重新加载，仍会较慢。
     * 2、若人脸朝向不正，可开启人脸对齐以提升特征提取准确度。（方法参考自定义配置人脸特征提取）
     * @throws Exception
     */
    /**
     * 人脸注册
     * @param faceRegisterInfo 人脸注册信息:详见实体类 FaceRegisterInfo
     * @param imagePath 人脸图片路径
     * @return 注册结果
     */
    @Override
    public Integer register(FaceRegisterInfo faceRegisterInfo, String imagePath) {
        try {
            //等待加载人脸库结束
            while (!faceRecModelWithDbConfig.isLoadFaceCompleted()){
                Thread.sleep(100);
            }
            R<float[]> featureResult = faceRecModelWithDbConfig.extractTopFaceFeature(imagePath);
            if (!featureResult.isSuccess()) {
//                return R.fail(featureResult.getCode(), "特征提取失败: " + featureResult.getMessage());
                return 0;
            }
            faceRecModelWithDbConfig.register(faceRegisterInfo, featureResult.getData());
            return 1;
        } catch (Exception e) {
            log.error("人脸注册失败，图片路径: {}", imagePath, e);
//            return R.fail(-1, "人脸注册失败: " + e.getMessage());
            return 0;
        }
    }
    /**
     * 人脸注册
     * @param faceRegisterInfo 人脸注册信息:详见实体类 FaceRegisterInfo
     * @param feature 特征向量
     * @return 注册结果
     */
    @Override
    public R<String> register(FaceRegisterInfo faceRegisterInfo, float[] feature) {
        try {
            //等待加载人脸库结束
            while (!faceRecModelWithDbConfig.isLoadFaceCompleted()){
                Thread.sleep(100);
            }
            return faceRecModelWithDbConfig.register(faceRegisterInfo, feature);
        } catch (Exception e) {
            log.error("人脸注册失败", e);
            return R.fail(-1, "人脸注册失败: " + e.getMessage());
        }
    }

    /**
     * 更新人脸
     * @param faceRegisterInfo
     * @param imagePath
     */
    @Override
    public Integer upsertFace(FaceRegisterInfo faceRegisterInfo, String imagePath) {
        try {
            //等待加载人脸库结束
            while (!faceRecModelWithDbConfig.isLoadFaceCompleted()){
                Thread.sleep(100);
            }
            R<float[]> featureResult = faceRecModelWithDbConfig.extractTopFaceFeature(imagePath);
            if (featureResult.isSuccess()) {
                faceRecModelWithDbConfig.upsertFace(faceRegisterInfo, featureResult.getData());
                log.info("人脸更新成功，ID: {}", faceRegisterInfo.getId());
                return 1;
            } else {
                log.error("人脸更新失败，特征提取失败: {}", featureResult.getMessage());
                return 0;
            }
        } catch (Exception e) {
            log.error("人脸更新失败，图片路径: {}", imagePath, e);
            return 0;
        }
    }
    /**
     * 更新人脸
     * @param faceRegisterInfo
     * @param feature
     */
    @Override
    public void upsertFace(FaceRegisterInfo faceRegisterInfo, float[] feature) {
        try {
            //等待加载人脸库结束
            while (!faceRecModelWithDbConfig.isLoadFaceCompleted()){
                Thread.sleep(100);
            }
            faceRecModelWithDbConfig.upsertFace(faceRegisterInfo, feature);
            log.info("人脸更新成功，ID: {}", faceRegisterInfo.getId());
        } catch (Exception e) {
            log.error("人脸更新失败", e);
        }
    }

    /**
     * 删除人脸
     * @param keys
     * @param keys 待删除的人脸ID列表
     **/
    @Override
    public Integer removeRegister(String... keys) {
        try {
            //等待加载人脸库结束
            while (!faceRecModelWithDbConfig.isLoadFaceCompleted()){
                Thread.sleep(100);
            }
            faceRecModelWithDbConfig.removeRegister(keys);
            log.info("人脸删除成功，IDs: {}", (Object) keys);
            return 1;
        } catch (Exception e) {
            log.error("人脸删除失败", e);
            return 0;
        }
    }

    /**
     * 清空人脸库
     */
    @Override
    public void clearFace() {
        try {
            //等待加载人脸库结束
            while (!faceRecModelWithDbConfig.isLoadFaceCompleted()){
                log.debug("正在加载");
                Thread.sleep(100);
            }
            log.debug("加载成功");
            faceRecModelWithDbConfig.clearFace();
            log.info("人脸库已清空");
        } catch (Exception e) {
            log.error("人脸库清空失败", e);
        }
    }

    /**
     * 人脸查询
     * @param imagePath 图片路径
     * @param params 查询参数FaceSearchParams faceSearchParams = new FaceSearchParams();
     *             faceSearchParams.setTopK(1);
     *             faceSearchParams.setThreshold(0.8f);
     * @return 查询结果
     */
    @Override
    public R<DetectionResponse> search(String imagePath, FaceSearchParams params) {
        try {
            //等待加载人脸库结束
            while (!faceRecModelWithDbConfig.isLoadFaceCompleted()){
                Thread.sleep(100);
            }
            R<DetectionResponse> result = faceRecModelWithDbConfig.search(imagePath, params);
            log.debug("人脸检索结果：{}", JSON.toJSONString(result));
            if (result.isSuccess()) {
                log.info("人脸查询成功，图片路径: {}", imagePath);
            } else {
                log.warn("人脸查询失败: {}", result.getMessage());
            }
            return result;
        } catch (Exception e) {
            log.error("人脸查询失败，图片路径: {}", imagePath, e);
            return R.fail(-1, "人脸查询失败: " + e.getMessage());
        }
    }

    /**
     * 单人脸查询
     * @param imagePath 图片路径
     * @param params 查询参数
     * @return 查询结果
     */
    @Override
    public R<List<FaceSearchResult>> searchByTopFace(String imagePath, FaceSearchParams params) {
        try {
            //等待加载人脸库结束
            while (!faceRecModelWithDbConfig.isLoadFaceCompleted()){
                Thread.sleep(100);
            }
            R<List<FaceSearchResult>> result = faceRecModelWithDbConfig.searchByTopFace(imagePath, params);
            if (result.isSuccess()) {
                log.info("单人脸查询成功，图片路径: {}", imagePath);
            } else {
                log.warn("单人脸查询失败: {}", result.getMessage());
            }
            return result;
        } catch (Exception e) {
            log.error("单人脸查询失败，图片路径: {}", imagePath, e);
            return R.fail(-1, "单人脸查询失败: " + e.getMessage());
        }
    }

    /**
     * 基于特征值查询
     */
    @Override
    public List<FaceSearchResult> search(float[] feature, FaceSearchParams params) {
        try {
            //等待加载人脸库结束
            while (!faceRecModelWithDbConfig.isLoadFaceCompleted()){
                Thread.sleep(100);
            }
            List<FaceSearchResult> results = faceRecModelWithDbConfig.search(feature, params);
            log.info("基于特征值查询成功，结果数量: {}", results.size());
            return results;
        } catch (Exception e) {
            log.error("基于特征值查询失败", e);
            throw new RuntimeException("基于特征值查询失败: " + e.getMessage());
        }
    }
}
