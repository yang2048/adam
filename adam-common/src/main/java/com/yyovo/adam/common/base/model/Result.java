package com.yyovo.adam.common.base.model;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.setting.dialect.Props;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Data
@Accessors(chain = true)
@ApiModel(value = "请求结果响应体")
public class Result<T> {
    @ApiModelProperty(value = "状态")
    private boolean success;
    @ApiModelProperty(value = "状态码")
    private Integer code;
    @ApiModelProperty(value = "描述")
    private String msg;
    @ApiModelProperty(value = "结果集")
    private T data;

    public static Integer FAILED = -1;
    public static Integer SUCCESS = 0;
    /**
     * 属性文件的路径
     */
    private static String PROFILE_PATH = "i18n/message.properties";

    public void setCode(Integer code) {
        this.code = code;
        this.msg = getMsg(code);
    }

    private String getMsg(Integer code) {
        if (StrUtil.isEmpty(msg)) {
            this.msg = getKeyValue(code);
        }
        return msg;
    }

    private Result() {
        this.success = true;
        this.code = SUCCESS;
        this.msg = "执行成功";
    }

    private Result(Integer code) {
        this.success = false;
        this.code = code;
        this.msg = getMsg(code);
    }

    private Result(String msg) {
        this.success = false;
        this.code = FAILED;
        this.msg = msg;
    }

    private Result(T data) {
        this.success = true;
        this.code = SUCCESS;
        this.msg = "执行成功";
        this.data = data;
    }

    public Result(Integer code, String msg) {
        this.success = false;
        this.code = code;
        this.msg = msg;
    }

    private Result(String msg, T data) {
        this.success = false;
        this.code = SUCCESS;
        this.msg = msg;
        this.data = data;
    }

    private Result(Integer code, String msg, T data) {
        this.success = false;
        this.code = code;
        this.msg = StrUtil.isEmpty(getMsg(code)) ? msg : getMsg(code);
        this.data = data;
    }

    public static <T> Result<T> success() {
        return new Result<>();
    }

    public static <T> Result<T> success(T data) {
        return new Result<>(data);
    }

    public static <T> Result<T> success(String msg, T result) {
        return new Result<>(msg, result);
    }

    public static <T> Result<T> failed(Integer code) {
        return new Result<>(code);
    }

    public static <T> Result<T> failed(String msg) {
        return new Result<>(msg);
    }

    public static <T> Result<T> failed(String msg, Object... arguments) {
        return new Result<>(String.format(msg, arguments));
    }

    public static <T> Result<T> failed(IErrorType errorType) {
        return new Result<>(errorType.getCode(), errorType.getMsg());
    }

    public static <T> Result<T> failed(IErrorType errorType, T data) {
        return new Result<>(errorType.getCode(), errorType.getMsg(), data);
    }

    /**
     * @param key 键
     * @return
     * @description 获取key对应的属性值
     */
    public static String getKeyValue(Integer key) {
        Props p = new Props(PROFILE_PATH);
        //中文转码后输出
        return Convert.convertCharset(p.getStr(String.valueOf(key)), "UTF-8", "UTF-8");
    }
}
