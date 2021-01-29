package com.example.software.project.development.management.platform.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 统一结果返回类
 *
 * @author hanhuafeng
 * @date 2019/1/7 9:59
 */
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {

    private boolean success = false;
    private Integer code;
    private String msg;
    private T data;

    public Result(ResultCode resultCode) {
        super();
        this.success = resultCode.success;
        this.code = resultCode.code;
        this.msg = resultCode.message;
    }

    public Result(ResultCode resultCode, T data) {
        super();
        this.success = resultCode.success;
        this.code = resultCode.code;
        this.msg = resultCode.message;
        this.data = data;
    }

    public Result(ResultCode resultCode, String message) {
        super();
        this.success = resultCode.success;
        this.code = resultCode.code;
        this.msg = message;
    }
}
