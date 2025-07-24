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
import com.ruoyi.lab.domain.AiMonthReport;
import com.ruoyi.lab.service.IAiMonthReportService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 月报Controller
 * 
 * @author ruoyi
 * @date 2025-07-17
 */
@RestController
@RequestMapping("/lab/month-report")
public class AiMonthReportController extends BaseController
{
    @Autowired
    private IAiMonthReportService aiMonthReportService;

    /**
     * 查询月报列表
     */
    @PreAuthorize("@ss.hasPermi('lab:monthReport:list')")
    @GetMapping("/list")
    public TableDataInfo list(AiMonthReport aiMonthReport)
    {
        startPage();
        List<AiMonthReport> list = aiMonthReportService.selectAiMonthReportList(aiMonthReport);
        return getDataTable(list);
    }

    /**
     * 导出月报列表
     */
    @PreAuthorize("@ss.hasPermi('lab:monthReport:export')")
    @Log(title = "月报", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AiMonthReport aiMonthReport)
    {
        List<AiMonthReport> list = aiMonthReportService.selectAiMonthReportList(aiMonthReport);
        ExcelUtil<AiMonthReport> util = new ExcelUtil<AiMonthReport>(AiMonthReport.class);
        util.exportExcel(response, list, "月报数据");
    }

    /**
     * 获取月报详细信息
     */
    @PreAuthorize("@ss.hasPermi('lab:monthReport:query')")
    @GetMapping(value = "/{monthReportId}")
    public AjaxResult getInfo(@PathVariable("monthReportId") Long monthReportId)
    {
        return success(aiMonthReportService.selectAiMonthReportByMonthReportId(monthReportId));
    }

    /**
     * 新增月报
     */
    @PreAuthorize("@ss.hasPermi('lab:monthReport:add')")
    @Log(title = "月报", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AiMonthReport aiMonthReport)
    {
        return toAjax(aiMonthReportService.insertAiMonthReport(aiMonthReport));
    }

    /**
     * 修改月报
     */
    @PreAuthorize("@ss.hasPermi('lab:monthReport:edit')")
    @Log(title = "月报", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AiMonthReport aiMonthReport)
    {
        return toAjax(aiMonthReportService.updateAiMonthReport(aiMonthReport));
    }

    /**
     * 删除月报
     */
    @PreAuthorize("@ss.hasPermi('lab:monthReport:remove')")
    @Log(title = "月报", businessType = BusinessType.DELETE)
	@DeleteMapping("/{monthReportIds}")
    public AjaxResult remove(@PathVariable Long[] monthReportIds)
    {
        return toAjax(aiMonthReportService.deleteAiMonthReportByMonthReportIds(monthReportIds));
    }
}
