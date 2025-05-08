package com.example.damcurvity.exception;

public class DamPourException extends RuntimeException {
    private final Integer code;
    private final String message;

    public DamPourException(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public DamPourException(DampouringExceptionEnum exceptionEnum) {
        this(exceptionEnum.getCode(),exceptionEnum.getMsg());
    }

    public Integer getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
