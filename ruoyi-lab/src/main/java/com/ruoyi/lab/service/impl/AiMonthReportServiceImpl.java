package com.ruoyi.lab.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lab.mapper.AiMonthReportMapper;
import com.ruoyi.lab.domain.AiMonthReport;
import com.ruoyi.lab.service.IAiMonthReportService;

/**
 * 月报Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-17
 */
@Service
public class AiMonthReportServiceImpl implements IAiMonthReportService 
{
    @Autowired
    private AiMonthReportMapper aiMonthReportMapper;

    /**
     * 查询月报
     * 
     * @param monthReportId 月报主键
     * @return 月报
     */
    @Override
    public AiMonthReport selectAiMonthReportByMonthReportId(Long monthReportId)
    {
        return aiMonthReportMapper.selectAiMonthReportByMonthReportId(monthReportId);
    }

    /**
     * 查询月报列表
     * 
     * @param aiMonthReport 月报
     * @return 月报
     */
    @Override
    public List<AiMonthReport> selectAiMonthReportList(AiMonthReport aiMonthReport)
    {
        return aiMonthReportMapper.selectAiMonthReportList(aiMonthReport);
    }

    /**
     * 新增月报
     * 
     * @param aiMonthReport 月报
     * @return 结果
     */
    @Override
    public int insertAiMonthReport(AiMonthReport aiMonthReport)
    {
        aiMonthReport.setCreateTime(DateUtils.getNowDate());
        return aiMonthReportMapper.insertAiMonthReport(aiMonthReport);
    }

    /**
     * 修改月报
     * 
     * @param aiMonthReport 月报
     * @return 结果
     */
    @Override
    public int updateAiMonthReport(AiMonthReport aiMonthReport)
    {
        return aiMonthReportMapper.updateAiMonthReport(aiMonthReport);
    }

    /**
     * 批量删除月报
     * 
     * @param monthReportIds 需要删除的月报主键
     * @return 结果
     */
    @Override
    public int deleteAiMonthReportByMonthReportIds(Long[] monthReportIds)
    {
        return aiMonthReportMapper.deleteAiMonthReportByMonthReportIds(monthReportIds);
    }

    /**
     * 删除月报信息
     * 
     * @param monthReportId 月报主键
     * @return 结果
     */
    @Override
    public int deleteAiMonthReportByMonthReportId(Long monthReportId)
    {
        return aiMonthReportMapper.deleteAiMonthReportByMonthReportId(monthReportId);
    }
}
