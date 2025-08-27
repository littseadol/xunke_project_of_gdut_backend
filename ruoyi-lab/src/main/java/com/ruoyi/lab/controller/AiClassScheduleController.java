package com.ruoyi.lab.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
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
import com.ruoyi.lab.domain.AiClassSchedule;
import com.ruoyi.lab.service.IAiClassScheduleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 排课信息Controller
 * 
 * @author Ldolphin
 * @date 2025-07-24
 */
@Slf4j
@RestController
@RequestMapping("/lab/classSchedule")
public class AiClassScheduleController extends BaseController
{
    @Autowired
    private IAiClassScheduleService aiClassScheduleService;

    /**
     * 查询排课信息列表
     */
    @PreAuthorize("@ss.hasPermi('lab:classSchedule:list')")
    @GetMapping("/list")
    public TableDataInfo list(AiClassSchedule aiClassSchedule)
    {
        startPage();
        List<AiClassSchedule> list = aiClassScheduleService.selectAiClassScheduleList(aiClassSchedule);
        return getDataTable(list);
    }

    /**
     * 导出排课信息列表
     */
    @PreAuthorize("@ss.hasPermi('lab:classSchedule:export')")
    @Log(title = "排课信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AiClassSchedule aiClassSchedule)
    {
        List<AiClassSchedule> list = aiClassScheduleService.selectAiClassScheduleList(aiClassSchedule);
        ExcelUtil<AiClassSchedule> util = new ExcelUtil<AiClassSchedule>(AiClassSchedule.class);
        util.exportExcel(response, list, "排课信息数据");
    }

    /**
     * 获取排课信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('lab:classSchedule:query')")
    @GetMapping(value = "/{scheduleId}")
    public AjaxResult getInfo(@PathVariable("scheduleId") Long scheduleId)
    {
        return success(aiClassScheduleService.selectAiClassScheduleByScheduleId(scheduleId));
    }

    /**
     * 新增排课信息
     */
    @PreAuthorize("@ss.hasPermi('lab:classSchedule:add')")
    @Log(title = "排课信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AiClassSchedule aiClassSchedule)
    {
        return toAjax(aiClassScheduleService.insertAiClassSchedule(aiClassSchedule));
    }

    /**
     * 修改排课信息
     */
    @PreAuthorize("@ss.hasPermi('lab:classSchedule:edit')")
    @Log(title = "排课信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AiClassSchedule aiClassSchedule)
    {
        return toAjax(aiClassScheduleService.updateAiClassSchedule(aiClassSchedule));
    }

    /**
     * 删除排课信息
     */
    @PreAuthorize("@ss.hasPermi('lab:classSchedule:remove')")
    @Log(title = "排课信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scheduleIds}")
    public AjaxResult remove(@PathVariable Long[] scheduleIds)
    {
        return toAjax(aiClassScheduleService.deleteAiClassScheduleByScheduleIds(scheduleIds));
    }
}
