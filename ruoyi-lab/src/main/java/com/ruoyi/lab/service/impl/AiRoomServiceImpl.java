package com.ruoyi.lab.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.lab.mapper.AiRoomMapper;
import com.ruoyi.lab.domain.AiRoom;
import com.ruoyi.lab.service.IAiRoomService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 房间Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class AiRoomServiceImpl implements IAiRoomService 
{
    @Autowired
    private AiRoomMapper aiRoomMapper;

    /**
     * 查询房间
     * 
     * @param roomId 房间主键
     * @return 房间
     */
    @Override
    public AiRoom selectAiRoomByRoomId(Long roomId)
    {
        return aiRoomMapper.selectAiRoomByRoomId(roomId);
    }

    /**
     * 查询房间列表
     * 
     * @param aiRoom 房间
     * @return 房间
     */
    @Override
    public List<AiRoom> selectAiRoomList(AiRoom aiRoom)
    {
        return aiRoomMapper.selectAiRoomList(aiRoom);
    }

    @Override
    public List<AiRoom> selectUnrelatedRoomList() {
        return aiRoomMapper.selectUnrelatedRoomList();
    }

    /**
     * 新增房间
     * 
     * @param aiRoom 房间
     * @return 结果
     */
    @Override
    public int insertAiRoom(AiRoom aiRoom)
    {
        aiRoom.setCreateTime(DateUtils.getNowDate());
        int result = aiRoomMapper.insertAiRoom(aiRoom);
        if (result == 0) {
            return result;
        } else {
            return aiRoomMapper.insertRoomBuilding(aiRoom);
        }
    }

    /**
     * 修改房间
     * 
     * @param aiRoom 房间
     * @return 结果
     */
    @Override
    public int updateAiRoom(AiRoom aiRoom) {
        int result = aiRoomMapper.updateAiRoom(aiRoom);
        if (result == 0) {
            return result;
        } else {
            return aiRoomMapper.updateRoomBuilding(aiRoom);
        }
    }

    /**
     * 批量删除房间
     * 
     * @param roomIds 需要删除的房间主键
     * @return 结果
     */
    @Override
    public int deleteAiRoomByRoomIds(Long[] roomIds)
    {
        int result = aiRoomMapper.deleteAiRoomByRoomIds(roomIds);
        if (result == 0) {
            return result;
        } else {
            return aiRoomMapper.deleteRoomBuildingByRoomIds(roomIds);
        }
    }

    /**
     * 删除房间信息
     * 
     * @param roomId 房间主键
     * @return 结果
     */
    @Override
    public int deleteAiRoomByRoomId(Long roomId)
    {
        int result = aiRoomMapper.deleteAiRoomByRoomId(roomId);
        if (result == 0) {
            return result;
        } else {
            return aiRoomMapper.deleteRoomBuildingByRoomId(roomId);
        }
    }

    @Override
    public int bindTeam(AiRoom aiRoom) {
        int count = aiRoomMapper.selectRoomTeamCountByRoomId(aiRoom);
        if (count == 0) {
            return aiRoomMapper.insertRoomTeam(aiRoom);
        } else {
            return aiRoomMapper.updateRoomTeam(aiRoom);
        }
    }
}