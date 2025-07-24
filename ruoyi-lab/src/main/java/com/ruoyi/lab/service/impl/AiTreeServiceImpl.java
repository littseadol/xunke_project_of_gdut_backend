package com.ruoyi.lab.service.impl;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.lab.domain.AiTreeNode;
import com.ruoyi.lab.domain.AiTreeSelect;
import com.ruoyi.lab.service.IAiTreeService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AiTreeServiceImpl implements IAiTreeService {
    @Override
    public List<AiTreeSelect> buildTree(List<AiTreeNode> aiTreeNodes) {
        List<AiTreeNode> returnList = new ArrayList<>();
        List<Long> tempList = aiTreeNodes.stream().map(AiTreeNode::getId).collect(Collectors.toList());
        for (AiTreeNode aiTreeNode : aiTreeNodes)
        {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(aiTreeNode.getParentId()))
            {
                recursionFn(aiTreeNodes, aiTreeNode);
                returnList.add(aiTreeNode);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = aiTreeNodes;
        }
        return returnList.stream().map(AiTreeSelect::new).collect(Collectors.toList());
    }

    private void recursionFn(List<AiTreeNode> list, AiTreeNode t)
    {
        // 得到子节点列表
        List<AiTreeNode> childList = getChildList(list, t);
        t.setChildren(childList);
        for (AiTreeNode tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }

    private boolean hasChild(List<AiTreeNode> list, AiTreeNode t)
    {
        return !getChildList(list, t).isEmpty();
    }

    private List<AiTreeNode> getChildList(List<AiTreeNode> list, AiTreeNode t)
    {
        List<AiTreeNode> tlist = new ArrayList<>();
        for (AiTreeNode n : list) {
            if (StringUtils.isNotNull(n.getParentId()) && n.getParentId().longValue() == t.getId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }
}
