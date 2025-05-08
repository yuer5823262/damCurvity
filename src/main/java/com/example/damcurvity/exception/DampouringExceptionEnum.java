package com.example.damcurvity.exception;

public enum DampouringExceptionEnum {
    NEED_USER_NAME(10001,"用户名不能为空"),
    NEED_PASSWORD(10002,"密码不能为空"),
    PASSWORD_TOO_SHORT(10003,"密码不能小于八位"),
    NAME_EXISTED(10004,"NAME_EXISTED"),
    INSERT_FAILED(10005,"注册失败"),
    PASSWORD_WRONG(10006,"密码错误"),
    NEED_LOGIN(10007,"NEED_LOGIN"),
    UPDATE_FAILED(10008,"更新失败"),
    REQUEST_PARAM_ERROR(10009,"参数错误"),
    ADD_FAILED(10010,"添加失败"),
    DELETE_FAILED(10011,"删除失败"),
    NOT_ADMIN(10012,"NOT_ADMIN"),
    REQUEST_METHOD_ERROR(10013,"REQUEST_METHOD_ERROR"),
    BIG_SB_ADD_ERROR(10014,"数据格式有误"),
    TOKEN_EMPTY(10015,"token错误"),
    DAORU_WRONG(10016,"导入失败"),
    SYSTEM_ERROR(20000,"系统异常");

    Integer code;

    String msg;

    DampouringExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
