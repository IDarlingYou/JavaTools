package com.ly.tools.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: LY
 * @Date: 2021/4/13 11:00
 * @Description:
 **/
@Data
public class Company {

    @ApiModelProperty(value = "公司名称")
    private String companyName;

    @ApiModelProperty(value = "公司ID")
    private String companyId;

}
