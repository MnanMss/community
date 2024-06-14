package com.cdu.community.server.meter.port;

import com.cdu.community.server.meter.domain.dto.MeterTypeDTO;
import com.cdu.community.server.shared.domain.Resp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.annotations.OpenAPI30;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mila
 * @date 2024/6/14 下午2:55
 */
@RestController
@RequestMapping("/api/meter")
@RequiredArgsConstructor
@Slf4j
@Validated
@Tag(name="MeterController" , description = "抄表管理")
public class MeterController {

    private final MeterService meterService;

    @PostMapping("/type")
    @Operation(description = "添加表计类别")
    public Resp<Void> addMeterType(@RequestBody @Valid MeterTypeDTO meterTypeDTO) {
        log.info("添加表计类别：{}" , meterTypeDTO);
        meterService.addMeterType(meterTypeDTO);
        return Resp.ok();
    }
}
