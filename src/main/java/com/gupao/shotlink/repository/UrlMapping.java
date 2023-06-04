package com.gupao.shotlink.repository;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.util.Date;

@Data
public class UrlMapping implements Serializable {

    @ApiModelProperty("主键")
    @TableId(value="id",type = IdType.AUTO )
    private Integer id;

    @ApiModelProperty(value = "短链key")
    private String shortUrl;

    @ApiModelProperty(value = "原始url")
    private String originalUrl;

    @ApiModelProperty(value = "原始url对应md5")
    private String originalUrlHash;

    @ApiModelProperty(value = "访问次数")
    private Integer interviewCount;

    @ApiModelProperty(value = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date createdDate;

    @ApiModelProperty(value = "过期时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date expiredDate;



}
