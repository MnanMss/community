package com.cdu.community.server.shared.port;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdu.community.server.shared.domain.dto.RoomDTO;
import com.cdu.community.server.shared.domain.dto.RoomProprietorDTO;
import com.cdu.community.server.shared.domain.entity.*;
import com.cdu.community.server.shared.infrastructure.orm.BuildingMapper;
import com.cdu.community.server.shared.infrastructure.orm.EdificeMapper;
import com.cdu.community.server.shared.infrastructure.orm.ProprietorMapper;
import com.cdu.community.server.shared.infrastructure.orm.RoomMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Jhc
 * @date: 2024-06-15
 **/

@Service
@RequiredArgsConstructor
public class SharedService {
    private final RoomMapper roomMapper;
    private final BuildingMapper buildingMapper;
    private final EdificeMapper edificeMapper;
    private final ProprietorMapper proprietorMapper;

    /**
     * 查询大厦/小区信息
     *
     * @param edificeId 大厦/小区ID
     * */
    public Edifice getEdificeById(Long edificeId) {
        return edificeMapper.selectById(edificeId);
    }

    /**
     * 查询楼栋信息
     *
     * @param buildingId 楼栋ID
     * */
    public Building getBuildingById(Long buildingId) {
        return buildingMapper.selectById(buildingId);
    }

    /**
     * 查询房间信息
     *
     * @param roomId 房间ID
     * */
    public Room getRoomById(Long roomId){
        return roomMapper.selectById(roomId);
    }

    /**
     * 查询房间列表
     * */
    public Page<Room> listRoom(RoomDTO condition) {
        LambdaQueryWrapper<Room> query = new LambdaQueryWrapper<>();
        if (condition.getCode() != null && !condition.getCode().isEmpty()){
            query.likeLeft(Room::getCode, condition.getCode());
        }
        Page<Room> page = new Page<>(condition.getPageNum(), condition.getPageSize());
        return roomMapper.selectPage(page, query);
    }

    /**
     * 查询房间信息
     *
     * @param roomCode 房间号
     * */
    public Room getRoomByCode(String roomCode){
        return roomMapper.getRoomByCode(roomCode);
    }

    /**
     * 查询业主信息
     *
     * @param proprietorName 业主姓名
     * */
    public Proprietor getProprietorByName(String proprietorName){
        return proprietorMapper.getProprietorByName(proprietorName);
    }

    public Page<RoomProprietor> listRoomProprietor(RoomProprietorDTO condition) {
        List<RoomProprietor> list = roomMapper.listRoomProprietor(condition);
        // 获取分页参数
        int pageNum = condition.getPageNum();
        int pageSize = condition.getPageSize();
        int offset = (pageNum - 1) * pageSize;
        condition.setOffset(offset);

        // 计算总数和分页
        int total = list.size();
        int fromIndex = Math.min((pageNum - 1) * pageSize, total);
        int toIndex = Math.min(pageNum * pageSize, total);

        // 获取当前页的数据
        List<RoomProprietor> paginatedList = list.subList(fromIndex, toIndex);
        // 创建Page对象
        Page<RoomProprietor> page = new Page<>();
        page.setRecords(paginatedList);
        page.setTotal(total);
        page.setCurrent(pageNum);
        page.setSize(pageSize);

        return page;
    }
}