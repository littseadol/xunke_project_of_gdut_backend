package com.ruoyi.lab.mapper;

import java.util.List;
import com.ruoyi.lab.domain.AiRoom;
import org.apache.ibatis.annotations.Mapper;

/**
 * 房间Mapper接口
 * 
 * @author ruoyi
 * @date 2025-07-12
 */
@Mapper
public interface AiRoomMapper 
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
    public int insertRoomBuilding(AiRoom aiRoom);

    /**
     * 修改房间
     * 
     * @param aiRoom 房间
     * @return 结果
     */
    public int updateAiRoom(AiRoom aiRoom);
    public int updateRoomBuilding(AiRoom aiRoom);

    /**
     * 删除房间
     * 
     * @param roomId 房间主键
     * @return 结果
     */
    public int deleteAiRoomByRoomId(Long roomId);
    public int deleteRoomBuildingByRoomId(Long roomId);

    /**
     * 批量删除房间
     * 
     * @param roomIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteAiRoomByRoomIds(Long[] roomIds);
    public int deleteRoomBuildingByRoomIds(Long[] roomIds);

    // 绑定团队
    public int selectRoomTeamCountByRoomId(AiRoom aiRoom);
    public int insertRoomTeam(AiRoom aiRoom);
    public int updateRoomTeam(AiRoom aiRoom);

    public int selectCount();
}
