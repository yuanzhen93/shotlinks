package com.gupao.shotlink.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

@Data
public class UrlMappingDTO {

    @ApiModelProperty(value = "短链key")
    private String key;

    @ApiModelProperty(value = "原始url")
    private String originalUrl;

    @ApiModelProperty(value = "原始url对应md5")
    private String originalUrlHash;

    @ApiModelProperty(value = "访问次数")
    private String interviewCount;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date createdDate;

}