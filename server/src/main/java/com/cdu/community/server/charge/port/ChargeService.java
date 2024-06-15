package com.cdu.community.server.charge.port;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdu.community.server.charge.domain.dto.*;
import com.cdu.community.server.charge.domain.entity.*;
import com.cdu.community.server.charge.domain.vo.ChargeManageVO;
import com.cdu.community.server.charge.domain.vo.ChargeRoomStatisticsVO;
import com.cdu.community.server.charge.domain.vo.ReceiveManageVO;
import com.cdu.community.server.charge.infrastructure.orm.*;
import com.cdu.community.server.shared.domain.PageVO;
import com.cdu.community.server.shared.port.SharedService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
    private final ChargeManageMapper chargeManageMapper;
    private final ReceiveManageMapper receiveManageMapper;


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
     * 获取应收管理信息
     *
     * @param roomId 房间id
     * */
    public List<ReceiveManage> getReceiveManage(Long roomId){
        LambdaQueryWrapper<ReceiveManage> query = new LambdaQueryWrapper<>();
        query.eq(ReceiveManage::getRoomId, roomId);
        return receiveManageMapper.selectList(query);
    }

    public PageVO<ChargeManageVO> listChargeManage(ChargeManageDTO condition) {
        //获取应收管理信息
        List<ReceiveManage> receiveManageList = getReceiveManage(condition.getRoomId());
        //获取应收管理id列表
        List<Long> receiveManageIds = receiveManageList.stream()
                .map(ReceiveManage::getId)
                .toList();

        List<ChargeManage> chargeManageList = chargeManageMapper.selectBatchIds(receiveManageIds);

        List<Long> chargeProjectIds = receiveManageList.stream()
                .map(ReceiveManage::getChargeProjectId)
                .toList();
        List<ChargeProject> chargeProjectList = chargeProjectMapper.selectBatchIds(chargeProjectIds);
        // 创建一个Map来存储ChargeProject对象，便于后续查找
        Map<Long, ChargeProject> chargeProjectMap = chargeProjectList.stream()
                .collect(Collectors.toMap(ChargeProject::getId, Function.identity()));
        // 组合信息
        List<ChargeManageVO> chargeManageVOList = chargeManageList.stream()
                .map(chargeManage -> {
                    ChargeManageVO vo = ChargeManageVO.of(chargeManage);
                    // 查找对应的ChargeProject对象
                    ChargeProject chargeProject = chargeProjectMap.get(chargeManage.getId());
                    if (chargeProject != null) {
                        vo.setName(chargeProject.getName());
                    }
                    return vo;
                })
                .collect(Collectors.toList());

        // 获取分页参数
        int pageNum = condition.getPageNum();
        int pageSize = condition.getPageSize();
        int total = chargeManageList.size();
        int fromIndex = Math.min((pageNum - 1) * pageSize, total);
        int toIndex = Math.min(pageNum * pageSize, total);

        // 获取当前页的数据
        List<ChargeManageVO> paginatedList = chargeManageVOList.subList(fromIndex, toIndex);
        return new PageVO<>(total, paginatedList);
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

    public void addReceiveManage(ReceiveManageDTO receiveManageDTO) {
        ReceiveManage receiveManage = new ReceiveManage();
        BeanUtils.copyProperties(receiveManageDTO, receiveManage);
        receiveManageMapper.insert(receiveManage);
    }

    public void editReceiveManage(ReceiveManageDTO receiveManageDTO) {
        ReceiveManage receiveManage = new ReceiveManage();
        BeanUtils.copyProperties(receiveManageDTO, receiveManage);
        receiveManageMapper.updateById(receiveManage);
    }
}
