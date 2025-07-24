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
import com.ruoyi.lab.domain.AiTeam;
import com.ruoyi.lab.service.IAiTeamService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 团队Controller
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
@RestController
@RequestMapping("/lab/team")
public class AiTeamController extends BaseController
{
    @Autowired
    private IAiTeamService aiTeamService;

    /**
     * 查询团队列表
     */
    @PreAuthorize("@ss.hasPermi('lab:team:list')")
    @GetMapping("/list")
    public TableDataInfo list(AiTeam aiTeam)
    {
        startPage();
        List<AiTeam> list = aiTeamService.selectAiTeamList(aiTeam);
        return getDataTable(list);
    }

    /**
     * 查询所有的团队列表
     */
    @PreAuthorize("@ss.hasPermi('lab:team:list')")
    @GetMapping("/list-all")
    public AjaxResult listAll(AiTeam aiTeam) {
        List<AiTeam> list = aiTeamService.selectAiTeamList(aiTeam);
        return AjaxResult.success(list);
    }

    /**
     * 导出团队列表
     */
    @PreAuthorize("@ss.hasPermi('lab:team:export')")
    @Log(title = "团队", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AiTeam aiTeam)
    {
        List<AiTeam> list = aiTeamService.selectAiTeamList(aiTeam);
        ExcelUtil<AiTeam> util = new ExcelUtil<AiTeam>(AiTeam.class);
        util.exportExcel(response, list, "团队数据");
    }

    /**
     * 获取团队详细信息
     */
    @PreAuthorize("@ss.hasPermi('lab:team:query')")
    @GetMapping(value = "/{teamId}")
    public AjaxResult getInfo(@PathVariable("teamId") Long teamId)
    {
        return success(aiTeamService.selectAiTeamByTeamId(teamId));
    }

    /**
     * 新增团队
     */
    @PreAuthorize("@ss.hasPermi('lab:team:add')")
    @Log(title = "团队", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AiTeam aiTeam)
    {
        return toAjax(aiTeamService.insertAiTeam(aiTeam));
    }

    /**
     * 修改团队
     */
    @PreAuthorize("@ss.hasPermi('lab:team:edit')")
    @Log(title = "团队", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AiTeam aiTeam)
    {
        return toAjax(aiTeamService.updateAiTeam(aiTeam));
    }

    /**
     * 删除团队
     */
    @PreAuthorize("@ss.hasPermi('lab:team:remove')")
    @Log(title = "团队", businessType = BusinessType.DELETE)
	@DeleteMapping("/{teamIds}")
    public AjaxResult remove(@PathVariable Long[] teamIds)
    {
        return toAjax(aiTeamService.deleteAiTeamByTeamIds(teamIds));
    }
}
