package com.ruoyi.lab.service;

import java.util.List;
import com.ruoyi.lab.domain.AiRoom;
import com.ruoyi.lab.domain.AiTeam;

/**
 * 房间Service接口
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
public interface IAiRoomService 
{
    /**
     * 查询房间
     * 
     * @param roomId 房间主键
     * @return 房间
     */
    public AiRoom selectAiRoomByRoomId(Long roomId);

    /**
     * 查询房间列表
     * 
     * @param aiRoom 房间
     * @return 房间集合
     */
    public List<AiRoom> selectAiRoomList(AiRoom aiRoom);
    public List<AiRoom> selectUnrelatedRoomList();

    /**
     * 新增房间
     * 
     * @param aiRoom 房间
     * @return 结果
     */
    public int insertAiRoom(AiRoom aiRoom);

    /**
     * 修改房间
     * 
     * @param aiRoom 房间
     * @return 结果
     */
    public int updateAiRoom(AiRoom aiRoom);

    /**
     * 批量删除房间
     * 
     * @param roomIds 需要删除的房间主键集合
     * @return 结果
     */
    public int deleteAiRoomByRoomIds(Long[] roomIds);

    /**
     * 删除房间信息
     * 
     * @param roomId 房间主键
     * @return 结果
     */
    public int deleteAiRoomByRoomId(Long roomId);

    // 绑定团队
    public int bindTeam(AiRoom aiRoom);
}
