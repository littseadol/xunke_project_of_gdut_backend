package com.ruoyi.lab.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.lab.domain.AiClassSchedule;
import com.ruoyi.lab.domain.AiVideoEvaluation;
import com.ruoyi.lab.mapper.AiClassScheduleMapper;
import com.ruoyi.lab.mapper.AiVideoEvaluationMapper;
import com.ruoyi.lab.service.IAiVideoEvaluationService;
import com.ruoyi.lab.service.impl.AiClassScheduleServiceImpl;
import com.ruoyi.lab.service.impl.AiScheduleMatchServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AiVideoEvaluationServiceImpl implements IAiVideoEvaluationService {
    @Autowired
    private AiVideoEvaluationMapper aiVideoEvaluationMapper;

    @Autowired
    private AiClassScheduleServiceImpl scheduleService;

    @Autowired
    private AiScheduleMatchServiceImpl aiScheduleMatchService;



    /**
     * 查询视频评价
     *
     * @param id 视频评价主键
     * @return 视频评价
     */
    @Override
    public AiVideoEvaluation selectAiVideoEvaluationById(Long id) {
        AiVideoEvaluation evaluation = aiVideoEvaluationMapper.selectAiVideoEvaluationById(id);
        if (evaluation != null && evaluation.getDeviceId() != null) {
            enrichInfo(evaluation, evaluation.getDeviceId());
        }
        return evaluation;
    }

    /**
     * 查询视频评价列表
     *
     * @param aiVideoEvaluation 视频评价
     * @return 视频评价
     */
    @Override
    public List<AiVideoEvaluation> selectAiVideoEvaluationList(AiVideoEvaluation aiVideoEvaluation) {
        List<AiVideoEvaluation> list = aiVideoEvaluationMapper.selectAiVideoEvaluationList(aiVideoEvaluation);
        list.forEach(evaluation -> {
            if (evaluation.getDeviceId() != null) {
                enrichInfo(evaluation, evaluation.getDeviceId());
            }
        });
        return list;
    }

    /**
     * 新增视频评价
     *
     * @param aiVideoEvaluation 视频评价
     * @return 结果
     */
    @Override
    public int insertAiVideoEvaluation(AiVideoEvaluation aiVideoEvaluation) {
        if (aiVideoEvaluation.getScheduleId() != null &&
                scheduleService.selectAiClassScheduleByScheduleId(aiVideoEvaluation.getScheduleId()) == null) {
            throw new RuntimeException("关联的排课信息不存在");
        }
        aiVideoEvaluation.setCreateTime(DateUtils.getNowDate());
        return aiVideoEvaluationMapper.insertAiVideoEvaluation(aiVideoEvaluation);
    }

    /**
     * 修改视频评价
     *
     * @param aiVideoEvaluation 视频评价
     * @return 结果
     */
    @Override
    public int updateAiVideoEvaluation(AiVideoEvaluation aiVideoEvaluation) {
        if (aiVideoEvaluation.getScheduleId() != null &&
                scheduleService.selectAiClassScheduleByScheduleId(aiVideoEvaluation.getScheduleId()) == null) {
            throw new RuntimeException("关联的排课信息不存在");
        }
        aiVideoEvaluation.setUpdateTime(DateUtils.getNowDate());
        return aiVideoEvaluationMapper.updateAiVideoEvaluation(aiVideoEvaluation);
    }

    /**
     * 批量删除视频评价
     *
     * @param ids 需要删除的视频评价主键
     * @return 结果
     */
    @Override
    public int deleteAiVideoEvaluationByIds(Long[] ids) {
        return aiVideoEvaluationMapper.deleteAiVideoEvaluationByIds(ids);
    }

    /**
     * 删除视频评价信息
     *
     * @param id 视频评价主键
     * @return 结果
     */
    @Override
    public int deleteAiVideoEvaluationById(Long id) {
        return aiVideoEvaluationMapper.deleteAiVideoEvaluationById(id);
    }

    /**
     * 新增保存方法（带排课ID校验）
     */
    public boolean saveEvaluation(AiVideoEvaluation evaluation) {
        if (scheduleService.selectAiClassScheduleByScheduleId(evaluation.getScheduleId()) == null) {
            throw new RuntimeException("排课信息不存在");
        }
        return aiVideoEvaluationMapper.insertAiVideoEvaluation(evaluation) > 0;
    }

    /**
     * 获取带排课信息的评价详情
     */
    public AiVideoEvaluation getDetail(Long id) {
        AiVideoEvaluation evaluation = aiVideoEvaluationMapper.selectWithSchedule(id);
        if (evaluation == null) {
            throw new RuntimeException("评价记录不存在");
        }
        return evaluation;
    }

    /**
     * 根据排课ID查询评价列表
     */
    public List<AiVideoEvaluation> listBySchedule(Long scheduleId) {
        return aiVideoEvaluationMapper.selectByScheduleId(scheduleId);
    }

    /**
     * 丰富评价信息 - 添加排课相关信息
     * @param evaluation 视频评价对象
     * @param deviceId 设备ID
     */
    //这个不能调用插入时候的那个匹配逻辑，而且你此时不是获得了ScheduleId了吗，直接select
    private void enrichInfo(AiVideoEvaluation evaluation, Long deviceId) {
        try {
            log.info("这里是enrichInfo，听到请回答:",evaluation);
            AiClassSchedule schedule = scheduleService.selectAiClassScheduleByScheduleId(evaluation.getScheduleId());
            if (schedule != null) {
                evaluation.setCourseName(schedule.getCourseName());
                evaluation.setTeacherName(schedule.getTeacherName());
                log.debug("成功填充排课信息：scheduleId={}, courseName={}",
                        schedule.getScheduleId(), schedule.getCourseName());
            } else {
                log.warn("未找到设备ID: {} 对应的课程安排", deviceId);
            }
        } catch (Exception e) {
            log.error("丰富评价信息失败，设备ID: {}", deviceId, e);
        }
    }
}