package com.ruoyi.lab.service;

import com.ruoyi.lab.domain.AiTreeNode;
import com.ruoyi.lab.domain.AiTreeSelect;

import java.util.List;

public interface IAiTreeService {
    List<AiTreeSelect> buildTree(List<AiTreeNode> aiTreeNodes);
}
