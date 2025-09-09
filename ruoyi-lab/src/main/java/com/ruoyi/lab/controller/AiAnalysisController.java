package com.ruoyi.lab.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.lab.domain.request.WhisperRequest;
import com.ruoyi.lab.service.impl.AiWhisperServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * ai视频分析Controller
 *
 * @author littseadol
 * @date 2025-08-29
 */
@RestController
@RequestMapping("/lab/analysis")
@Slf4j
public class AiAnalysisController extends BaseController {

    @Autowired
    private AiWhisperServiceImpl whisperService;

    /**
     * 语音转写接口 - 支持文件上传和参数配置
     * POST /lab/analysis/transcribe?encode=true&task=transcribe&language=zh&output=txt
     */
    @PostMapping(value = "/transcribe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public AjaxResult transcribe(
            @RequestPart("audio_file") MultipartFile audioFile,
            @RequestParam(value = "encode", defaultValue = "true") Boolean encode,
            @RequestParam(value = "task", defaultValue = "transcribe") String task,
            @RequestParam(value = "language", defaultValue = "zh") String language,
            @RequestParam(value = "initial_prompt", required = false) String initialPrompt,
            @RequestParam(value = "output", defaultValue = "txt") String output) {

        log.info("收到转录请求，文件大小: {} bytes", audioFile.getSize());
        log.info("参数: task={}, language={}, output={}", task, language, output);

        try {
            if (audioFile.isEmpty()) {
                return AjaxResult.error("音频文件为空");
            }

            // 打印接收到的文件信息
            log.info("接收文件: 名称={}, 类型={}, 大小={} bytes",
                    audioFile.getOriginalFilename(),
                    audioFile.getContentType(),
                    audioFile.getSize());

            WhisperRequest request = new WhisperRequest();
            request.setEncode(encode);
            request.setTask(task);
            request.setLanguage(language);
            request.setInitialPrompt(initialPrompt);
            request.setOutput(output);

            return whisperService.transcribeAudio(audioFile, request);
        } catch (Exception e) {
            log.error("转录处理失败", e);
            return AjaxResult.error("转录失败: " + e.getMessage());
        }
    }

    /**
     * 异常统一处理
     */
    private AjaxResult handleException(Exception e) {
        if (e instanceof IllegalArgumentException) {
            return AjaxResult.error(422, "参数验证失败: " + e.getMessage());
        } else {
            return AjaxResult.error("语音转写失败: " + e.getMessage());
        }
    }
}
