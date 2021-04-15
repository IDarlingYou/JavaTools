package com.ly.tools.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @Author: LY
 * @Date: 2021/4/13 11:00
 * @Description:
 **/
@Data
public class CompanyDTO {

    @ApiModelProperty(value = "公司名称或公司ID")
    private List<Company> companyMes;

    @ApiModelProperty(value = "页数",example = "1")
    private Integer pageNum;

    @ApiModelProperty(value = "每页条数",example = "1")
    private Integer pageSize;

}
