package com.ruoyi.lab.mapper;

import com.ruoyi.lab.domain.AiGroup;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 分组管理 数据层
 * 
 * @author ruoyi
 */
@Mapper
public interface AiGroupMapper
{
    /**
     * 查询分组管理数据
     * 
     * @param aiGroup 分组信息
     * @return 分组信息集合
     */
    public List<AiGroup> selectGroupList(AiGroup aiGroup);

    /**
     * 根据角色ID查询分组树信息
     * 
     * @param roleId 角色ID
     * @param groupCheckStrictly 分组树选择项是否关联显示
     * @return 选中分组列表
     */
    public List<Long> selectGroupListByRoleId(@Param("roleId") Long roleId, @Param("groupCheckStrictly") boolean groupCheckStrictly);

    /**
     * 根据分组ID查询信息
     * 
     * @param groupId 分组ID
     * @return 分组信息
     */
    public AiGroup selectGroupById(Long groupId);

    /**
     * 根据ID查询所有子分组
     * 
     * @param groupId 分组ID
     * @return 分组列表
     */
    public List<AiGroup> selectChildrenGroupById(Long groupId);

    /**
     * 根据ID查询所有子分组（正常状态）
     * 
     * @param groupId 分组ID
     * @return 子分组数
     */
    public int selectNormalChildrenGroupById(Long groupId);

    /**
     * 是否存在子节点
     * 
     * @param groupId 分组ID
     * @return 结果
     */
    public int hasChildByGroupId(Long groupId);

    /**
     * 查询分组是否存在设备
     * 
     * @param groupId 分组ID
     * @return 结果
     */
    public int checkGroupExistDevice(Long groupId);

    /**
     * 校验分组名称是否唯一
     * 
     * @param groupName 分组名称
     * @param parentId 父分组ID
     * @return 结果
     */
    public AiGroup checkGroupNameUnique(@Param("groupName") String groupName, @Param("parentId") Long parentId);

    /**
     * 新增分组信息
     * 
     * @param aiGroup 分组信息
     * @return 结果
     */
    public int insertGroup(AiGroup aiGroup);

    /**
     * 修改分组信息
     * 
     * @param aiGroup 分组信息
     * @return 结果
     */
    public int updateGroup(AiGroup aiGroup);

    /**
     * 修改所在分组正常状态
     * 
     * @param groupIds 分组ID组
     */
    public void updateGroupStatusNormal(Long[] groupIds);

    /**
     * 修改子元素关系
     * 
     * @param aiGroups 子元素
     * @return 结果
     */
    public int updateGroupChildren(@Param("aiGroups") List<AiGroup> aiGroups);

    /**
     * 删除分组管理信息
     * 
     * @param groupId 分组ID
     * @return 结果
     */
    public int deleteGroupById(Long groupId);
}
