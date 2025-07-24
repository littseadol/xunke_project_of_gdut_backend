package com.ruoyi.lab.service.impl;

import com.ruoyi.lab.mapper.*;
import com.ruoyi.lab.service.IAiCommonService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class AiCommonServiceImpl implements IAiCommonService {

    @Autowired
    private AiAlgorithmMapper aiAlgorithmMapper;

    @Autowired
    private AiBuildingMapper aiBuildingMapper;

    @Autowired
    private AiRoomMapper aiRoomMapper;

    @Autowired
    private AiTeamMapper aiTeamMapper;

    @Autowired
    private AiFaceMapper aiFaceMapper;

    @Autowired
    private AiDeviceMapper aiDeviceMapper;

    @Override
    public Map<String, Object> selectCounts() {
        Map<String,Object> data = new HashMap<>();
        data.put("algorithmCount", aiAlgorithmMapper.selectCount());
        data.put("buildingCount",aiBuildingMapper.selectCount());
        data.put("roomCount",aiRoomMapper.selectCount());
        data.put("teamCount",aiTeamMapper.selectCount());
        data.put("faceCount",aiFaceMapper.selectCount());
        data.put("deviceCount",aiDeviceMapper.selectCount());
        data.put("eventCount", 0);
        data.put("reportCount", 0);
        return data;
    }
}