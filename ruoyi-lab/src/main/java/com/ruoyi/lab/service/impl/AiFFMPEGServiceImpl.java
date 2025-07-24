package com.ruoyi.lab.service.impl;
import com.ruoyi.lab.service.IAiFFMPEGService;
import com.ruoyi.lab.service.IAiMinioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@Slf4j
public class AiFFMPEGServiceImpl implements IAiFFMPEGService {

    @Value("${ffmpeg-rtsp.temp-dir}")
    private String ffmpegRtspTempDir;

    @Autowired
    private IAiMinioService iAiMinioService;

    public String getSnapshotFromRtspUrl(String rtspUrl) throws Exception {
        String filePath = "/tmp/" + UUID.randomUUID().toString().replace("-", "") + ".jpg";
        log.debug("filePath：{}", filePath);
        ProcessBuilder pb = new ProcessBuilder(
                "ffmpeg",
                "-i", rtspUrl,
                "-vframes", "1",
                "-f", "image2",
                "-q:v", "1",
                filePath
        );
        pb.redirectErrorStream(true);
        Process process = pb.start();
        int exitCode = process.waitFor();
        if (exitCode == 0) {
            log.debug("图像抓取成功，保存为: " + filePath);

            // 把图片上传到oss
            String type = "device-snapshot";
            String url = iAiMinioService.putObject(type, filePath);
            log.debug("成功上传到oss");
            log.debug("url:{}", url);
            return url;
        } else {
            log.debug("FFmpeg 执行失败。");
            return null;
        }
    }
}