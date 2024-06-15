package com.cdu.community.server.meter.domain.dto;

import com.cdu.community.server.shared.domain.PageQuery;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * @author mila
 * @date 2024/6/15 下午8:21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MeterReadingRecordSearchDTO extends PageQuery {
    @Schema(description = "大厦/小区ID")
    private Long edificeId;

    @Schema(description = "表号")
    private String num;

    @Schema(description = "计费起始")
    private Date startChargeTime;

    @Schema(description = "计费截止")
    private Date endChargeTime;
}
