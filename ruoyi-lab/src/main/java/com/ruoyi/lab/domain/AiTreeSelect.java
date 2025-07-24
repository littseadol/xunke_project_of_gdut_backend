package com.ruoyi.lab.domain;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.ruoyi.common.utils.StringUtils;

/**
 * Treeselect树结构实体类
 * 
 * @author ruoyi
 */
public class AiTreeSelect implements Serializable
{
    private static final long serialVersionUID = 1L;

    /** 节点ID */
    private Long id;

    /** 节点名称 */
    private String label;

    /** 用于前端 el-tree-select */
    private String value;

    /** 节点禁用 */
    private boolean disabled = false;

    /** 子节点 */
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<AiTreeSelect> children;

    // group表示分组，device表示设备
    private String type;

    public AiTreeSelect()
    {

    }

    public AiTreeSelect(AiGroup aiGroup)
    {
        this.id = aiGroup.getGroupId();
        this.label = aiGroup.getGroupName();
        this.value = aiGroup.getGroupName();
        // 0表示可用，1表示不可用
        this.disabled = StringUtils.equals("1", aiGroup.getStatus());
        this.children = aiGroup.getChildren().stream().map(AiTreeSelect::new).collect(Collectors.toList());
    }

    public AiTreeSelect(AiTreeNode aiTreeNode)
    {
        this.id = aiTreeNode.getId();
        this.label = aiTreeNode.getName();
        this.value = aiTreeNode.getName();
        // 0表示可用，1表示不可用
        this.disabled = StringUtils.equals("1", aiTreeNode.getStatus());
        this.children = aiTreeNode.getChildren().stream().map(AiTreeSelect::new).collect(Collectors.toList());
        this.type = aiTreeNode.getType();
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getLabel()
    {
        return label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setLabel(String label)
    {
        this.label = label;
    }

    public boolean isDisabled()
    {
        return disabled;
    }

    public void setDisabled(boolean disabled)
    {
        this.disabled = disabled;
    }

    public List<AiTreeSelect> getChildren()
    {
        return children;
    }

    public void setChildren(List<AiTreeSelect> children)
    {
        this.children = children;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
