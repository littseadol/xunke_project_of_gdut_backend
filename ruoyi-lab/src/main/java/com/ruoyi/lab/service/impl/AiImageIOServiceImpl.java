package com.ruoyi.lab.service.impl;

import com.ruoyi.lab.domain.AiFaceRectangle;
import com.ruoyi.lab.service.IAiImageIOService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class AiImageIOServiceImpl implements IAiImageIOService {
    @Override
    public String drawRect(String imagePath, List<AiFaceRectangle> aiFaceRectangles) {
        try {
            // 读取原始图片
            BufferedImage image = ImageIO.read(new File(imagePath));

            // 创建Graphics2D对象
            Graphics2D g2d = image.createGraphics();

            // 设置矩形颜色和样式
            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(3)); // 设置线宽

            // 设置字体
            Font font = new Font("Arial", Font.PLAIN, 25); // 字体名,样式,大小
            g2d.setFont(font);

            // 绘制矩形 (x, y, width, height)
            int index = 0;
            for (AiFaceRectangle aiFaceRectangle : aiFaceRectangles) {
                index += 1;
                String content = aiFaceRectangle.getContent();
                int x = aiFaceRectangle.getX();
                int y = aiFaceRectangle.getY();
                int width = aiFaceRectangle.getWidth();
                int height = aiFaceRectangle.getHeight();
                g2d.drawRect(x, y, width, height);
                g2d.drawString(index + " " + content, x, y - 10);
            }

            // 释放资源
            g2d.dispose();

            // 保存修改后的图片
            String outputPath = "/tmp/" + UUID.randomUUID() + ".jpg";
            ImageIO.write(image, "jpg", new File(outputPath));

            log.debug("矩形绘制完成！");
            return outputPath;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        }

    }
}
