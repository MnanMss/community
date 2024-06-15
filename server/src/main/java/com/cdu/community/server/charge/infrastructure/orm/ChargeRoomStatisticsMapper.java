package com.cdu.community.server.charge.infrastructure.orm;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cdu.community.server.charge.domain.dto.ChargeRoomStatisticsDTO;
import com.cdu.community.server.charge.domain.entity.ChargeRoomStatistics;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChargeRoomStatisticsMapper extends BaseMapper<ChargeRoomStatistics> {

    List<ChargeRoomStatistics> list(@Param("param") ChargeRoomStatisticsDTO condition);
}
