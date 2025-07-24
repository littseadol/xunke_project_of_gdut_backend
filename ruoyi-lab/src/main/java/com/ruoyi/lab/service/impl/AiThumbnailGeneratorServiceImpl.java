package com.ruoyi.lab.service.impl;

import com.ruoyi.lab.service.IAiThumbnailGeneratorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.UUID;

@Service
@Slf4j
public class AiThumbnailGeneratorServiceImpl implements IAiThumbnailGeneratorService {
    @Override
    public String generateThumbnail(String filePath) {
        try {
            // 读取原始图片
            BufferedImage originalImage = ImageIO.read(new File(filePath));

            // 创建缩略图
            Image thumbnail = originalImage.getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            BufferedImage bufferedThumbnail = new BufferedImage(
                    200, 200, BufferedImage.TYPE_INT_RGB);

            // 绘制缩略图
            Graphics2D g2d = bufferedThumbnail.createGraphics();
            g2d.drawImage(thumbnail, 0, 0, null);
            g2d.dispose();

            // 保存缩略图
            String targetPath = "C:/Users/Ldolphin/Desktop/temp/" + UUID.randomUUID().toString().replace("-", "") + ".jpg";
            ImageIO.write(bufferedThumbnail, "jpg", new File(targetPath));
            return targetPath;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }
    }
}