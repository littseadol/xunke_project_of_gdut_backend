package com.ruoyi.lab.domain.request;

import java.util.List;

public class UpdateTeamRoomRequest {
    private Long teamId;
    private List<Long> roomIds;

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public List<Long> getRoomIds() {
        return roomIds;
    }

    public void setRoomIds(List<Long> roomIds) {
        this.roomIds = roomIds;
    }
}
