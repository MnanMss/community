package com.cdu.community.server.shared.domain;

import lombok.Data;

/**
 * @author mila
 * @date 2024/6/12 下午10:55
 */
@Data
public class Resp<T> {

    private String code;
    private String msg;
    private T data;

    private Resp() {
        this.code = Code.OK.getValue();
        this.msg = Code.OK.getMessage();
    }

    private Resp(Code code) {
        this.code = code.getValue();
        this.msg = code.getMessage();
    }

    private Resp(T data) {
        this();
        this.data = data;
    }


    public static Resp<Void> ok() {return new Resp<>();}

    public static <T> Resp<T> ok(T data) {return new Resp<>(data);}

    /**
     * 业务处理失败
     */
    public static Resp<Void> fail(Code errorCode) {return new Resp<>(errorCode);}

    public static Resp<Void> error() {
        return new Resp<>(Code.SYSTEM_ERROR);
    }
}
