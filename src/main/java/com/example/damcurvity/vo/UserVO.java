package com.example.damcurvity.vo;

import lombok.Data;

@Data
public class UserVO {
    private Integer id;

    private String username;

    private String name;

    private Integer gender;

    private String organization;

    private String department;

    private String position;

    private String phone;

    private Integer type;

    private Integer role;

    private String createTime;

    private String operator;
    private String token;
}
