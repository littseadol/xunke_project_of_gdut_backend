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
import com.ruoyi.lab.domain.AiAlarmEvent;
import com.ruoyi.lab.service.IAiAlarmEventService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * eventController
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
@RestController
@RequestMapping("/lab/event")
public class AiAlarmEventController extends BaseController
{
    @Autowired
    private IAiAlarmEventService aiAlarmEventService;

    /**
     * 查询event列表
     */
    @PreAuthorize("@ss.hasPermi('lab:event:list')")
    @GetMapping("/list")
    public TableDataInfo list(AiAlarmEvent aiAlarmEvent)
    {
        startPage();
        List<AiAlarmEvent> list = aiAlarmEventService.selectAiAlarmEventList(aiAlarmEvent);
        return getDataTable(list);
    }

    /**
     * 导出event列表
     */
    @PreAuthorize("@ss.hasPermi('lab:event:export')")
    @Log(title = "event", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AiAlarmEvent aiAlarmEvent)
    {
        List<AiAlarmEvent> list = aiAlarmEventService.selectAiAlarmEventList(aiAlarmEvent);
        ExcelUtil<AiAlarmEvent> util = new ExcelUtil<AiAlarmEvent>(AiAlarmEvent.class);
        util.exportExcel(response, list, "event数据");
    }

    /**
     * 获取event详细信息
     */
    @PreAuthorize("@ss.hasPermi('lab:event:query')")
    @GetMapping(value = "/{eventId}")
    public AjaxResult getInfo(@PathVariable("eventId") Long eventId)
    {
        return success(aiAlarmEventService.selectAiAlarmEventByEventId(eventId));
    }

    /**
     * 新增event
     */
    @PreAuthorize("@ss.hasPermi('lab:event:add')")
    @Log(title = "event", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AiAlarmEvent aiAlarmEvent)
    {
        return toAjax(aiAlarmEventService.insertAiAlarmEvent(aiAlarmEvent));
    }

    /**
     * 修改event
     */
    @PreAuthorize("@ss.hasPermi('lab:event:edit')")
    @Log(title = "event", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AiAlarmEvent aiAlarmEvent)
    {
        return toAjax(aiAlarmEventService.updateAiAlarmEvent(aiAlarmEvent));
    }

    /**
     * 删除event
     */
    @PreAuthorize("@ss.hasPermi('lab:event:remove')")
    @Log(title = "event", businessType = BusinessType.DELETE)
	@DeleteMapping("/{eventIds}")
    public AjaxResult remove(@PathVariable Long[] eventIds)
    {
        return toAjax(aiAlarmEventService.deleteAiAlarmEventByEventIds(eventIds));
    }
}
