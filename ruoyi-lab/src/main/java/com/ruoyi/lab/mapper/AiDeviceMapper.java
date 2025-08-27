package com.ruoyi.lab.mapper;

import com.ruoyi.lab.domain.AiDevice;
import com.ruoyi.lab.domain.AiDeviceAlgorithm;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AiDeviceMapper {
    void addDevice(AiDevice device);
    void insertAiDeviceGroup(@Param("deviceId") Long deviceId, @Param("groupId") Long groupId);
    void addDeviceAlgorithm(AiDeviceAlgorithm deviceAlgorithm);

    List<AiDevice> getDeviceList(AiDevice aiDevice);
    List<AiDevice> selectAllDeviceList();

    void deleteDevice(@Param("deviceId") Long deviceId);
    void deleteAiDeviceGroup(@Param("deviceId") Long deviceId);
    void deleteDeviceAlgorithm(@Param("deviceId") Long deviceId);

    List<AiDeviceAlgorithm> getDeviceAlgorithmListByDeviceId(@Param("deviceId") Long deviceId);

    void updateDevice(AiDevice aiDevice);
    void updateDeviceGroup(@Param("deviceId") Long deviceId, @Param("groupId") Long groupId);
    void updateDeviceAlgorithm(AiDeviceAlgorithm deviceAlgorithm);

    void updateCheckStatus(AiDevice aiDevice);

    List<AiDevice> getCheckedDeviceList();

    String getRtspUrlByDeviceId(@Param("deviceId") Long deviceId);

    public int selectCount();

    // 更新坐标
    public int updateCoordinate(AiDevice aiDevice);

    @Select("SELECT g.group_name " +
            "FROM ai_device_group dg " +
            "JOIN ai_group g ON dg.group_id = g.group_id " +
            "WHERE dg.device_id = #{deviceId}")
    public String getLocationByDeviceId(@Param("deviceId") Long deviceId);
}