package com.cdu.community.server.charge.port;

import com.cdu.community.server.charge.domain.dto.ChargeProjectDTO;
import com.cdu.community.server.shared.domain.Resp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
