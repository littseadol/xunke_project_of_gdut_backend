package com.ruoyi.lab.mapper;

import java.util.List;
import com.ruoyi.lab.domain.AiMonthReport;

/**
 * 月报Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-17
 */
public interface AiMonthReportMapper 
{
    /**
     * 查询月报
     * 
     * @param monthReportId 月报主键
     * @return 月报
     */
    public AiMonthReport selectAiMonthReportByMonthReportId(Long monthReportId);

    /**
     * 查询月报列表
     * 
     * @param aiMonthReport 月报
     * @return 月报集合
     */
    public List<AiMonthReport> selectAiMonthReportList(AiMonthReport aiMonthReport);

    /**
     * 新增月报
     * 
     * @param aiMonthReport 月报
     * @return 结果
     */
    public int insertAiMonthReport(AiMonthReport aiMonthReport);

    /**
     * 修改月报
     * 
     * @param aiMonthReport 月报
     * @return 结果
     */
    public int updateAiMonthReport(AiMonthReport aiMonthReport);

    /**
     * 删除月报
     * 
     * @param monthReportId 月报主键
     * @return 结果
     */
    public int deleteAiMonthReportByMonthReportId(Long monthReportId);

    /**
     * 批量删除月报
     * 
     * @param monthReportIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAiMonthReportByMonthReportIds(Long[] monthReportIds);
}
