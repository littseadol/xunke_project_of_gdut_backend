package com.ruoyi.lab.service.impl;

import com.ruoyi.lab.domain.AiClassSchedule;
import com.ruoyi.lab.mapper.AiClassScheduleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class AiScheduleMatchServiceImpl {
    private final AiClassScheduleMapper scheduleMapper;
    private final AiDeviceServiceImpl deviceService;

    /**
     * 自动匹配当前时间的排课记录
     */
    public AiClassSchedule matchCurrentSchedule(Long deviceId) {
        // 1. 获取设备对应的教室位置
        String location = deviceService.getLocationByDeviceId(deviceId);

        // 2. 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 3. 精确匹配
        return matchSchedule(location, now.toLocalDate(), now.toLocalTime());
    }

    /**
     * 通用匹配方法（可按需调用）
     */
    public AiClassSchedule matchSchedule(String location, LocalDate date, LocalTime time) {
        return scheduleMapper.selectByLocationAndTime(location, date, time);
    }

    /**
     * 匹配指定时间范围的排课（用于视频回放评价）
     */
    /*public List<AiClassSchedule> matchScheduleRange(String deviceId,
                                                    LocalDateTime start,
                                                    LocalDateTime end) {
        String location = deviceService.getLocationByDeviceId(deviceId);

        return scheduleMapper.selectList(
                new QueryWrapper<Schedule>()
                        .eq("location", location)
                        .between("schedule_date", start.toLocalDate(), end.toLocalDate())
                        .and(wrapper -> wrapper
                                .between("start_time", start.toLocalTime(), end.toLocalTime())
                                .or()
                                .between("end_time", start.toLocalTime(), end.toLocalTime())
                                .or()
                                .le("start_time", start.toLocalTime()).ge("end_time", end.toLocalTime())
                        )
        );
    }*/
}