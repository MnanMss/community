package com.cdu.community.server.charge.port;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cdu.community.server.charge.domain.dto.ChargeProjectDTO;
import com.cdu.community.server.charge.domain.entity.ChargeProject;
import com.cdu.community.server.charge.domain.vo.ChargeProjectVo;
import com.cdu.community.server.shared.domain.PageDTO;
import com.cdu.community.server.shared.domain.Resp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author mila
 * @date 2024/6/14 下午3:18
 */
@RestController
@RequestMapping("/api/charge")
@RequiredArgsConstructor
@Validated
@Slf4j
@Tag(name="ChargeController" , description = "收费管理")
public class ChargeController {
    private final ChargeService chargeService;

    @PostMapping("/project")
    @Operation(description = "新增收费项目")
    public Resp<Void> addChargeProject(@RequestBody ChargeProjectDTO chargeProjectDTO) {
        log.info("新增收费项目：{}" , chargeProjectDTO);
        chargeService.addChargeProject(chargeProjectDTO);
        return Resp.ok();
    }

    @PutMapping("/project")
    @Operation(description = "修改收费项目")
    public Resp<Void> editChargeProject(Long id, @RequestBody ChargeProjectDTO chargeProjectDTO){
        log.info("修改收费项目：{}", chargeProjectDTO);
        chargeService.editChargeProject(id, chargeProjectDTO);
        return Resp.ok();
    }

    @DeleteMapping("/project/{id}")
    @Operation(description = "删除收费项目")
    public Resp<Void> deleteChargeProject(@PathVariable("id") Long id){
        log.info("删除收费项目：{}", id);
        chargeService.deleteChargeProject(id);
        return Resp.ok();
    }

    @GetMapping("/project/list")
    @Operation(description = "查询收费项目列表")
    public Resp<PageDTO<ChargeProjectVo>> listChargeProject(ChargeProjectDTO condition){
        log.info("查询收费项目列表：{}", condition);
        Page<ChargeProject> list = chargeService.listChargeProject(condition);
        log.info(list.getRecords().toString());
        List<ChargeProjectVo> voList = list.getRecords().stream().
                map(ChargeProjectVo::of)
                .toList();
        return Resp.ok(new PageDTO<>(list.getTotal(), voList));
    }
}
