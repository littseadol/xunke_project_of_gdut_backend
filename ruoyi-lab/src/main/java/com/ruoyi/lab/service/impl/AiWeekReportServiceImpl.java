package com.ruoyi.lab.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lab.mapper.AiWeekReportMapper;
import com.ruoyi.lab.domain.AiWeekReport;
import com.ruoyi.lab.service.IAiWeekReportService;

/**
 * 周报Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-17
 */
@Service
public class AiWeekReportServiceImpl implements IAiWeekReportService 
{
    @Autowired
    private AiWeekReportMapper aiWeekReportMapper;

    /**
     * 查询周报
     * 
     * @param weekReportId 周报主键
     * @return 周报
     */
    @Override
    public AiWeekReport selectAiWeekReportByWeekReportId(Long weekReportId)
    {
        return aiWeekReportMapper.selectAiWeekReportByWeekReportId(weekReportId);
    }

    /**
     * 查询周报列表
     * 
     * @param aiWeekReport 周报
     * @return 周报
     */
    @Override
    public List<AiWeekReport> selectAiWeekReportList(AiWeekReport aiWeekReport)
    {
        return aiWeekReportMapper.selectAiWeekReportList(aiWeekReport);
    }

    /**
     * 新增周报
     * 
     * @param aiWeekReport 周报
     * @return 结果
     */
    @Override
    public int insertAiWeekReport(AiWeekReport aiWeekReport)
    {
        aiWeekReport.setCreateTime(DateUtils.getNowDate());
        return aiWeekReportMapper.insertAiWeekReport(aiWeekReport);
    }

    /**
     * 修改周报
     * 
     * @param aiWeekReport 周报
     * @return 结果
     */
    @Override
    public int updateAiWeekReport(AiWeekReport aiWeekReport)
    {
        return aiWeekReportMapper.updateAiWeekReport(aiWeekReport);
    }

    /**
     * 批量删除周报
     * 
     * @param weekReportIds 需要删除的周报主键
     * @return 结果
     */
    @Override
    public int deleteAiWeekReportByWeekReportIds(Long[] weekReportIds)
    {
        return aiWeekReportMapper.deleteAiWeekReportByWeekReportIds(weekReportIds);
    }

    /**
     * 删除周报信息
     * 
     * @param weekReportId 周报主键
     * @return 结果
     */
    @Override
    public int deleteAiWeekReportByWeekReportId(Long weekReportId)
    {
        return aiWeekReportMapper.deleteAiWeekReportByWeekReportId(weekReportId);
    }
}
