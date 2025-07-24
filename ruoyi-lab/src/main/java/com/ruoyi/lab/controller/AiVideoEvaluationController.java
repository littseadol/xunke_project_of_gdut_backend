package com.ruoyi.lab.controller;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.lab.service.IAiMinioService;
import io.minio.MinioClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.lab.domain.AiVideoEvaluation;
import com.ruoyi.lab.service.IAiVideoEvaluationService;
import com.ruoyi.lab.service.IAiHttpRequestService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 视频评价Controller
 */
@RestController
@RequestMapping("/lab/evaluation")
public class AiVideoEvaluationController extends BaseController {

    @Autowired
    private IAiVideoEvaluationService aiVideoEvaluationService;

    @Autowired
    private IAiHttpRequestService aiHttpRequestService;

    @Autowired
    private IAiMinioService iAiMinioService;

    @Value("${minio.bucket-name}")
    private String bucketName;

    /**
     * 查询视频评价列表
     */
    @PreAuthorize("@ss.hasPermi('lab:evaluation:list')")
    @GetMapping("/list")
    public TableDataInfo list(AiVideoEvaluation aiVideoEvaluation) {
        startPage();
        List<AiVideoEvaluation> list = aiVideoEvaluationService.selectAiVideoEvaluationList(aiVideoEvaluation);
        return getDataTable(list);
    }

    /**
     * 导出视频评价列表
     */
    @PreAuthorize("@ss.hasPermi('lab:evaluation:export')")
    @Log(title = "视频评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AiVideoEvaluation aiVideoEvaluation) {
        List<AiVideoEvaluation> list = aiVideoEvaluationService.selectAiVideoEvaluationList(aiVideoEvaluation);
        ExcelUtil<AiVideoEvaluation> util = new ExcelUtil<>(AiVideoEvaluation.class);
        util.exportExcel(response, list, "视频评价数据");
    }

    /**
     * 获取视频评价详细信息
     */
    @PreAuthorize("@ss.hasPermi('lab:evaluation:query')")
    @GetMapping("/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(aiVideoEvaluationService.selectAiVideoEvaluationById(id));
    }

    /**
     * 新增视频评价（带截图上传）
     */
    @PreAuthorize("@ss.hasPermi('lab:evaluation:add')")
    @Log(title = "视频评价", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(
            @RequestParam String deviceId,
            @RequestParam Integer score,
            @RequestParam String content,
            @RequestParam(required = false) String suggestion,
            @RequestParam Date snapshotTime,
            @RequestParam Date videoTime,
            @RequestParam String snapshotUrl) {

        try {

            AiVideoEvaluation evaluation = new AiVideoEvaluation();
            evaluation.setDeviceId(deviceId);
            evaluation.setScore(score);
            evaluation.setContent(content);
            evaluation.setSuggestion(suggestion);
            evaluation.setSnapshotPath(snapshotUrl);
            evaluation.setSnapshotTime(snapshotTime);
            evaluation.setVideoTime(videoTime);

            return toAjax(aiVideoEvaluationService.insertAiVideoEvaluation(evaluation));
        } catch (Exception e) {
            return error("评价提交失败: " + e.getMessage());
        }
    }


        /**
         * 修改视频评价
         */
        @PreAuthorize("@ss.hasPermi('lab:evaluation:edit')")
        @Log(title = "视频评价", businessType = BusinessType.UPDATE)
        @PutMapping
        public AjaxResult edit(@RequestBody AiVideoEvaluation aiVideoEvaluation) {
            return toAjax(aiVideoEvaluationService.updateAiVideoEvaluation(aiVideoEvaluation));
        }

        /**
         * 删除视频评价
         */
        @PreAuthorize("@ss.hasPermi('lab:evaluation:remove')")
        @Log(title = "视频评价", businessType = BusinessType.DELETE)
        @DeleteMapping("/{ids}")
        public AjaxResult remove(@PathVariable Long[] ids) {
            return toAjax(aiVideoEvaluationService.deleteAiVideoEvaluationByIds(ids));
        }

    }
