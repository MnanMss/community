package com.cdu.community.server.shared.infrastructure.orm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdu.community.server.shared.domain.dto.RoomProprietorDTO;
import com.cdu.community.server.shared.domain.entity.Room;
import com.cdu.community.server.shared.domain.entity.RoomProprietor;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

import java.util.List;

/**
 * @author: Jhc
 * @date: 2024-06-15
 **/

public interface RoomMapper extends BaseMapper<Room> {

    @Select("select * from room r where r.code=#{roomCode} ")
    Room getRoomByCode(String roomCode);

    List<RoomProprietor> listRoomProprietor(@Param("param") RoomProprietorDTO condition);
}
