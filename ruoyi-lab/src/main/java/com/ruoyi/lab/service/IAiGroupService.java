package com.ruoyi.lab.service;

import java.util.List;

import com.ruoyi.lab.domain.AiGroup;
import com.ruoyi.lab.domain.AiTreeSelect;

/**
 * 分组管理 服务层
 * 
 * @author ruoyi
 */
public interface IAiGroupService
{
    /**
     * 查询分组管理数据
     * 
     * @param aiGroup 分组信息
     * @return 分组信息集合
     */
    public List<AiGroup> selectGroupList(AiGroup aiGroup);

    /**
     * 查询分组树结构信息
     * 
     * @param aiGroup 分组信息
     * @return 分组树信息集合
     */
    public List<AiTreeSelect> selectGroupTreeList(AiGroup aiGroup);

    public List<AiTreeSelect> selectGroupDeviceTreeList();

    /**
     * 根据分组ID查询信息
     * 
     * @param groupId 分组ID
     * @return 分组信息
     */
    public AiGroup selectGroupById(Long groupId);

    /**
     * 根据ID查询所有子分组（正常状态）
     * 
     * @param groupId 分组ID
     * @return 子分组数
     */
    public int selectNormalChildrenGroupById(Long groupId);

    /**
     * 是否存在分组子节点
     * 
     * @param groupId 分组ID
     * @return 结果
     */
    public boolean hasChildByGroupId(Long groupId);

    /**
     * 查询分组是否存在用户
     * 
     * @param groupId 分组ID
     * @return 结果 true 存在 false 不存在
     */
    public boolean checkGroupExistUser(Long groupId);

    /**
     * 校验分组名称是否唯一
     * 
     * @param aiGroup 分组信息
     * @return 结果
     */
    public boolean checkGroupNameUnique(AiGroup aiGroup);

    /**
     * 校验分组是否有数据权限
     * 
     * @param groupId 分组id
     */
    public void checkGroupDataScope(Long groupId);

    /**
     * 新增保存分组信息
     * 
     * @param aiGroup 分组信息
     * @return 结果
     */
    public int insertGroup(AiGroup aiGroup);

    /**
     * 修改保存分组信息
     * 
     * @param aiGroup 分组信息
     * @return 结果
     */
    public int updateGroup(AiGroup aiGroup);

    /**
     * 删除分组管理信息
     * 
     * @param groupId 分组ID
     * @return 结果
     */
    public int deleteGroupById(Long groupId);
}
