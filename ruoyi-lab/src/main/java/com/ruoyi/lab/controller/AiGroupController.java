package com.ruoyi.lab.controller;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.lab.domain.AiGroup;
import com.ruoyi.lab.service.IAiGroupService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分组信息
 * 
 * @author ruoyi
 */
@RestController
@RequestMapping("/lab/group")
@Slf4j
public class AiGroupController extends BaseController {
    @Autowired
    private IAiGroupService iVideoGroupService;

    /**
     * 获取分组列表
     */
    @PreAuthorize("@ss.hasPermi('system:dept:list')")
    @GetMapping("/list")
    public AjaxResult list(AiGroup aiGroup)
    {
        log.debug("获取分组列表，{}", JSON.toJSONString(aiGroup));
        List<AiGroup> aiGroupList = iVideoGroupService.selectGroupList(aiGroup);
        return success(aiGroupList);
    }

    /**
     * 查询分组列表（排除节点）
     */
    @PreAuthorize("@ss.hasPermi('system:dept:list')")
    @GetMapping("/list/exclude/{groupId}")
    public AjaxResult excludeChild(@PathVariable(value = "groupId", required = false) Long groupId)
    {
        List<AiGroup> aiGroupList = iVideoGroupService.selectGroupList(new AiGroup());
        aiGroupList.removeIf(d -> d.getGroupId().intValue() == groupId || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), groupId + ""));
        return success(aiGroupList);
    }

    /**
     * 根据分组编号获取详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:dept:query')")
    @GetMapping(value = "/{groupId}")
    public AjaxResult getInfo(@PathVariable Long groupId)
    {
        iVideoGroupService.checkGroupDataScope(groupId);
        return success(iVideoGroupService.selectGroupById(groupId));
    }

    /**
     * 新增分组
     */
    @PreAuthorize("@ss.hasPermi('system:dept:add')")
    @Log(title = "分组管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody AiGroup aiGroup)
    {
        if (!iVideoGroupService.checkGroupNameUnique(aiGroup))
        {
            return error("新增分组'" + aiGroup.getGroupName() + "'失败，分组名称已存在");
        }
        aiGroup.setCreateBy(getUsername());
        return toAjax(iVideoGroupService.insertGroup(aiGroup));
    }

    /**
     * 修改分组
     */
    @PreAuthorize("@ss.hasPermi('system:dept:edit')")
    @Log(title = "分组管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody AiGroup aiGroup)
    {
        log.debug("修改分组，{}", JSON.toJSONString(aiGroup));
        Long groupId = aiGroup.getGroupId();
        iVideoGroupService.checkGroupDataScope(groupId);
        if (!iVideoGroupService.checkGroupNameUnique(aiGroup))
        {
            return error("修改分组'" + aiGroup.getGroupName() + "'失败，分组名称已存在");
        }
        else if (aiGroup.getParentId().equals(groupId))
        {
            return error("修改分组'" + aiGroup.getGroupName() + "'失败，上级分组不能是自己");
        }
        else if (StringUtils.equals(UserConstants.DEPT_DISABLE, aiGroup.getStatus()) && iVideoGroupService.selectNormalChildrenGroupById(groupId) > 0)
        {
            return error("该分组包含未停用的子分组！");
        }
        aiGroup.setUpdateBy(getUsername());
        return toAjax(iVideoGroupService.updateGroup(aiGroup));
    }

    /**
     * 删除分组
     */
    @PreAuthorize("@ss.hasPermi('system:dept:remove')")
    @Log(title = "分组管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{groupId}")
    public AjaxResult remove(@PathVariable Long groupId)
    {
        if (iVideoGroupService.hasChildByGroupId(groupId))
        {
            return warn("存在下级分组,不允许删除");
        }
        if (iVideoGroupService.checkGroupExistUser(groupId))
        {
            return warn("分组存在设备,不允许删除");
        }
        iVideoGroupService.checkGroupDataScope(groupId);
        return toAjax(iVideoGroupService.deleteGroupById(groupId));
    }

    /**
     * 获取分组树列表
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/get-group-tree")
    public AjaxResult selectGroupTreeList(AiGroup aiGroup)
    {
        return success(iVideoGroupService.selectGroupTreeList(aiGroup));
    }

    /**
     * 获取分组树列表
     */
    @PreAuthorize("@ss.hasPermi('system:user:list')")
    @GetMapping("/get-group-device-tree")
    public AjaxResult selectGroupDeviceTreeList()
    {
        return success(iVideoGroupService.selectGroupDeviceTreeList());
    }
}