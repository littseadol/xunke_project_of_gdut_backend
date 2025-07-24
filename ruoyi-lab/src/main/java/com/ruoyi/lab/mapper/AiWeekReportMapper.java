package com.ruoyi.lab.mapper;

import java.util.List;
import com.ruoyi.lab.domain.AiWeekReport;

/**
 * 周报Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-17
 */
public interface AiWeekReportMapper 
{
    /**
     * 查询周报
     * 
     * @param weekReportId 周报主键
     * @return 周报
     */
    public AiWeekReport selectAiWeekReportByWeekReportId(Long weekReportId);

    /**
     * 查询周报列表
     * 
     * @param aiWeekReport 周报
     * @return 周报集合
     */
    public List<AiWeekReport> selectAiWeekReportList(AiWeekReport aiWeekReport);

    /**
     * 新增周报
     * 
     * @param aiWeekReport 周报
     * @return 结果
     */
    public int insertAiWeekReport(AiWeekReport aiWeekReport);

    /**
     * 修改周报
     * 
     * @param aiWeekReport 周报
     * @return 结果
     */
    public int updateAiWeekReport(AiWeekReport aiWeekReport);

    /**
     * 删除周报
     * 
     * @param weekReportId 周报主键
     * @return 结果
     */
    public int deleteAiWeekReportByWeekReportId(Long weekReportId);

    /**
     * 批量删除周报
     * 
     * @param weekReportIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAiWeekReportByWeekReportIds(Long[] weekReportIds);
}
