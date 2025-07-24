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
import com.ruoyi.lab.domain.AiBuilding;
import com.ruoyi.lab.service.IAiBuildingService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 楼栋Controller
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
@RestController
@RequestMapping("/lab/building")
public class AiBuildingController extends BaseController
{
    @Autowired
    private IAiBuildingService aiBuildingService;

    /**
     * 查询楼栋列表
     */
    @PreAuthorize("@ss.hasPermi('lab:building:list')")
    @GetMapping("/list")
    public TableDataInfo list(AiBuilding aiBuilding)
    {
        startPage();
        List<AiBuilding> list = aiBuildingService.selectAiBuildingList(aiBuilding);
        return getDataTable(list);
    }

    /**
     * 查询所有楼栋列表
     */
    @PreAuthorize("@ss.hasPermi('lab:building:list')")
    @GetMapping("/list-all")
    public AjaxResult listAll(AiBuilding aiBuilding) {
        List<AiBuilding> list = aiBuildingService.selectAiBuildingList(aiBuilding);
        return AjaxResult.success(list);
    }

    /**
     * 导出楼栋列表
     */
    @PreAuthorize("@ss.hasPermi('lab:building:export')")
    @Log(title = "楼栋", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AiBuilding aiBuilding)
    {
        List<AiBuilding> list = aiBuildingService.selectAiBuildingList(aiBuilding);
        ExcelUtil<AiBuilding> util = new ExcelUtil<AiBuilding>(AiBuilding.class);
        util.exportExcel(response, list, "楼栋数据");
    }

    /**
     * 获取楼栋详细信息
     */
    @PreAuthorize("@ss.hasPermi('lab:building:query')")
    @GetMapping(value = "/{buildingId}")
    public AjaxResult getInfo(@PathVariable("buildingId") Long buildingId)
    {
        return success(aiBuildingService.selectAiBuildingByBuildingId(buildingId));
    }

    /**
     * 新增楼栋
     */
    @PreAuthorize("@ss.hasPermi('lab:building:add')")
    @Log(title = "楼栋", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AiBuilding aiBuilding)
    {
        return toAjax(aiBuildingService.insertAiBuilding(aiBuilding));
    }

    /**
     * 修改楼栋
     */
    @PreAuthorize("@ss.hasPermi('lab:building:edit')")
    @Log(title = "楼栋", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AiBuilding aiBuilding)
    {
        return toAjax(aiBuildingService.updateAiBuilding(aiBuilding));
    }

    /**
     * 删除楼栋
     */
    @PreAuthorize("@ss.hasPermi('lab:building:remove')")
    @Log(title = "楼栋", businessType = BusinessType.DELETE)
	@DeleteMapping("/{buildingIds}")
    public AjaxResult remove(@PathVariable Long[] buildingIds)
    {
        return toAjax(aiBuildingService.deleteAiBuildingByBuildingIds(buildingIds));
    }
}
