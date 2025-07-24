package com.ruoyi.lab.domain;

import java.util.ArrayList;
import java.util.List;

public class AiTreeNode {
    private Long id;
    private Long parentId;
    private String name;
    private String status;
    private List<AiTreeNode> children = new ArrayList<>();
    private String type;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AiTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<AiTreeNode> children) {
        this.children = children;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
