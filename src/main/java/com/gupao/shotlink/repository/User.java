package com.gupao.shotlink.repository;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.stereotype.Repository;

@Data
public class User {
    @ApiModelProperty(name = "id")
    private int id;

    @ApiModelProperty(name = "姓名")
    private String name;

    @ApiModelProperty(name = "姓名")
    private int age;

    @ApiModelProperty(name = "邮件")
    private String email;
}
