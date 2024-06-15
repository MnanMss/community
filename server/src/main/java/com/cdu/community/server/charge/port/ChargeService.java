package com.cdu.community.server.charge.port;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdu.community.server.charge.domain.dto.ChargeRoomDTO;
import com.cdu.community.server.charge.domain.dto.ChargeRoomStatisticsDTO;
import com.cdu.community.server.charge.domain.entity.ChargeProject;
import com.cdu.community.server.charge.domain.dto.ChargeProjectDTO;
import com.cdu.community.server.charge.domain.entity.ChargeRoom;
import com.cdu.community.server.charge.domain.entity.ChargeRoomStatistics;
import com.cdu.community.server.charge.domain.vo.ChargeRoomStatisticsVO;
import com.cdu.community.server.charge.domain.vo.ReceiveManageVO;
import com.cdu.community.server.charge.infrastructure.orm.ChargeProjectMapper;
import com.cdu.community.server.charge.infrastructure.orm.ChargeRoomMapper;
import com.cdu.community.server.charge.infrastructure.orm.ChargeRoomStatisticsMapper;
import com.cdu.community.server.shared.domain.PageVO;
import com.cdu.community.server.shared.port.SharedService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mila
 * @date 2024/6/14 下午3:19
 */
@Service
@RequiredArgsConstructor
public class ChargeService {
    private final ChargeProjectMapper chargeProjectMapper;
    private final ChargeRoomMapper chargeRoomMapper;
    private final SharedService sharedService;
    private final ChargeRoomStatisticsMapper chargeRoomStatisticsMapper;


    public void addChargeProject(ChargeProjectDTO chargeProjectDTO) {
        ChargeProject chargeProject = new ChargeProject();
        BeanUtils.copyProperties(chargeProjectDTO , chargeProject);
        chargeProjectMapper.insert(chargeProject);
    }

    public void editChargeProject(Long id, ChargeProjectDTO chargeProjectDTO) {
        ChargeProject chargeProject = new ChargeProject();
        BeanUtils.copyProperties(chargeProjectDTO, chargeProject);
        chargeProject.setId(id);
        chargeProjectMapper.updateById(chargeProject);
    }

    public void deleteChargeProject(Long id) {
        chargeProjectMapper.deleteById(id);
    }

    public Page<ChargeProject> listChargeProject(ChargeProjectDTO condition) {
        LambdaQueryWrapper<ChargeProject> query = new LambdaQueryWrapper<>();
        if(condition.getName() != null && !condition.getName().isEmpty()){
            query.like(ChargeProject::getName, condition.getName());
        }
        if(condition.getCode() != null && !condition.getCode().isEmpty()){
            query.likeLeft(ChargeProject::getCode, condition.getCode());
        }
        if(condition.getBillingType() != null){
            query.eq(ChargeProject::getBillingType, condition.getBillingType());
        }
        if(condition.getChargeType() != null){
            query.eq(ChargeProject::getChargeType, condition.getChargeType());
        }
        Page<ChargeProject> page = new Page<>(condition.getPageNum(), condition.getPageSize());
        return chargeProjectMapper.selectPage(page, query);
    }

    /**
     * 获取收费项目信息
     *
     * @param chargeProjectId 收费项目ID
     * */
    public ChargeProject getChargeProjectById(Long chargeProjectId){
        return chargeProjectMapper.selectById(chargeProjectId);
    }

    public void addChargeRoom(ChargeRoomDTO chargeRoomDTO) {
        ChargeRoom chargeRoom = new ChargeRoom();
        BeanUtils.copyProperties(chargeRoomDTO , chargeRoom);
        chargeRoomMapper.insert(chargeRoom);
    }


    public void editChargeRoom(Long id, ChargeRoomDTO chargeRoomDTO) {
        ChargeRoom chargeRoom = new ChargeRoom();
        BeanUtils.copyProperties(chargeRoomDTO, chargeRoom);
        chargeRoom.setId(id);
        chargeRoomMapper.updateById(chargeRoom);
    }

    public void deleteChargeRoom(Long id) {
        chargeRoomMapper.deleteById(id);
    }

    public Page<ChargeRoom> listChargeRoom(ChargeRoomDTO condition) {
        LambdaQueryWrapper<ChargeRoom> query = new LambdaQueryWrapper<>();
        if(condition.getRoomId() != null){
            query.eq(ChargeRoom::getRoomId, condition.getRoomId());
        }
        Page<ChargeRoom> page = new Page<>(condition.getPageNum(), condition.getPageSize());
        return chargeRoomMapper.selectPage(page, query);
    }

    public PageVO<ChargeRoomStatisticsVO> scheduleChargeRoom(ChargeRoomStatisticsDTO condition) {
        String roomCode = sharedService.getRoomByCode(condition.getRoomCode()).getCode();
        condition.setRoomCode(roomCode);
        String proprietorName  = sharedService.getProprietorByName(condition.getProprietorName()).getName();
        condition.setProprietorName(proprietorName);

        List<ChargeRoomStatistics> list = chargeRoomStatisticsMapper.list(condition);

        // 获取分页参数
        int pageNum = condition.getPageNum();
        int pageSize = condition.getPageSize();
        // 计算总数和分页
        int total = list.size();
        int fromIndex = Math.min((pageNum - 1) * pageSize, total);
        int toIndex = Math.min(pageNum * pageSize, total);
        // 获取当前页的数据
        List<ChargeRoomStatistics> paginatedList = list.subList(fromIndex, toIndex);
        // 转换为 VO 对象
        List<ChargeRoomStatisticsVO> voList = paginatedList.stream().map(this::convertToVO).collect(Collectors.toList());

        return new PageVO<>(total, voList);
    }

    /**
     * 将entity转换成VO
     * */
    private ChargeRoomStatisticsVO convertToVO(ChargeRoomStatistics statistics) {
        ChargeRoomStatisticsVO vo = new ChargeRoomStatisticsVO();
        vo.setRoomCode(statistics.getRoomCode());
        vo.setProprietorName(statistics.getProprietorName());

        List<ReceiveManageVO> receiveManageVOList = statistics.getReceiveManages().stream()
                .map(manage -> {
                    ReceiveManageVO receiveManageVO = new ReceiveManageVO();
                    receiveManageVO.setChargeProjectName(manage.getChargeProjectName());
                    receiveManageVO.setReceiveAmount(manage.getReceiveAmount());
                    return receiveManageVO;
                }).collect(Collectors.toList());
        vo.setReceiveManages(receiveManageVOList);

        return vo;
    }
}
