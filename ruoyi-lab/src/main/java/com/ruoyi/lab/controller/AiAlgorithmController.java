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
import com.ruoyi.lab.domain.AiAlgorithm;
import com.ruoyi.lab.service.IAiAlgorithmService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 算法Controller
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
@RestController
@RequestMapping("/lab/algorithm")
public class AiAlgorithmController extends BaseController
{
    @Autowired
    private IAiAlgorithmService aiAlgorithmService;

    /**
     * 查询算法列表
     */
    @PreAuthorize("@ss.hasPermi('lab:algorithm:list')")
    @GetMapping("/list")
    public TableDataInfo list(AiAlgorithm aiAlgorithm)
    {
        startPage();
        List<AiAlgorithm> list = aiAlgorithmService.selectAiAlgorithmList(aiAlgorithm);
        return getDataTable(list);
    }

    /**
     * 查询所有的算法列表
     */
    @PreAuthorize("@ss.hasPermi('lab:algorithm:list')")
    @GetMapping("/list-all")
    public AjaxResult listAll(AiAlgorithm aiAlgorithm) {
        List<AiAlgorithm> list = aiAlgorithmService.selectAiAlgorithmList(aiAlgorithm);
        return AjaxResult.success(list);
    }

    /**
     * 导出算法列表
     */
    @PreAuthorize("@ss.hasPermi('lab:algorithm:export')")
    @Log(title = "算法", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AiAlgorithm aiAlgorithm)
    {
        List<AiAlgorithm> list = aiAlgorithmService.selectAiAlgorithmList(aiAlgorithm);
        ExcelUtil<AiAlgorithm> util = new ExcelUtil<AiAlgorithm>(AiAlgorithm.class);
        util.exportExcel(response, list, "算法数据");
    }

    /**
     * 获取算法详细信息
     */
    @PreAuthorize("@ss.hasPermi('lab:algorithm:query')")
    @GetMapping(value = "/{algorithmId}")
    public AjaxResult getInfo(@PathVariable("algorithmId") Long algorithmId)
    {
        return success(aiAlgorithmService.selectAiAlgorithmByAlgorithmId(algorithmId));
    }

    /**
     * 新增算法
     */
    @PreAuthorize("@ss.hasPermi('lab:algorithm:add')")
    @Log(title = "算法", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AiAlgorithm aiAlgorithm)
    {
        return toAjax(aiAlgorithmService.insertAiAlgorithm(aiAlgorithm));
    }

    /**
     * 修改算法
     */
    @PreAuthorize("@ss.hasPermi('lab:algorithm:edit')")
    @Log(title = "算法", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AiAlgorithm aiAlgorithm)
    {
        return toAjax(aiAlgorithmService.updateAiAlgorithm(aiAlgorithm));
    }

    /**
     * 删除算法
     */
    @PreAuthorize("@ss.hasPermi('lab:algorithm:remove')")
    @Log(title = "算法", businessType = BusinessType.DELETE)
	@DeleteMapping("/{algorithmIds}")
    public AjaxResult remove(@PathVariable Long[] algorithmIds)
    {
        return toAjax(aiAlgorithmService.deleteAiAlgorithmByAlgorithmIds(algorithmIds));
    }
}
