package com.ruoyi.lab.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson2.JSON;
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
import com.ruoyi.lab.domain.AiRoom;
import com.ruoyi.lab.service.IAiRoomService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 房间Controller
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
@RestController
@RequestMapping("/lab/room")
@Slf4j
public class AiRoomController extends BaseController
{
    @Autowired
    private IAiRoomService aiRoomService;

    /**
     * 查询房间列表
     */
    @PreAuthorize("@ss.hasPermi('lab:room:list')")
    @GetMapping("/list")
    public TableDataInfo list(AiRoom aiRoom)
    {
        startPage();
        List<AiRoom> list = aiRoomService.selectAiRoomList(aiRoom);
        return getDataTable(list);
    }

    /**
     * 查询所有房间列表
     */
    @PreAuthorize("@ss.hasPermi('lab:room:list')")
    @GetMapping("/list-all")
    public AjaxResult listAll(AiRoom aiRoom) {
        List<AiRoom> list = aiRoomService.selectAiRoomList(aiRoom);
        return AjaxResult.success(list);
    }

    // selectUnrelatedRoomList
    /**
     * 查询所有未关联的列表
     */
    @PreAuthorize("@ss.hasPermi('lab:room:list')")
    @GetMapping("/list-all-unrelated")
    public AjaxResult listAllUnrelated(AiRoom aiRoom) {
        List<AiRoom> list = aiRoomService.selectUnrelatedRoomList();
        return AjaxResult.success(list);
    }

    /**
     * 导出房间列表
     */
    @PreAuthorize("@ss.hasPermi('lab:room:export')")
    @Log(title = "房间", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, AiRoom aiRoom)
    {
        List<AiRoom> list = aiRoomService.selectAiRoomList(aiRoom);
        ExcelUtil<AiRoom> util = new ExcelUtil<AiRoom>(AiRoom.class);
        util.exportExcel(response, list, "房间数据");
    }

    /**
     * 获取房间详细信息
     */
    @PreAuthorize("@ss.hasPermi('lab:room:query')")
    @GetMapping(value = "/{roomId}")
    public AjaxResult getInfo(@PathVariable("roomId") Long roomId)
    {
        return success(aiRoomService.selectAiRoomByRoomId(roomId));
    }

    /**
     * 新增房间
     */
    @PreAuthorize("@ss.hasPermi('lab:room:add')")
    @Log(title = "房间", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody AiRoom aiRoom)
    {
        return toAjax(aiRoomService.insertAiRoom(aiRoom));
    }

    /**
     * 绑定团队
     */
    @PreAuthorize("@ss.hasPermi('lab:room:bindTeam')")
    @PostMapping("/bind-team")
    public AjaxResult bindTeam(@RequestBody AiRoom aiRoom) {
        log.debug("绑定团队：{}", JSON.toJSONString(aiRoom));
        return toAjax(aiRoomService.bindTeam(aiRoom));
    }

    /**
     * 修改房间
     */
    @PreAuthorize("@ss.hasPermi('lab:room:edit')")
    @Log(title = "房间", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody AiRoom aiRoom)
    {
        return toAjax(aiRoomService.updateAiRoom(aiRoom));
    }

    /**
     * 删除房间
     */
    @PreAuthorize("@ss.hasPermi('lab:room:remove')")
    @Log(title = "房间", businessType = BusinessType.DELETE)
	@DeleteMapping("/{roomIds}")
    public AjaxResult remove(@PathVariable Long[] roomIds)
    {
        return toAjax(aiRoomService.deleteAiRoomByRoomIds(roomIds));
    }
}
