package com.ruoyi.lab.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.lab.domain.request.WhisperRequest;
import com.ruoyi.lab.service.IAiWhisperService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
public class AiWhisperServiceImpl implements IAiWhisperService {

    @Value("${whisper.server.url:http://localhost:9090}")
    private String whisperServerUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public AjaxResult transcribeAudio(MultipartFile audioFile, WhisperRequest request) {
        try {
            // 参数验证
            validateRequest(request);

            // 保存临时文件
            Path tempPath = Files.createTempFile("whisper_", getFileExtension(audioFile));
            audioFile.transferTo(tempPath);

            try {
                // 调用Whisper服务
                String result = callWhisperService(tempPath.toFile(), request);
                return buildSuccessResponse(result, audioFile.getOriginalFilename());

            } finally {
                // 清理临时文件
                Files.deleteIfExists(tempPath);
            }

        } catch (Exception e) {
            return buildErrorResponse(e);
        }
    }

    /**
     * 调用Whisper服务核心方法
     */
    private String callWhisperService(File audioFile, WhisperRequest request) {
        // 构建请求URL
        String url = whisperServerUrl + "/asr" + buildQueryParams(request);

        // 准备请求头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        headers.set("accept", getAcceptHeader(request.getOutput()));

        // 构建multipart/form-data请求体
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("audio_file", new FileSystemResource(audioFile));

        // 添加initial_prompt参数（如果存在）
        if (request.getInitialPrompt() != null && !request.getInitialPrompt().isEmpty()) {
            body.add("initial_prompt", request.getInitialPrompt());
        }

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        // 发送请求并处理响应
        ResponseEntity<String> response = restTemplate.exchange(
                url, HttpMethod.POST, requestEntity, String.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            return response.getBody();
        } else {
            throw new RuntimeException("Whisper服务返回错误: " + response.getStatusCode());
        }
    }

    /**
     * 构建查询参数
     */
    private String buildQueryParams(WhisperRequest request) {
        StringBuilder params = new StringBuilder("?");
        params.append("encode=").append(request.getEncode());
        params.append("&task=").append(request.getTask());
        params.append("&language=").append(request.getLanguage());
        params.append("&output=").append(request.getOutput());
        return params.toString();
    }

    /**
     * 根据输出格式设置Accept头
     */
    private String getAcceptHeader(String output) {
        switch (output) {
            case "json":
                return "application/json";
            case "txt":
                return "text/plain";
            case "vtt":
            case "srt":
            case "tsv":
                return "text/" + output;
            default:
                return "application/json";
        }
    }

    /**
     * 参数验证
     */
    private void validateRequest(WhisperRequest request) {
        // 验证task参数
        if (!"transcribe".equals(request.getTask()) && !"translate".equals(request.getTask())) {
            throw new IllegalArgumentException("task参数必须是'transcribe'或'translate'");
        }

        // 验证output参数
        String[] validOutputs = {"txt", "vtt", "srt", "tsv", "json"};
        boolean validOutput = false;
        for (String valid : validOutputs) {
            if (valid.equals(request.getOutput())) {
                validOutput = true;
                break;
            }
        }
        if (!validOutput) {
            throw new IllegalArgumentException("output参数必须是txt, vtt, srt, tsv, json之一");
        }
    }

    /**
     * 获取文件扩展名
     */
    private String getFileExtension(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        if (originalFilename != null && originalFilename.contains(".")) {
            return originalFilename.substring(originalFilename.lastIndexOf("."));
        }
        return ".tmp";
    }

    /**
     * 构建成功响应
     */
    private AjaxResult buildSuccessResponse(String result, String originalFilename) {
        AjaxResult ajaxResult = AjaxResult.success("转录成功", result);

        // 设置响应头信息（模拟Whisper服务的响应头）
        ajaxResult.put("asr-engine", "openai_whisper");
        ajaxResult.put("content-type", "text/plain; charset=utf-8");

        // 设置文件名（URL编码）
        if (originalFilename != null) {
            String safeFilename = originalFilename.replaceAll("[^a-zA-Z0-9.-]", "_");
            ajaxResult.put("content-disposition", "attachment; filename=\"" + safeFilename + ".txt\"");
        }

        return ajaxResult;
    }

    /**
     * 构建错误响应
     */
    private AjaxResult buildErrorResponse(Exception e) {
        if (e instanceof IllegalArgumentException) {
            // 参数验证错误 - 422状态码
            return AjaxResult.error(422, e.getMessage());
        } else {
            // 其他错误
            return AjaxResult.error("语音转写失败: " + e.getMessage());
        }
    }

    @Override
    public boolean healthCheck() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("accept", "application/json");
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = restTemplate.exchange(
                    whisperServerUrl + "/asr?encode=true&task=transcribe&language=zh&output=txt",
                    HttpMethod.OPTIONS, entity, String.class);

            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false;
        }
    }
}