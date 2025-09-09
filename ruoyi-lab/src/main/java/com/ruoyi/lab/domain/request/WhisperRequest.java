package com.ruoyi.lab.domain.request;

import lombok.Data;

@Data
public class WhisperRequest {
    private String audioFile; // 音频文件路径或Base64编码
    private String task = "transcribe"; // transcribe或translate
    private String language = "zh"; // 语言代码
    private String output = "txt"; // 输出格式
    private String initialPrompt; // 初始提示
    private Boolean encode = true; // 是否编码
}