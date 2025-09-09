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
import com.ruoyi.lab.domain.AiVideoRecord;
import com.ruoyi.lab.service.IAiVideoRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 录像管理Controller
 * 
 * @author littseadol
 * @date 2025-08-29
 */
@RestController
@RequestMapping("/record/record")
public class AiVideoRecordController extends BaseController
{
    @Autowired
    private IAiVideoRecordService aiVideoRecordService;

    /**
     * 查询录像管理列表
     */
    @PreAuthorize("@ss.hasPermi('record:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(AiVideoRecord aiVideoRecord)
    {
        startPage();
        List<AiVideoRecord> list = aiVideoRecordService.selectAiVideoRecordList(aiVideoRecord);
        return getDataTable(list);
    }

    /**
     * 导出录像管理列表
     */
    @PreAuthorize("@ss.hasPermi('record:record:export')")
    @Log(title = "录像管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AiVideoRecord aiVideoRecord)
    {
        List<AiVideoRecord> list = aiVideoRecordService.selectAiVideoRecordList(aiVideoRecord);
        ExcelUtil<AiVideoRecord> util = new ExcelUtil<AiVideoRecord>(AiVideoRecord.class);
        util.exportExcel(response, list, "录像管理数据");
    }

    /**
     * 获取录像管理详细信息
     */
    @PreAuthorize("@ss.hasPermi('record:record:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(aiVideoRecordService.selectAiVideoRecordByRecordId(recordId));
    }

    /**
     * 新增录像管理
     */
    @PreAuthorize("@ss.hasPermi('record:record:add')")
    @Log(title = "录像管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AiVideoRecord aiVideoRecord)
    {
        return toAjax(aiVideoRecordService.insertAiVideoRecord(aiVideoRecord));
    }

    /**
     * 修改录像管理
     */
    @PreAuthorize("@ss.hasPermi('record:record:edit')")
    @Log(title = "录像管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AiVideoRecord aiVideoRecord)
    {
        return toAjax(aiVideoRecordService.updateAiVideoRecord(aiVideoRecord));
    }

    /**
     * 删除录像管理
     */
    @PreAuthorize("@ss.hasPermi('record:record:remove')")
    @Log(title = "录像管理", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(aiVideoRecordService.deleteAiVideoRecordByRecordIds(recordIds));
    }
}
