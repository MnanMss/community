package com.cdu.community.server.shared.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author mila
 * @date 2024/6/12 下午10:57
 */
@Getter
@AllArgsConstructor
public enum Code {

    OK("200" , "ok"),

    SYSTEM_ERROR("500" , "系统错误");


    private final String value;

    private final String message;
}
