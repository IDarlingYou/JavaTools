package com.ly.tools.entity.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: LY
 * @Date: 2021/4/13 11:42
 * @Description:
 **/
@Data
public class TitleMesDTO {

    @ApiModelProperty(value = "关键词（标题，采购人）", required = true)
    private String keyword;

    @ApiModelProperty(value = "查询类别 1-标题 2-采购人")
    private String searchType;

    @ApiModelProperty(value = "公告类型 1. 招标预告、2. 招标公告、3. 招标变更、4. 中标结果")
    private Integer type;

    @ApiModelProperty(value = "发布开始日期")
    private String publishStartTime;

    @ApiModelProperty(value = "发布结束日期")
    private String publishEndTime;

    @ApiModelProperty(value = "省份")
    private String province;

    @ApiModelProperty(value = "页数")
    private Integer pageNum;

    @ApiModelProperty(value = "每页条数")
    private Integer pageSize;


}
