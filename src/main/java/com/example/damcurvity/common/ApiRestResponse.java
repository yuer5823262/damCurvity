package com.example.damcurvity.common;

import com.example.damcurvity.exception.DampouringExceptionEnum;

public class ApiRestResponse<T> {
    private Integer status;
    private String msg;
    private T data;
    private static final  int OK_CODE = 10000;
    private static  final String OK_MSG = "SUCCESS";

    public ApiRestResponse(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ApiRestResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ApiRestResponse() {
        this(OK_CODE,OK_MSG);
    }
    public static <T> ApiRestResponse<T> success(){
        return new ApiRestResponse<>();
    }
    public static <T> ApiRestResponse<T> success(T result){
        ApiRestResponse<T> response = new ApiRestResponse<>();
        response.setData(result);
        return response;
    }

    public static <T> ApiRestResponse<T> error(Integer code, String msg){
        return new ApiRestResponse<>(code,msg);
    }

    public static <T> ApiRestResponse<T> error(DampouringExceptionEnum ex){
        return new ApiRestResponse<>(ex.getCode(),ex.getMsg());
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static int getOkCode() {
        return OK_CODE;
    }

    public static String getOkMsg() {
        return OK_MSG;
    }

    public String toStr(){

        String msg = "{\n" +
                "    \"status\": "+getStatus().toString()+",\n" +
                "    \"msg\": \""+getMsg()+"\",\n" +
                "    \"data\": null\n" +
                "}";

        return msg;

    }
}
