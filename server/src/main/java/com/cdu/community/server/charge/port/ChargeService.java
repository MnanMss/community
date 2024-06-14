package com.cdu.community.server.charge.port;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdu.community.server.charge.domain.entity.ChargeProject;
import com.cdu.community.server.charge.domain.dto.ChargeProjectDTO;
import com.cdu.community.server.charge.infrastructure.orm.ChargeProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @author mila
 * @date 2024/6/14 下午3:19
 */
@Service
@RequiredArgsConstructor
public class ChargeService {
    private final ChargeProjectMapper chargeProjectMapper;


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
}
