package com.ruoyi.lab.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.lab.service.impl.AiKeywordExtractorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lab/knowledge")
public class AiTextAnalysisController extends BaseController {

    @Autowired
    private AiKeywordExtractorServiceImpl keywordExtractorService;

    @PostMapping("/extractKeywords")
    public AjaxResult extractKeywords(@RequestBody Map<String, String> request) {
        String text = request.get("text");
        try {
            List<String> keywords = keywordExtractorService.extractKeywords(text);
            return AjaxResult.success("提取成功", keywords);
        } catch (Exception e) {
            return AjaxResult.error("提取失败: " + e.getMessage());
        }
    }
}