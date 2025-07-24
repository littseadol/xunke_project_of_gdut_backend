package com.ruoyi.lab.service.impl;

import com.alibaba.fastjson2.JSON;
import com.ruoyi.lab.domain.AiDevice;
import com.ruoyi.lab.domain.AiGroup;
import com.ruoyi.lab.domain.AiTreeNode;
import com.ruoyi.lab.mapper.AiGroupMapper;
import com.ruoyi.lab.service.IAiDeviceService;
import com.ruoyi.lab.service.IAiGroupService;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.lab.domain.AiTreeSelect;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.lab.service.IAiTreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

/**
 * 分组管理 服务实现
 * 
 * @author ruoyi
 */
@Slf4j
@Service
public class AiGroupServiceImpl implements IAiGroupService {
    @Autowired
    private AiGroupMapper aiGroupMapper;

    @Autowired
    private IAiTreeService iAiTreeService;

    @Autowired
    private IAiDeviceService iAiDeviceService;

    /**
     * 查询分组管理数据
     * 
     * @param aiGroup 分组信息
     * @return 分组信息集合
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<AiGroup> selectGroupList(AiGroup aiGroup)
    {
        return aiGroupMapper.selectGroupList(aiGroup);
    }

    /**
     * 查询分组树结构信息
     * 
     * @param aiGroup 分组信息
     * @return 分组树信息集合
     */
    @Override
    public List<AiTreeSelect> selectGroupTreeList(AiGroup aiGroup) {
        List<AiGroup> aiGroupList = aiGroupMapper.selectGroupList(aiGroup);
        log.debug("aiGroupList:{}", JSON.toJSONString(aiGroupList));

        // 把group类型转化成treeNode类型
        List<AiTreeNode> aiTreeNodes = new ArrayList<>();
        for (AiGroup group : aiGroupList) {
            AiTreeNode aiTreeNode = new AiTreeNode();
            aiTreeNode.setId(group.getGroupId());
            aiTreeNode.setName(group.getGroupName());
            aiTreeNode.setParentId(group.getParentId());
            aiTreeNode.setStatus(group.getStatus());
            aiTreeNodes.add(aiTreeNode);
        }
        return iAiTreeService.buildTree(aiTreeNodes);
    }

    @Override
    public List<AiTreeSelect> selectGroupDeviceTreeList() {
        List<AiGroup> aiGroupList = aiGroupMapper.selectGroupList(new AiGroup());
        List<AiDevice> aiDeviceList = iAiDeviceService.selectAllDeviceList();

        // 把group类型转化成treeNode类型
        List<AiTreeNode> aiTreeNodes = new ArrayList<>();

        for (AiGroup group : aiGroupList) {
            AiTreeNode aiTreeNode = new AiTreeNode();
            aiTreeNode.setId(group.getGroupId());
            aiTreeNode.setName(group.getGroupName());
            aiTreeNode.setParentId(group.getParentId());
            aiTreeNode.setStatus("1");
            aiTreeNode.setType("group");
            aiTreeNodes.add(aiTreeNode);
        }

        for (AiDevice device : aiDeviceList) {
            AiTreeNode aiTreeNode = new AiTreeNode();
            aiTreeNode.setId(device.getDeviceId());
            aiTreeNode.setName(device.getDeviceName());
            aiTreeNode.setParentId(device.getGroupId());
            aiTreeNode.setStatus("0");
            aiTreeNode.setType("device");
            aiTreeNodes.add(aiTreeNode);
        }
        return iAiTreeService.buildTree(aiTreeNodes);
    }

    /**
     * 根据分组ID查询信息
     * 
     * @param groupId 分组ID
     * @return 分组信息
     */
    @Override
    public AiGroup selectGroupById(Long groupId)
    {
        return aiGroupMapper.selectGroupById(groupId);
    }

    /**
     * 根据ID查询所有子分组（正常状态）
     * 
     * @param deptId 分组ID
     * @return 子分组数
     */
    @Override
    public int selectNormalChildrenGroupById(Long deptId)
    {
        return aiGroupMapper.selectNormalChildrenGroupById(deptId);
    }

    /**
     * 是否存在子节点
     * 
     * @param deptId 分组ID
     * @return 结果
     */
    @Override
    public boolean hasChildByGroupId(Long deptId)
    {
        int result = aiGroupMapper.hasChildByGroupId(deptId);
        return result > 0;
    }

    /**
     * 查询分组是否存在用户
     * 
     * @param groupId 分组ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkGroupExistUser(Long groupId)
    {
        int result = aiGroupMapper.checkGroupExistDevice(groupId);
        return result > 0;
    }

    /**
     * 校验分组名称是否唯一
     * 
     * @param aiGroup 分组信息
     * @return 结果
     */
    @Override
    public boolean checkGroupNameUnique(AiGroup aiGroup)
    {
        Long groupId = StringUtils.isNull(aiGroup.getGroupId()) ? -1L : aiGroup.getGroupId();
        AiGroup info = aiGroupMapper.checkGroupNameUnique(aiGroup.getGroupName(), aiGroup.getParentId());
        if (StringUtils.isNotNull(info) && info.getGroupId().longValue() != groupId.longValue())
        {
            return UserConstants.NOT_UNIQUE;
        }
        return UserConstants.UNIQUE;
    }

    /**
     * 校验分组是否有数据权限
     * 
     * @param groupId 分组id
     */
    @Override
    public void checkGroupDataScope(Long groupId)
    {
        if (!SysUser.isAdmin(SecurityUtils.getUserId()) && StringUtils.isNotNull(groupId))
        {
            AiGroup aiGroup = new AiGroup();
            aiGroup.setGroupId(groupId);
            List<AiGroup> aiGroupList = SpringUtils.getAopProxy(this).selectGroupList(aiGroup);
            if (StringUtils.isEmpty(aiGroupList))
            {
                throw new ServiceException("没有权限访问分组数据！");
            }
        }
    }

    /**
     * 新增保存分组信息
     * 
     * @param aiGroup 分组信息
     * @return 结果
     */
    @Override
    public int insertGroup(AiGroup aiGroup)
    {
        AiGroup info = aiGroupMapper.selectGroupById(aiGroup.getParentId());
        // 如果父节点不为正常状态,则不允许新增子节点
        if (!UserConstants.DEPT_NORMAL.equals(info.getStatus()))
        {
            throw new ServiceException("分组停用，不允许新增");
        }
        aiGroup.setAncestors(info.getAncestors() + "," + aiGroup.getParentId());
        return aiGroupMapper.insertGroup(aiGroup);
    }

    /**
     * 修改保存分组信息
     * 
     * @param aiGroup 分组信息
     * @return 结果
     */
    @Override
    public int updateGroup(AiGroup aiGroup)
    {
        AiGroup newParentDept = aiGroupMapper.selectGroupById(aiGroup.getParentId());
        AiGroup oldDept = aiGroupMapper.selectGroupById(aiGroup.getGroupId());
        if (StringUtils.isNotNull(newParentDept) && StringUtils.isNotNull(oldDept))
        {
            String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getGroupId();
            String oldAncestors = oldDept.getAncestors();
            aiGroup.setAncestors(newAncestors);
            updateDeptChildren(aiGroup.getGroupId(), newAncestors, oldAncestors);
        }
        int result = aiGroupMapper.updateGroup(aiGroup);
        if (UserConstants.DEPT_NORMAL.equals(aiGroup.getStatus()) && StringUtils.isNotEmpty(aiGroup.getAncestors())
                && !StringUtils.equals("0", aiGroup.getAncestors()))
        {
            // 如果该分组是启用状态，则启用该分组的所有上级分组
            updateParentDeptStatusNormal(aiGroup);
        }
        return result;
    }

    /**
     * 修改该分组的父级分组状态
     * 
     * @param aiGroup 当前分组
     */
    private void updateParentDeptStatusNormal(AiGroup aiGroup)
    {
        String ancestors = aiGroup.getAncestors();
        Long[] deptIds = Convert.toLongArray(ancestors);
        aiGroupMapper.updateGroupStatusNormal(deptIds);
    }

    /**
     * 修改子元素关系
     * 
     * @param groupId 被修改的分组ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateDeptChildren(Long groupId, String newAncestors, String oldAncestors)
    {
        List<AiGroup> children = aiGroupMapper.selectChildrenGroupById(groupId);
        for (AiGroup child : children)
        {
            child.setAncestors(child.getAncestors().replaceFirst(oldAncestors, newAncestors));
        }
        if (!children.isEmpty())
        {
            aiGroupMapper.updateGroupChildren(children);
        }
    }

    /**
     * 删除分组管理信息
     * 
     * @param deptId 分组ID
     * @return 结果
     */
    @Override
    public int deleteGroupById(Long deptId) {
        return aiGroupMapper.deleteGroupById(deptId);
    }
}
