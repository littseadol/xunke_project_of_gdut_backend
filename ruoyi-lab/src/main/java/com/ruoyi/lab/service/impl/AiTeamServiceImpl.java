package com.ruoyi.lab.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lab.mapper.AiTeamMapper;
import com.ruoyi.lab.domain.AiTeam;
import com.ruoyi.lab.service.IAiTeamService;

/**
 * 团队Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
@Service
public class AiTeamServiceImpl implements IAiTeamService 
{
    @Autowired
    private AiTeamMapper aiTeamMapper;

    /**
     * 查询团队
     * 
     * @param teamId 团队主键
     * @return 团队
     */
    @Override
    public AiTeam selectAiTeamByTeamId(Long teamId)
    {
        return aiTeamMapper.selectAiTeamByTeamId(teamId);
    }

    /**
     * 查询团队列表
     * 
     * @param aiTeam 团队
     * @return 团队
     */
    @Override
    public List<AiTeam> selectAiTeamList(AiTeam aiTeam)
    {
        return aiTeamMapper.selectAiTeamList(aiTeam);
    }

    /**
     * 新增团队
     * 
     * @param aiTeam 团队
     * @return 结果
     */
    @Override
    public int insertAiTeam(AiTeam aiTeam)
    {
        aiTeam.setCreateTime(DateUtils.getNowDate());
        return aiTeamMapper.insertAiTeam(aiTeam);
    }

    /**
     * 修改团队
     * 
     * @param aiTeam 团队
     * @return 结果
     */
    @Override
    public int updateAiTeam(AiTeam aiTeam)
    {
        return aiTeamMapper.updateAiTeam(aiTeam);
    }

    /**
     * 批量删除团队
     * 
     * @param teamIds 需要删除的团队主键
     * @return 结果
     */
    @Override
    public int deleteAiTeamByTeamIds(Long[] teamIds)
    {
        return aiTeamMapper.deleteAiTeamByTeamIds(teamIds);
    }

    /**
     * 删除团队信息
     * 
     * @param teamId 团队主键
     * @return 结果
     */
    @Override
    public int deleteAiTeamByTeamId(Long teamId)
    {
        return aiTeamMapper.deleteAiTeamByTeamId(teamId);
    }
}
