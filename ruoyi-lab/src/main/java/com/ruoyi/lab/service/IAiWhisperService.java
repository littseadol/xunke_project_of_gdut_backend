package com.ruoyi.lab.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.lab.domain.request.WhisperRequest;
import org.springframework.web.multipart.MultipartFile;

public interface IAiWhisperService {
    /**
     * 语音转写服务
     * @param audioFile 音频文件
     * @param request 转写参数
     * @return 转写结果
     */
    AjaxResult transcribeAudio(MultipartFile audioFile, WhisperRequest request);

    /**
     * 服务健康检查
     * @return 服务状态
     */
    boolean healthCheck();
}
