package com.ruoyi.lab.mapper;

import java.util.List;
import com.ruoyi.lab.domain.AiTeam;
import org.apache.ibatis.annotations.Mapper;

/**
 * 团队Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
@Mapper
public interface AiTeamMapper 
{
    /**
     * 查询团队
     * 
     * @param teamId 团队主键
     * @return 团队
     */
    public AiTeam selectAiTeamByTeamId(Long teamId);

    /**
     * 查询团队列表
     * 
     * @param aiTeam 团队
     * @return 团队集合
     */
    public List<AiTeam> selectAiTeamList(AiTeam aiTeam);

    /**
     * 新增团队
     * 
     * @param aiTeam 团队
     * @return 结果
     */
    public int insertAiTeam(AiTeam aiTeam);

    /**
     * 修改团队
     * 
     * @param aiTeam 团队
     * @return 结果
     */
    public int updateAiTeam(AiTeam aiTeam);

    /**
     * 删除团队
     * 
     * @param teamId 团队主键
     * @return 结果
     */
    public int deleteAiTeamByTeamId(Long teamId);

    /**
     * 批量删除团队
     * 
     * @param teamIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAiTeamByTeamIds(Long[] teamIds);

    public int selectCount();
}
