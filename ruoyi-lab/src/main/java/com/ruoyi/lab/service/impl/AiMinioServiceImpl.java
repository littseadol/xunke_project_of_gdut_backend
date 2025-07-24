package com.ruoyi.lab.service.impl;

import com.ruoyi.common.utils.uuid.UUID;
import com.ruoyi.lab.service.IAiMinioService;
import com.ruoyi.lab.service.IAiThumbnailGeneratorService;
import io.minio.*;
import io.minio.errors.*;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.ruoyi.common.utils.file.MimeTypeUtils.getExtension;

@Service
@Slf4j
public class AiMinioServiceImpl implements IAiMinioService {
    private final MinioClient minioClient;

    @Value("${minio.bucket-name}")
    private String bucketName;

    @Value("${minio.endpoint}")
    private String endpoint;

    @Autowired
    private IAiThumbnailGeneratorService iAiThumbnailGeneratorService;

    public AiMinioServiceImpl(@Value("${minio.endpoint}") String endpoint, @Value("${minio.access-key}")String accessKey, @Value("${minio.secret-key}")String secretKey) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        log.debug("init minio");
        log.debug("init minio endpoint : " + endpoint);
        log.debug("minio accessKey:{}", accessKey);
        log.debug("minio secretKey:{}", secretKey);

        minioClient = MinioClient.builder().
                credentials(accessKey, secretKey).
                endpoint(endpoint).
                build();
        log.debug("init minio success");
    }

    public Boolean bucketExists() {
        try {
            return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    public String putObject(String type, String filePath) {
        try {
            String objectName = type;

            // 如果是事件，则以年月日进行命名
            if (type.equals("event")) {
                LocalDateTime now = LocalDateTime.now(); // 获取当前时间
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd"); // 定义时间格式（无特殊字符，适合文件名）
                objectName = type + "/" + now.format(formatter);
            }

            int index = filePath.lastIndexOf(".");
            String suffix = filePath.substring(index + 1);

            // 如果是图片，则生成缩略图
            String thumbnailPath = null;
            if (suffix.equals("jpg") || suffix.equals("jpeg") || suffix.equals("png")) {
                thumbnailPath = thumbnailPath = iAiThumbnailGeneratorService.generateThumbnail(filePath);
            }

            // 生成文件名和缩略图文件名
            String uuid = UUID.randomUUID().toString().replace("-", "");
            long currentTimeMillis = System.currentTimeMillis();
            String fileName = uuid + "_" + currentTimeMillis + "." + suffix;
            String thumbnailName = uuid + "_" + currentTimeMillis + "_200X200.jpg";

            // 上传原文件
            minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName)
                    .object(objectName + "/" + fileName)
                    .stream(new FileInputStream(filePath), -1, 10485760)
                    .build());

            // 上传缩略图
            if (suffix.equals("jpg") || suffix.equals("jpeg") || suffix.equals("png")) {
                minioClient.putObject(PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName + "/" + thumbnailName)
                        .stream(new FileInputStream(thumbnailPath), -1, 10485760)
                        .build());
            }

            log.debug("minio put object success");
            String url = endpoint + "/" + bucketName + "/" + objectName + "/" + fileName;
            String thumbnailUrl = endpoint + "/" + bucketName + "/" + objectName + "/" + thumbnailName;
            log.debug("url：{}", url);
            log.debug("thumbnailUrl：{}", thumbnailUrl);
            return url;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }


    public String store(MultipartFile file) throws Exception {
        // 生成唯一文件名
        String fileName = UUID.randomUUID().toString() + getExtension(file.getOriginalFilename());
        String objectName = "uploads/" + fileName;

        // 检查存储桶是否存在
        boolean isExist = minioClient.bucketExists(BucketExistsArgs.builder()
                .bucket(bucketName)
                .build());
        if (!isExist) {
            minioClient.makeBucket(MakeBucketArgs.builder()
                    .bucket(bucketName)
                    .build());
        }

        // 上传文件
        minioClient.putObject(
                PutObjectArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .stream(file.getInputStream(), file.getSize(), -1)
                        .contentType(file.getContentType())
                        .build());

        // 返回访问URL
        return minioClient.getPresignedObjectUrl(
                GetPresignedObjectUrlArgs.builder()
                        .bucket(bucketName)
                        .object(objectName)
                        .method(Method.GET)
                        .build());
    }
}