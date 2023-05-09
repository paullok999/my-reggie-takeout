package com.kalok.reggie.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class EmployeeVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String name;
}
