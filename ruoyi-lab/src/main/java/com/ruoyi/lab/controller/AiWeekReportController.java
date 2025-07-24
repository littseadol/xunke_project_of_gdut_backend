package com.ruoyi.lab.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.lab.domain.AiWeekReport;
import com.ruoyi.lab.service.IAiWeekReportService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 周报Controller
 * 
 * @author ruoyi
 * @date 2025-07-17
 */
@RestController
@RequestMapping("/lab/week-report")
public class AiWeekReportController extends BaseController
{
    @Autowired
    private IAiWeekReportService aiWeekReportService;

    /**
     * 查询周报列表
     */
    @PreAuthorize("@ss.hasPermi('lab:report:list')")
    @GetMapping("/list")
    public TableDataInfo list(AiWeekReport aiWeekReport)
    {
        startPage();
        List<AiWeekReport> list = aiWeekReportService.selectAiWeekReportList(aiWeekReport);
        return getDataTable(list);
    }

    /**
     * 导出周报列表
     */
    @PreAuthorize("@ss.hasPermi('lab:report:export')")
    @Log(title = "周报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AiWeekReport aiWeekReport)
    {
        List<AiWeekReport> list = aiWeekReportService.selectAiWeekReportList(aiWeekReport);
        ExcelUtil<AiWeekReport> util = new ExcelUtil<AiWeekReport>(AiWeekReport.class);
        util.exportExcel(response, list, "周报数据");
    }

    /**
     * 获取周报详细信息
     */
    @PreAuthorize("@ss.hasPermi('lab:report:query')")
    @GetMapping(value = "/{weekReportId}")
    public AjaxResult getInfo(@PathVariable("weekReportId") Long weekReportId)
    {
        return success(aiWeekReportService.selectAiWeekReportByWeekReportId(weekReportId));
    }

    /**
     * 新增周报
     */
    @PreAuthorize("@ss.hasPermi('lab:report:add')")
    @Log(title = "周报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AiWeekReport aiWeekReport)
    {
        return toAjax(aiWeekReportService.insertAiWeekReport(aiWeekReport));
    }

    /**
     * 修改周报
     */
    @PreAuthorize("@ss.hasPermi('lab:report:edit')")
    @Log(title = "周报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AiWeekReport aiWeekReport)
    {
        return toAjax(aiWeekReportService.updateAiWeekReport(aiWeekReport));
    }

    /**
     * 删除周报
     */
    @PreAuthorize("@ss.hasPermi('lab:report:remove')")
    @Log(title = "周报", businessType = BusinessType.DELETE)
	@DeleteMapping("/{weekReportIds}")
    public AjaxResult remove(@PathVariable Long[] weekReportIds)
    {
        return toAjax(aiWeekReportService.deleteAiWeekReportByWeekReportIds(weekReportIds));
    }
}
