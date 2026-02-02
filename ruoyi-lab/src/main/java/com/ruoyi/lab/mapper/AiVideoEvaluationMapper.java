package com.ruoyi.lab.mapper;

import java.util.List;
import com.ruoyi.lab.domain.AiVideoEvaluation;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 视频评价Mapper接口
 * 
 * @author Ldolphin
 * @date 2025-07-23
 */
@Repository
public interface AiVideoEvaluationMapper 
{
    /**
     * 查询视频评价
     * 
     * @param id 视频评价主键
     * @return 视频评价
     */
    public AiVideoEvaluation selectAiVideoEvaluationById(Long id);

    /**
     * 查询视频评价列表
     * 
     * @param aiVideoEvaluation 视频评价
     * @return 视频评价集合
     */
    public List<AiVideoEvaluation> selectAiVideoEvaluationList(AiVideoEvaluation aiVideoEvaluation);

    /**
     * 新增视频评价
     * 
     * @param aiVideoEvaluation 视频评价
     * @return 结果
     */
    public int insertAiVideoEvaluation(AiVideoEvaluation aiVideoEvaluation);

    /**
     * 修改视频评价
     * 
     * @param aiVideoEvaluation 视频评价
     * @return 结果
     */
    public int updateAiVideoEvaluation(AiVideoEvaluation aiVideoEvaluation);

    /**
     * 删除视频评价
     * 
     * @param id 视频评价主键
     * @return 结果
     */
    public int deleteAiVideoEvaluationById(Long id);

    /**
     * 批量删除视频评价
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAiVideoEvaluationByIds(Long[] ids);


    // 新增关联查询方法
    @Select("SELECT e.*, s.course_name, s.teacher_name, s.location " +
            "FROM ai_video_evaluation e " +
            "LEFT JOIN ai_class_schedule s ON e.schedule_id = s.schedule_id " +
            "WHERE e.id = #{id}")
    AiVideoEvaluation selectWithSchedule(Long id);

    // 根据排课ID查询评价
    @Select("SELECT * FROM ai_video_evaluation WHERE schedule_id = #{scheduleId}")
    List<AiVideoEvaluation> selectByScheduleId(Long scheduleId);
}
