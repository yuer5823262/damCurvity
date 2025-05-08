package com.example.damcurvity.req;

import lombok.Data;

import javax.validation.constraints.NotNull;
@Data
public class UserLoginReq {
    @NotNull
    String userName;
    @NotNull
    String pwd;



}
