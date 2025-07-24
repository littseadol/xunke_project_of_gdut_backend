package com.ruoyi.lab.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.lab.service.IAiCommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
@RequestMapping("/lab/common")
public class AiCommonController {

    @Autowired
    private IAiCommonService aiCommonService;

    @GetMapping("/list-count")
    public AjaxResult listCount() {
        Map<String, Object> data = aiCommonService.selectCounts();
        return AjaxResult.success(data);
    }
}
