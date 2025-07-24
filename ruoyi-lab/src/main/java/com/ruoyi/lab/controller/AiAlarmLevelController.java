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
import com.ruoyi.lab.domain.AiAlarmLevel;
import com.ruoyi.lab.service.IAiAlarmLevelService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * levelController
 * 
 * @author ruoyi
 * @date 2025-07-11
 */
@RestController
@RequestMapping("/lab/alarm-level")
public class AiAlarmLevelController extends BaseController
{
    @Autowired
    private IAiAlarmLevelService aiAlarmLevelService;

    /**
     * 查询level列表
     */
    @PreAuthorize("@ss.hasPermi('lab:level:list')")
    @GetMapping("/list")
    public TableDataInfo list(AiAlarmLevel aiAlarmLevel)
    {
        startPage();
        List<AiAlarmLevel> list = aiAlarmLevelService.selectAiAlarmLevelList(aiAlarmLevel);
        return getDataTable(list);
    }

    /**
     * 查询所有level列表
     */
    @PreAuthorize("@ss.hasPermi('lab:level:list')")
    @GetMapping("/list-all")
    public AjaxResult listAll(AiAlarmLevel aiAlarmLevel) {

        List<AiAlarmLevel> list = aiAlarmLevelService.selectAiAlarmLevelList(aiAlarmLevel);
        return AjaxResult.success(list);
    }

    /**
     * 导出level列表
     */
    @PreAuthorize("@ss.hasPermi('lab:level:export')")
    @Log(title = "level", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AiAlarmLevel aiAlarmLevel)
    {
        List<AiAlarmLevel> list = aiAlarmLevelService.selectAiAlarmLevelList(aiAlarmLevel);
        ExcelUtil<AiAlarmLevel> util = new ExcelUtil<AiAlarmLevel>(AiAlarmLevel.class);
        util.exportExcel(response, list, "level数据");
    }

    /**
     * 获取level详细信息
     */
    @PreAuthorize("@ss.hasPermi('lab:level:query')")
    @GetMapping(value = "/{alarmLevelId}")
    public AjaxResult getInfo(@PathVariable("alarmLevelId") Long alarmLevelId)
    {
        return success(aiAlarmLevelService.selectAiAlarmLevelByAlarmLevelId(alarmLevelId));
    }

    /**
     * 新增level
     */
    @PreAuthorize("@ss.hasPermi('lab:level:add')")
    @Log(title = "level", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AiAlarmLevel aiAlarmLevel)
    {
        return toAjax(aiAlarmLevelService.insertAiAlarmLevel(aiAlarmLevel));
    }

    /**
     * 修改level
     */
    @PreAuthorize("@ss.hasPermi('lab:level:edit')")
    @Log(title = "level", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AiAlarmLevel aiAlarmLevel)
    {
        return toAjax(aiAlarmLevelService.updateAiAlarmLevel(aiAlarmLevel));
    }

    /**
     * 删除level
     */
    @PreAuthorize("@ss.hasPermi('lab:level:remove')")
    @Log(title = "level", businessType = BusinessType.DELETE)
	@DeleteMapping("/{alarmLevelIds}")
    public AjaxResult remove(@PathVariable Long[] alarmLevelIds)
    {
        return toAjax(aiAlarmLevelService.deleteAiAlarmLevelByAlarmLevelIds(alarmLevelIds));
    }
}
